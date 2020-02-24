package com.urielhernandez.siriusxmtest.di.module

import com.google.gson.GsonBuilder
import com.urielhernandez.siriusxmtest.utils.BASE_URL
import com.urielhernandez.siriusxmtest.networking.BooksApi
import com.urielhernandez.siriusxmtest.utils.API_KEY
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY_VALUE = "apikey"

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideBooksApi(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        okHttpBuilder.addInterceptor {
            val request = it.request()
            val url = request.url().newBuilder()
                .addQueryParameter(API_KEY_VALUE, API_KEY)
                .build()
            it.proceed(request.newBuilder().url(url).build())
        }

        val gson = GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()

        val gsonConverter = GsonConverterFactory.create(gson)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    }
}