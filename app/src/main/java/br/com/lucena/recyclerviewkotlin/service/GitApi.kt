package br.com.lucena.recyclerviewkotlin.service

import br.com.lucena.recyclerviewkotlin.domain.Pull
import retrofit2.http.GET
import br.com.lucena.recyclerviewkotlin.domain.GitHub
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query


interface GitApi {

    @GET("search/repositories")
    fun listReposotories(@Query(value = "q", encoded = true) q: String, @Query("sort") sort: String, @Query("page") page: Int): Call<GitHub>

    @GET("repos/{autor}/{repositorio}/pulls")
    fun listPulls(@Path("autor") autor: String, @Path("repositorio") repositorio: String): Call<List<Pull>>

    companion object {

        val BASE_URL = "https://api.github.com/"
    }
}