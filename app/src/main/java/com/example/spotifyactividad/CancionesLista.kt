
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyactividad.BuscarFragment
import com.example.spotifyactividad.CancionAdapter
import com.example.spotifyactividad.R
import com.example.tema12.playlistResponse


class CancionesLista : Fragment() {
    private lateinit var playlist: playlistResponse.Playlist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playlist = it.getSerializable("playlist") as playlistResponse.Playlist
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cancioneslista, container, false)
        val arrow = view.findViewById<ImageView>(R.id.volverAtras)
        val playlistNameTextView = view.findViewById<TextView>(R.id.nombrePlaylist)
        val playlistName = playlist.nameOfSong
        playlistNameTextView.text=playlistName



        arrow.setOnClickListener{
            val searchFragment = BuscarFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container ,searchFragment)
                .commit()
        }

        // Configurar el RecyclerView y el adaptador
        val recyclerViewSongs: RecyclerView = view.findViewById(R.id.recycleCanciones)
        val songsAdapter = CancionAdapter(playlist.songs)

        val layoutManager = LinearLayoutManager(context)
        recyclerViewSongs.layoutManager = layoutManager

        recyclerViewSongs.adapter = songsAdapter

        return view
    }




}
