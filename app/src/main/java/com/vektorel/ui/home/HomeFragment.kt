package com.vektorel.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.vektorel.R
import com.vektorel.adapter.PokemonListAdapter
import com.vektorel.models.Pokemon
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Method

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var rclist: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        rclist = root.findViewById(R.id.recyclerView_pokemon)
        // RecyclerView yerleşim şeklini belirliyorsunuz
        var layoutmanager =
                LinearLayoutManager(requireContext(),
                        LinearLayoutManager.VERTICAL,false)
        rclist.layoutManager = layoutmanager
        Istek()
        return root
    }

    fun Istek(){
        var myque = Volley.newRequestQueue(requireContext())
        var url = "https://pokeapi.co/api/v2/pokemon/?limit=30&offset=30"

        var restIstek = JsonObjectRequest(Request.Method.GET,
        url, null,
        Response.Listener<JSONObject> {response ->
               var myresults = response.getJSONArray("results") as JSONArray
               var uzunluk = myresults.length()-1
                var pokeList = ArrayList<Pokemon>()

            for (x in 0..uzunluk){
                // önce pokemon nesnesi oluşturulur
                var tmp = Pokemon()
                // json isteğinden gelen joson listesinden sıra ile veri çekilir.
                var name = myresults.getJSONObject(x).getString("name")
                var url = myresults.getJSONObject(x).getString("url")
                // nesne değerleri doldurulur
                tmp.name = name
                tmp.url = url
                // listeye eklenir.
                pokeList.add(tmp)
            }

            var pwadapter = PokemonListAdapter(pokeList,requireContext())
            rclist.adapter = pwadapter

        },
        Response.ErrorListener {
            Log.d("HATA", "Istek hata: "+ it.message)
        })

        myque.add(restIstek)
    }
}