package com.example.dictionaryapp.core.utils

sealed class UiEvent{

    data class Navigate(val route: String): UiEvent()

    object OnNavigateUp: UiEvent()

    data class ShowSnackBar(val message: String): UiEvent()

}
