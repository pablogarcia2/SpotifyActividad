

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyactividad.R
import com.squareup.picasso.Picasso
import com.example.tema12.playlistResponse.Playlist


class PlaylistAdapter(private val playlists: List<Playlist>) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var playlistClickListener: OnPlaylistClickListener? = null

    fun setOnPlaylistClickListener(listener: OnPlaylistClickListener) {
        playlistClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val Imagen: ImageView = itemView.findViewById(R.id.imagen_playlist)
        private val Titulo: TextView = itemView.findViewById(R.id.titulo_playlist)
        private val Seguidores: TextView = itemView.findViewById(R.id.seguidores_playlist)

        init {
            itemView.setOnClickListener {
                val playlist = playlists[adapterPosition]
                playlistClickListener?.onPlaylistClick(playlist)
            }
        }

        fun bind(playlist: Playlist) {
            Titulo.text = playlist.nameOfSong
            Seguidores.text = "${playlist.numberOfFollowers} SEGUIDORES "
            Picasso.get()
                .load(playlist.dummyImageUrl)
                .into(Imagen)

        }
    }

    interface OnPlaylistClickListener {
        fun onPlaylistClick(playlist: Playlist)
    }
}
