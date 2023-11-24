package com.example.dictionaryapp.feature_dictionary.presentation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = wordInfo.word,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        wordInfo.meanings.forEach {meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed {i , definition ->
                Text(text = "${i + 1}. ${definition.definition} ")
                Spacer(modifier = modifier.height(8.dp))
                definition.example?.let {example ->
                    Text(text = "Example: $example")
                }
                Spacer(modifier = modifier.height(8.dp))

            }
            Spacer(modifier = modifier.height(16.dp))

        }

    }

}