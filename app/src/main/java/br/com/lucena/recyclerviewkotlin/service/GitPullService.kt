package br.com.lucena.recyclerviewkotlin.service

import android.util.Log
import br.com.lucena.recyclerviewkotlin.domain.Pull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GitPullService : GitService() {

    private var pullList: List<Pull>? = null

    private var listener: LoadPullsListener? = null

    fun buscaPulls(autor: String, repositorio: String) {
        val gitApi = retrofit.create(GitApi::class.java)
        val pulls = gitApi.listPulls(autor, repositorio)

        pulls.enqueue(object : Callback<List<Pull>> {
            override fun onResponse(call: Call<List<Pull>>, response: Response<List<Pull>>) {
                if (response.isSuccessful()) {
                    pullList = response.body()
                    listener!!.onLoad(pullList)
                }
            }

            override fun onFailure(call: Call<List<Pull>>, t: Throwable) {
                Log.e("App", "Erro: " + t.message)
            }
        })
    }

    fun setListener(listener: LoadPullsListener) {
        this.listener = listener
    }

    interface LoadPullsListener {
        fun onLoad(pullList: List<Pull>?)
    }
}
