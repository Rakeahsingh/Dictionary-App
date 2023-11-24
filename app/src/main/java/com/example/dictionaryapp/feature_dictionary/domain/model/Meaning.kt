package com.example.dictionaryapp.feature_dictionary.domain.model


data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Defination>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
