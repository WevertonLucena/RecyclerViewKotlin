package br.com.lucena.recyclerviewkotlin.service

import android.util.Log
import br.com.lucena.recyclerviewkotlin.domain.Repositorio
import br.com.lucena.recyclerviewkotlin.domain.GitHub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GitRepositorioService : GitService() {

    internal lateinit var repositorioList: List<Repositorio>

    internal lateinit var listener: LoadRepositoriosListener

    fun buscaRepositorios(page: Int) {
        val gitApi = retrofit.create(GitApi::class.java)
        val repositorios = gitApi.listReposotories("language:Java", "stars", page)

        repositorios.enqueue(object : Callback<GitHub> {
            override fun onResponse(call: Call<GitHub>, response: Response<GitHub>) {
                if (response.isSuccessful()) {
                    repositorioList = response.body()!!.items!!
                    listener.onLoad(repositorioList)
                }
            }

            override fun onFailure(call: Call<GitHub>, t: Throwable) {
                Log.e("App", "Erro: " + t.message)
            }
        })


    }

    fun setListener(listener: LoadRepositoriosListener) {
        this.listener = listener
    }


    interface LoadRepositoriosListener {
        fun onLoad(repositoriosList: List<Repositorio>)
    }
}
