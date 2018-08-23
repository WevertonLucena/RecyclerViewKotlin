package br.com.lucena.recyclerviewkotlin.adapter

import android.content.Context
import br.com.lucena.recyclerviewkotlin.domain.Pull
import com.squareup.picasso.Picasso
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.lucena.recyclerviewkotlin.R
import java.text.SimpleDateFormat


class PullAdapter(private val data: List<Pull>, private val context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<PullAdapter.VHolder>() {
    private val picasso: Picasso

    init {
        picasso = Picasso.with(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.pull_item, parent, false)
        return VHolder(view)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtTitulo: TextView
        internal var txtData: TextView
        internal var txtBody: TextView
        internal var txtAutor: TextView
        internal var imgAvatar: ImageView

        init {
            txtTitulo = itemView.findViewById(R.id.txtTitulo)
            txtBody = itemView.findViewById(R.id.txtBody)
            txtData = itemView.findViewById(R.id.txtData)
            txtAutor = itemView.findViewById(R.id.txtNome)
            imgAvatar = itemView.findViewById(R.id.imgAvatar)

        }

        fun bind(pull: Pull, listener: OnItemClickListener) {
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            txtTitulo.text = pull.title
            txtBody.text = pull.body
            txtData.setText(format.format(pull.date))
            txtAutor.text = pull.user.login
            Picasso.with(itemView.context).load(pull.user.avatar).into(imgAvatar)
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    listener.onItemClick(pull)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClick(pull: Pull)
    }
}