package com.example.dictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity


@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(entity: List<WordInfoEntity>)

    @Query(" DELETE FROM WordInfoEntity WHERE word IN (:word) ")
    suspend fun deleteWordInfo(word: List<String>)

//    @Delete
//    suspend fun deleteAllWordInfo(entity: List<WordInfoEntity>)

    @Query(" SELECT * FROM WordInfoEntity WHERE word LIKE '%' || :word || '%' ")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>

}