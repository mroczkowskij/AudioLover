package com.jj.audiolover.di

import com.jj.audiolover.AudioLover
import com.jj.audiolover.data.DiscogsWebInterface
import com.jj.audiolover.di.features.AlbumListFragmentComponent
import com.jj.audiolover.di.features.MainActivityComponent
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import javax.inject.Singleton

/**
 * Created by JJ on 27.01.2018.
 */
@Module(subcomponents = arrayOf(MainActivityComponent::class, AlbumListFragmentComponent::class))
class AppModule(private val app: AudioLover) {
    @Provides
    @Singleton
    fun provideApp(): AudioLover = app

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logingInterceptor = HttpLoggingInterceptor()
        logingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var request = chain.request()
                        val url = request.url().newBuilder()
                                .addQueryParameter("key", "gjxIICNVseJgHDJNituV")
                                .addQueryParameter("secret", "GnjVaDWRncXgonbaaVPLybSIefnskOTe")
                                .build()
                        request = request.newBuilder().url(url).build()
                        return chain.proceed(request)
                    }
                })
                .addInterceptor(logingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl("https://api.discogs.com/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideDiscogsWebinterface(retrofit: Retrofit): DiscogsWebInterface {
        return retrofit.create(DiscogsWebInterface::class.java)
    }

    @Provides
    fun provideViewModelFactory(app: AudioLover): ViewModelFactory {
        return ViewModelFactory(app)
    }
}