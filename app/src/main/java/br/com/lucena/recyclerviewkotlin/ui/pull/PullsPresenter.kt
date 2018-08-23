package br.com.lucena.recyclerviewkotlin.ui.pull

import br.com.lucena.recyclerviewkotlin.domain.Pull
import br.com.lucena.recyclerviewkotlin.adapter.PullAdapter
import br.com.lucena.recyclerviewkotlin.model.PullModel


class PullsPresenter(private val view: PullsContrato.View) : PullsContrato.Presenter, PullAdapter.OnItemClickListener {
    private val model: PullsContrato.Model

    init {
        model = PullModel(this)
    }

    override fun buscarPulls(autor: String, repositorio: String) {
        view.mostrarBarraDeProgresso(true)
        model.buscarPulls(autor, repositorio)
    }

    override fun carregarPulls(pullList: List<Pull>) {
        view.mostrarPulls(pullList)
        view.mostrarBarraDeProgresso(false)
    }

    override fun onItemClick(pull: Pull) {
        view.pullClicado(pull)
    }
}