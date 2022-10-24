package com.ebc.rickmortyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ebc.rickmortyapp.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import com.ebc.rickmortyapp.paging.RickMortyPaginSource
import javax.inject.Inject

@HiltViewModel()
internal class RickMortyModel
@Inject constructor(private val apiService: ApiService):ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)){
        RickMortyPaginSource(apiService)
    }.flow.cachedIn((viewModelScope))
}