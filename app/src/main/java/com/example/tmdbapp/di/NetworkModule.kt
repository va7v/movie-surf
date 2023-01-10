//@Module
//class NetworkModule {
//    const val BASE_URL = BuildConfig.BASE_URL
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApi(retrofit: Retrofit): WebApi {
//        return retrofit.create(WebApi::class.java)
//    }
//
//}
