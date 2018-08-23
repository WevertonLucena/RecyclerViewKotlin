package br.com.lucena.recyclerviewkotlin.service

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


open class GitService {

    protected var retrofit = Retrofit.Builder()
            .baseUrl(GitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}