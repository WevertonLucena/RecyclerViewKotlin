package br.com.lucena.recyclerviewkotlin.ui.pull

import br.com.lucena.recyclerviewkotlin.domain.Pull



interface PullsContrato {

    interface Model {
        fun buscarPulls(autor: String, repositorio: String)
    }

    interface View {
        fun mostrarPulls(pullList: List<Pull>)
        fun pullClicado(pull: Pull)
        fun mostrarBarraDeProgresso(mostrar: Boolean?)
    }

    interface Presenter {
        fun buscarPulls(autor: String, repositorio: String)
        fun carregarPulls(pullList: List<Pull>)
    }
}