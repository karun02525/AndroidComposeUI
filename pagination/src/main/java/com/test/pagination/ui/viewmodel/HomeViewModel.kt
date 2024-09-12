package com.test.pagination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.test.pagination.network.ApiService
import com.test.pagination.paging.FakePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,

) : ViewModel() {


    val pager = Pager(
        config = PagingConfig(pageSize = 3, prefetchDistance = 2),
        pagingSourceFactory = {FakePagingSource(apiService)}
    ).flow.cachedIn(viewModelScope)




}