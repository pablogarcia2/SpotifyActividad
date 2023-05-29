package com.example.spotifyactividad

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tema12.playlistResponse
import com.google.android.material.snackbar.Snackbar

import com.squareup.picasso.Picasso
import kotlin.math.log

class CancionAdapter(private val songs: List<playlistResponse.Playlist.Song>) : RecyclerView.Adapter<CancionAdapter.SongViewHolder>() {
    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val Imagen: ImageView = itemView.findViewById(R.id.imagenCancion)
        private val Artista: TextView = itemView.findViewById(R.id.artistaCancion)
        private val Nombre: TextView = itemView.findViewById(R.id.nombreCancion)
        private val Icono: ImageView = itemView.findViewById(R.id.icono)

        fun bind(song: playlistResponse.Playlist.Song) {

            Log.i("Artista",song.artist)
            Log.i("nombre",song.name)

            Picasso.get()
                .load(song.url)
                .into(Imagen)

            Artista.text = song.artist
            Nombre.text = song.name

            Icono.setOnClickListener {

                //esta parte es para el icono de me gusta
                song.esFavorito = !song.esFavorito



                val favoriteIconResId = if (song.esFavorito) {
                    R.drawable.baseline_favorite_24
                } else {
                    R.drawable.baseline_favorite_border_24
                }
                Icono.setImageResource(favoriteIconResId)

                // El mensaje
                if(song.esFavorito){
                    val snackbarMessage = "${song.name} se ha añadido a favoritos"
                    Snackbar.make(itemView, snackbarMessage, Snackbar.LENGTH_SHORT).show()

                }

            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cancion, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return songs.size
    }


}