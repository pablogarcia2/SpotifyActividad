package com.example.spotifyactividad
import CancionesLista
import PlaylistAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tema12.playlistResponse
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class BuscarFragment : Fragment(), PlaylistAdapter.OnPlaylistClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var playlistAdapter: PlaylistAdapter
    private val playlists: MutableList<playlistResponse.Playlist> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_buscar, container, false)
        recyclerView = view.findViewById(R.id.playlist_recycle_view)

        // Configurar el RecyclerView con un GridLayoutManager
        val layoutManager = GridLayoutManager(context, NUM_COLUMNAS)
        recyclerView.layoutManager = layoutManager

        // Crear y configurar el adaptador
        playlistAdapter = PlaylistAdapter(playlists)
        playlistAdapter.setOnPlaylistClickListener(this)
        recyclerView.adapter = playlistAdapter

        // Obtener las playlists desde el JSON y actualizar el adaptador
        val json = readJsonFromFile("playlist.json")
        obtenerPlaylistsDesdeJson(json)

        return view
    }

    private fun obtenerPlaylistsDesdeJson(json: String) {
        val gson = Gson()
        val playlistResponse = gson.fromJson(json, playlistResponse::class.java)
        playlists.addAll(playlistResponse.data)
        playlistAdapter.notifyDataSetChanged()
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(requireContext().assets.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    override fun onPlaylistClick(playlist: playlistResponse.Playlist) {
        val CancionesLista = CancionesLista()
        val bundle = Bundle()
        bundle.putSerializable("playlist", playlist)
        CancionesLista.arguments = bundle

        fragmentManager?.beginTransaction()?.replace(R.id.main_container, CancionesLista)?.commit()
    }


    companion object {
        private const val NUM_COLUMNAS = 2
    }
}
