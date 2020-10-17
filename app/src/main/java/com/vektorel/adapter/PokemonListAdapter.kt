package com.vektorel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vektorel.R
import com.vektorel.models.Pokemon
class PokemonListAdapter(pokemonList: ArrayList<Pokemon>, mContext: Context):
    RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>(){

    var pkList = pokemonList
    var mycontext = mContext

    class PokemonListViewHolder(parent:ViewGroup):
        RecyclerView.ViewHolder(parent){

        var ad = parent.findViewById<TextView>(R.id.txtad)
        var url = parent.findViewById<TextView>(R.id.txturl)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        var myinflate = LayoutInflater.from(parent.context)
        var myPokemonLayout = myinflate.inflate(R.layout.pokemon_list,parent,false)
        return PokemonListViewHolder(myPokemonLayout as ViewGroup)
    }

    override fun getItemCount(): Int {
       return pkList.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.ad.text = pkList.get(position).name
        holder.url.text = pkList.get(position).url
    }
}