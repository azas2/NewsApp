package com.mala.newsapp.ui

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mala.newsapp.Adapter.SpinnerAdapter
import com.mala.newsapp.R
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.ViewModels.NewsViewModel
import com.mala.newsapp.data.DataBase
import com.mala.newsapp.databinding.ActivityNewsBinding


class NewsActivity: AppCompatActivity() {
    lateinit var  viewmodel:NewsViewModel
   private lateinit var binding:ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)
        val newsRepository = Repository(DataBase(this))
        val viewModelProviderFactory = NewsViewModelProvider(newsRepository)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        viewmodel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
        spinnerSetup()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchNewsFragemnt2, R.id.savedNewsFragment -> {
                    binding.myToolbar.visibility = View.GONE
                    this.supportActionBar?.hide()

                }
                      R.id.articleFragment->{
                    binding.myToolbar.visibility = View.VISIBLE
                    this.supportActionBar?.show()
                          binding.spinnerNav.visibility=View.GONE
                }
                R.id.breakingNewsFragment ->{
                    binding.myToolbar.visibility = View.VISIBLE
                    this.supportActionBar?.show()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        NavigationUI.setupActionBarWithNavController(this,findNavController(R.id.fragmentHost)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragmentHost)
        navController.navigateUp()
        return true
    }
    fun spinnerSetup(){
        val data = listOf(
            Triple("us", R.drawable.usa, 2),
            Triple( "eg", R.drawable.egypt, 1
            ),
            Triple("sa", R.drawable.sa, 3)
        )
        val adapter = SpinnerAdapter(this, data)

            binding.spinnerNav.adapter = adapter
        binding.spinnerNav.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = adapter.getItem(position)
                if (selectedItem!=null){
                    val selectedText=selectedItem.first
                    viewmodel.getBreakingNews(selectedText)

                    Toast.makeText(this@NewsActivity, selectedText, Toast.LENGTH_SHORT).show()

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@NewsActivity, "Please select country", Toast.LENGTH_SHORT).show()

            }

        }
    }


}