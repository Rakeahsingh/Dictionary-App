package com.example.dictionaryapp.feature_dictionary.presentation.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.core.utils.Resources
import com.example.dictionaryapp.core.utils.UiEvent
import com.example.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWord: GetWordInfo
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var state by mutableStateOf(WordInfoState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String){
        searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWord(query)
                .onEach {result ->
                when (result){
                    is Resources.Success -> {
                        state = state.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resources.Error -> {
                        state = state.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _uiEvent.send(UiEvent.ShowSnackBar(
                            message = result.message ?: "UnKnown Error"
                        ))
                    }
                    is Resources.Loading -> {
                        state = state.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }

                }
                }.launchIn(viewModelScope)
        }
    }

}