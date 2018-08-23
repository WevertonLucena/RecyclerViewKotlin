package br.com.lucena.recyclerviewkotlin.model

import br.com.lucena.recyclerviewkotlin.service.GitPullService
import br.com.lucena.recyclerviewkotlin.domain.Pull
import br.com.lucena.recyclerviewkotlin.ui.pull.PullsContrato


class PullModel(private val presenter: PullsContrato.Presenter) : PullsContrato.Model, GitPullService.LoadPullsListener {

    override fun onLoad(pullList: List<Pull>?) {
        presenter.carregarPulls(pullList!!)
    }

    override fun buscarPulls(autor: String, repositorio: String) {
        val service = GitPullService()
        service.setListener(this)
        service.buscaPulls(autor, repositorio)
    }
}
