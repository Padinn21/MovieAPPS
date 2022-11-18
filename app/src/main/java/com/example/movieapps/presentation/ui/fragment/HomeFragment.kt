package com.example.movieapps.presentation.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.R
import com.example.movieapps.data.model.ResponsePopularMovieItem
import com.example.movieapps.data.model.SerialResponseItem
import com.example.movieapps.data.viewmodel.LoginViewModel
import com.example.movieapps.data.viewmodel.ViewModelFactory
import com.example.movieapps.data.viewmodel.ViewModelPopularMovie
import com.example.movieapps.databinding.FragmentHomeBinding
import com.example.movieapps.presentation.ui.adapter.MovieAdapter
import com.example.movieapps.presentation.ui.adapter.SerialAdapter

class HomeFragment : Fragment() {
    private lateinit var pref: com.example.movieapps.data.datastore.LoginDataStoreManager
    private lateinit var viewModelLoginPref: LoginViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = com.example.movieapps.data.datastore.LoginDataStoreManager(this.requireActivity())
        viewModelLoginPref = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        viewModelLoginPref.getUser().observe(this.requireActivity(),{
            binding.tvSayHello.text = "Welcome, " + it.name
        })
        showDataMoviePopoular()
        showDataSerialPopular()

        binding.btnFavorite.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }


    fun showDataMoviePopoular() {
        val viewModel = ViewModelProvider(this).get(ViewModelPopularMovie::class.java)
        viewModel.callApiPopularMovie{movies: List<ResponsePopularMovieItem> ->
            binding.rvMovie1.adapter = MovieAdapter(movies)
        }
        binding.rvMovie1.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMovie1.setHasFixedSize(true)
    }

    fun showDataSerialPopular() {
        val viewModel = ViewModelProvider(this).get(ViewModelPopularMovie::class.java)
        viewModel.callApiTvSerial{serial: List<SerialResponseItem> ->
            binding.rvMovie2.adapter = SerialAdapter(serial)
        }
        binding.rvMovie2.layoutManager = LinearLayoutManager(this.requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMovie2.setHasFixedSize(true)
    }
}
