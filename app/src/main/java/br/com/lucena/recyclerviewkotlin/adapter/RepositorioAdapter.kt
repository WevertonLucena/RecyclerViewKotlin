package br.com.lucena.recyclerviewkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.lucena.recyclerviewkotlin.R
import br.com.lucena.recyclerviewkotlin.domain.Repositorio
import com.squareup.picasso.Picasso

class RepositorioAdapter(data:List<Repositorio>, context: Context, listener:OnItemClickListener): RecyclerView.Adapter<RepositorioAdapter.VHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    internal var data:List<Repositorio> = data
    private val listener:OnItemClickListener
    internal var context:Context
    private var picasso: Picasso
    init{
        this.listener = listener
        this.context = context
        picasso = Picasso.with(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):VHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.repositorio_item, parent, false)
        return VHolder(view)
    }
    override fun onBindViewHolder(holder:VHolder, position:Int) {
        holder.bind(data.get(position), listener)
    }
    class VHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var txtNome:TextView
        internal var txtDescricao:TextView
        internal var txtForks:TextView
        internal var txtStars:TextView
        internal var imgAvatar:ImageView
        internal var txtAutor:TextView
        init{
            txtNome = itemView.findViewById(R.id.txtNome)
            txtDescricao = itemView.findViewById(R.id.txtDescricao)
            txtForks = itemView.findViewById(R.id.txtForks)
            txtStars = itemView.findViewById(R.id.txtStars)
            imgAvatar = itemView.findViewById(R.id.imgAvatar)
            txtAutor = itemView.findViewById(R.id.txtAutor)
        }
        fun bind(repositorio:Repositorio, listener:OnItemClickListener) {
            txtNome.setText(repositorio.name)
            txtDescricao.setText(repositorio.description)
            txtForks.setText(repositorio.forks.toString())
            this.txtStars.setText(repositorio.stars.toString())
            txtAutor.setText(repositorio.autor!!.login)
            Picasso.with(itemView.context).load(repositorio.autor!!.avatar).into(imgAvatar)
            itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v:View) {
                    listener.onItemClick(repositorio)
                }
            })
        }
    }
    interface OnItemClickListener {
        fun onItemClick(repositorio:Repositorio)
    }
}