package br.com.lucena.recyclerviewkotlin.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.lucena.recyclerviewkotlin.R
import android.content.Intent
import br.com.lucena.recyclerviewkotlin.domain.Repositorio
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView
import br.com.lucena.recyclerviewkotlin.adapter.RepositorioAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ProgressBar
import br.com.lucena.recyclerviewkotlin.ui.pull.PullActivity


class MainActivity : AppCompatActivity(), MainContrato.View {

    private var manager: LinearLayoutManager? = null
    private var adapter: RepositorioAdapter? = null
    private var isScrolling: Boolean? = false
    private var currentItems: Int = 0
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0
    private var progressBar: ProgressBar? = null
    private var repositorios: MutableList<Repositorio>? = null
    private var page: Int = 0

    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        progressBar = findViewById(R.id.progressBar)
        manager = LinearLayoutManager(this)
        repositorios = ArrayList()
        page = 1

        presenter = MainPresenter(this)

        if (savedInstanceState != null) {
            repositorios = savedInstanceState.getParcelableArrayList("repositorios")
            page = savedInstanceState.getInt("page")
        }

        adapter = RepositorioAdapter(repositorios!!, this, presenter!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager!!.childCount
                totalItems = manager!!.itemCount
                scrollOutItems = manager!!.findFirstVisibleItemPosition()

                if (isScrolling!! && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false
                    fetchData()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", page)
        outState.putParcelableArrayList("repositorios", repositorios as ArrayList<out Parcelable>?)
    }

    override fun onResume() {
        super.onResume()
        if (repositorios != null && repositorios!!.size <= 0) {
            presenter!!.solicitarRepositorios(page)
        }
    }

    private fun fetchData() {
        page++
        presenter!!.solicitarRepositorios(page)
    }

    override fun mostrarRepositorios(repositoriosList: List<Repositorio>) {
        repositorios!!.addAll(repositoriosList)
        adapter!!.notifyDataSetChanged()
    }

    override fun repositorioClicado(repositorio: Repositorio) {
        val i = Intent(this, PullActivity::class.java)
        i.putExtra("repositorio", repositorio.name)
        i.putExtra("autor", repositorio.autor!!.login)
        startActivity(i)
    }

    override fun mostrarBarraDeProgresso(mostrar: Boolean) {
        if (mostrar)
            progressBar!!.visibility = View.VISIBLE
        else
            progressBar!!.visibility = View.GONE
    }
}
