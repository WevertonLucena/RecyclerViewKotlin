package br.com.lucena.recyclerviewkotlin.ui.main

import br.com.lucena.recyclerviewkotlin.domain.Repositorio
import br.com.lucena.recyclerviewkotlin.model.RepositorioModel
import br.com.lucena.recyclerviewkotlin.adapter.RepositorioAdapter


class MainPresenter(private val view: MainContrato.View) : MainContrato.Presenter, RepositorioAdapter.OnItemClickListener {
    private val model: MainContrato.Model

    init {
        model = RepositorioModel(this)
    }

    override fun solicitarRepositorios(page: Int) {
        view.mostrarBarraDeProgresso(true)
        model.buscarRepositorios(page)
    }

    override fun onItemClick(repositorio: Repositorio) {
        view.repositorioClicado(repositorio)
    }

    override fun carregarRepositorios(repositoriosList: List<Repositorio>) {
        view.mostrarRepositorios(repositoriosList)
        view.mostrarBarraDeProgresso(false)
    }
}
