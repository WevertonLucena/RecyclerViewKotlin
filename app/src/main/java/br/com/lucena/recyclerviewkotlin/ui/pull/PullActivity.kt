package br.com.lucena.recyclerviewkotlin.ui.pull

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.lucena.recyclerviewkotlin.R
import android.content.Intent
import android.net.Uri
import br.com.lucena.recyclerviewkotlin.domain.Pull
import android.os.Parcelable
import br.com.lucena.recyclerviewkotlin.adapter.PullAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar


class PullActivity : AppCompatActivity(), PullsContrato.View {

    private var adapter: PullAdapter? = null
    private var progressBar: ProgressBar? = null
    private var pullList: MutableList<Pull>? = null
    private var autor: String? = null
    private var repositorio: String? = null
    private var presenter: PullsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull)

        repositorio = intent.getStringExtra("repositorio")
        autor = intent.getStringExtra("autor")

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        progressBar = findViewById(R.id.progressBar)
        presenter = PullsPresenter(this)
        val manager = LinearLayoutManager(this)
        pullList = ArrayList()

        if (savedInstanceState != null) {
            pullList = savedInstanceState.getParcelableArrayList("pullList")
        }

        adapter = PullAdapter(pullList!!, this, presenter!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("pullList", pullList as ArrayList<out Parcelable>?)
    }

    override fun onResume() {
        super.onResume()
        if (pullList != null && pullList!!.size <= 0)
            presenter!!.buscarPulls(autor!!, repositorio!!)
    }

    override fun mostrarPulls(pulls: List<Pull>) {
        pullList!!.clear()
        pullList!!.addAll(pulls)
        adapter!!.notifyDataSetChanged()
    }

    override fun pullClicado(pull: Pull) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pull.url))
        startActivity(browserIntent)
    }

    override fun mostrarBarraDeProgresso(mostrar: Boolean?) {
        if (mostrar!!)
            progressBar!!.visibility = View.VISIBLE
        else
            progressBar!!.visibility = View.GONE
    }
}
