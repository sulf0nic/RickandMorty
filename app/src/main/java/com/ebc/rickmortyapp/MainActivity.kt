package com.ebc.rickmortyapp

import android.os.Bundle
import android.service.controls.templates.StatelessTemplate
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ebc.rickmortyapp.adapter.RickMortyPagedAdapter
import com.ebc.rickmortyapp.databinding.ActivityMainBinding
import com.ebc.rickmortyapp.viewmodel.RickMortyModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter:RickMortyPagedAdapter
    private val viewModel:RickMortyModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRv()
        loadingData()
        }

    private fun loadingData(){
        lifecycleScope.launch{
            viewModel.listData.collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv(){
        mAdapter = RickMortyPagedAdapter()
        binding.recyclerview.apply{
            layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)

        }
    }
    }



