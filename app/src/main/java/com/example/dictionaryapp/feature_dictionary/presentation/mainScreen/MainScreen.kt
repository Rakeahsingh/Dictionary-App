package com.example.dictionaryapp.feature_dictionary.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionaryapp.core.utils.UiEvent

@Composable
fun MainScreen(
    viewModel: WordInfoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit
) {

    val state = viewModel.state


    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{event ->
            when(event){

                is UiEvent.OnNavigateUp -> onNavigateUp()

                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                else -> Unit
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = viewModel.searchQuery,
            onValueChange = viewModel::onSearch,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            shape = RoundedCornerShape(5.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = modifier.fillMaxSize()
        ){
            items(state.wordInfoItem.size){i ->
                val wordInfo = state.wordInfoItem[i]
                if (i > 0){
                    Spacer(modifier = Modifier.height(8.dp))
                }
                WordInfoItem(wordInfo = wordInfo)
                if (i < state.wordInfoItem.size - 1) {
                    Divider()
                }
            }
        }

    }

}