package com.example.dictionaryapp.feature_dictionary.domain.repository

import com.example.dictionaryapp.core.utils.Resources
import com.example.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List

interface WordInfoRepository {

     fun getWordInfo(word: String): Flow<Resources<List<WordInfo>>>

}