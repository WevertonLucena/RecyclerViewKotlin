package br.com.lucena.recyclerviewkotlin.ui.main

import br.com.lucena.recyclerviewkotlin.domain.Repositorio

interface MainContrato {

    interface Model {
        fun buscarRepositorios(page:Int)
    }

    interface View {
        fun mostrarRepositorios(repositoriosList:List<Repositorio>)
        fun repositorioClicado(repositorio:Repositorio)
        fun mostrarBarraDeProgresso(mostrar:Boolean)
    }

    interface Presenter {
        fun solicitarRepositorios(page:Int)
        fun carregarRepositorios(repositoriosList:List<Repositorio>)
    }
}