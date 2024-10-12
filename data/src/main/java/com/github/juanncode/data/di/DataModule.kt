package com.github.juanncode.data.di

import androidx.room.Room
import com.github.juanncode.data.BuildConfig
import com.github.juanncode.data.database.RecipeDatabase
import com.github.juanncode.data.datasource.local.LocalDataSource
import com.github.juanncode.data.datasource.local.RoomDataSource
import com.github.juanncode.data.datasource.remote.RemoteDataSource
import com.github.juanncode.data.datasource.remote.RetrofitDataSource
import com.github.juanncode.data.repository.RecipeRepositoryImpl
import com.github.juanncode.data.retrofit.ApiService
import com.github.juanncode.domain.repository.RecipeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val dataModule = module{

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            okHttpBuilder
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
        }

        okHttpBuilder
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java,
            "recipe.db"
        ).build()
    }

    single {
        get<RecipeDatabase>().recipeDao
    }

    singleOf(::RoomDataSource).bind<LocalDataSource>()
    singleOf(::RetrofitDataSource).bind<RemoteDataSource>()
    singleOf(::RecipeRepositoryImpl).bind<RecipeRepository>()
}