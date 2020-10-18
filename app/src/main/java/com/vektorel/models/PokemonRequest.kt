package com.vektorel.models

class PokemonRequest {
    var count: String = ""
    var next: String = ""
    var previous: String = ""
    lateinit  var results: ArrayList<Pokemon>
}

