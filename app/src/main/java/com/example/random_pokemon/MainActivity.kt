package com.example.random_pokemon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
//    private lateinit var pokemon_image : ImageView
//    private lateinit var pokemon_name: TextView
//    private lateinit var pokemon_id: TextView

    private lateinit var pokemonList: MutableList<pokemon>
    private lateinit var rv_pokemon : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        pokemon_image = findViewById(R.id.pokemon_image)
//        pokemon_name = findViewById(R.id.pokemon_name)
//        pokemon_id = findViewById(R.id.pokemon_id)

        pokemonList = mutableListOf()
        rv_pokemon = findViewById(R.id.rvPokemon)
        GetPokemonURL()
        //Getting API call
        //next button
        //display image
    }


    private fun GetPokemonURL() {
        val client = AsyncHttpClient()

        for(i in 0 until 20){
            val random = Random.nextInt(1, 1000)

        client["https://pokeapi.co/api/v2/pokemon/$random",object : JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("pokemon URL error", response)
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//                Log.d("pokemon URL success", "$json")
                var newItem = pokemon(
                    json.jsonObject.getJSONObject("sprites").getString("front_default"),
                    json.jsonObject.getString("name"),
                    json.jsonObject.getString("id")
                )
                Log.d("onSucess","$pokemonList")
                pokemonList.add(newItem)
                val adapter = PokemonAdapter(pokemonList)
                rv_pokemon.adapter = adapter
                rv_pokemon.layoutManager = LinearLayoutManager(this@MainActivity)
//                pokemon_name.text = json?.jsonObject?.getString("name")
                //name
//                pokemon_id.text = json?.jsonObject?.getString("id")
                //ID #
//                var image = json?.jsonObject?.getJSONObject("sprites")?.getString("front_default")
                //Sprite -> front default
//                Glide.with(this@MainActivity)
//                    .load(image)
//                    .fitCenter()
//                    .into(pokemon_image)
            }

        }]
    }
    }
}