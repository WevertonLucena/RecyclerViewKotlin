package br.com.lucena.recyclerviewkotlin.model


import br.com.lucena.recyclerviewkotlin.domain.Repositorio
import br.com.lucena.recyclerviewkotlin.service.GitRepositorioService
import br.com.lucena.recyclerviewkotlin.ui.main.MainContrato

class RepositorioModel(private val presenter: MainContrato.Presenter) : MainContrato.Model, GitRepositorioService.LoadRepositoriosListener {

    override fun buscarRepositorios(page: Int) {
        val service = GitRepositorioService()
        service.setListener(this)
        service.buscaRepositorios(page)
    }

    override fun onLoad(repositoriosList: List<Repositorio>) {
        presenter.carregarRepositorios(repositoriosList)
    }
}