package com.example.dictionaryapp.feature_dictionary.domain.model

data class Defination(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)
