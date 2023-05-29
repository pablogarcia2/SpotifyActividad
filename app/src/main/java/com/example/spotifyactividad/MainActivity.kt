package com.example.spotifyactividad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)
        val boton: BottomNavigationView = findViewById(R.id.Nav)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, InicioFragment()).commit()
        boton.setOnItemSelectedListener {menuItem->
            when(menuItem.itemId){

                R.id.home ->{
                    goToFragment(InicioFragment())
                    true
                }
                R.id.buscar->{
                    goToFragment(BuscarFragment())
                    true
                }
                R.id.biblioteca ->{
                    goToFragment(BibliotecaFragment())
                    true
                }
                R.id.premium ->{
                    goToFragment(PremiumFragment())
                    true
                }
                else -> false
            }

        }
    }

    fun goToFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).commit()
    }
}