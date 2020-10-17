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
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.vektorel.R
import com.vektorel.adapter.PokemonListAdapter
import org.json.JSONObject
import java.lang.reflect.Method

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var rclist: RecyclerView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        rclist = root.findViewById(R.id.recyclerView_pokemon)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    fun Istek(){
        var myque = Volley.newRequestQueue(requireContext())
        var url = "https://pokeapi.co/api/v2/pokemon/?limit=30&offset=30"

        var restIstek = JsonObjectRequest(Request.Method.GET,
        url, null,
        Response.Listener<JSONObject> {response ->
               var myresults = response.getJSONArray("results")
               var uzunluk = myresults.length()-1


            for (x in 0..uzunluk){

            }
            var pwadapter = PokemonListAdapter(ArrayList())
            rclist.adapter = pwadapter

        },
        Response.ErrorListener {

        })

        myque.add(restIstek)
    }
}