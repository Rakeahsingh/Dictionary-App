package com.example.dictionaryapp.feature_dictionary.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.dictionaryapp.core.utils.Resources
import com.example.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.example.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepositoryImp(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getWordInfo(word: String): Flow<Resources<List<WordInfo>>> {
        return flow {
            emit(Resources.Loading())

            val wordInfo = dao.getWordInfo(word).map {
                it.toWordInfo() }
            emit(Resources.Loading(data = wordInfo))

            try {
                val remoteWordInfo = api.getWordInfo(word)
                dao.deleteWordInfo(remoteWordInfo.map {
                    it.word }
                )
                dao.insertWordInfo(remoteWordInfo.map {
                    it.toWordInfoEntity()
                })
//                dao.deleteAllWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })

            }catch (e: HttpException){
                emit(Resources.Error(
                    message = "Oops, Something Went Wrong ",
                    data = wordInfo
                ))

            }catch (e: IOException){
                emit(Resources.Error(
                    message = "Couldn't, Reach Server Please Check your Internet ",
                    data = wordInfo
                ))
            }catch (e: retrofit2.HttpException){
                emit(Resources.Error(
                    message = " Word Not Found",
                    data = wordInfo
                ))
            }

            val newWordInfo = dao.getWordInfo(word).map {
                it.toWordInfo() }
            emit(Resources.Success(newWordInfo))

        }
    }
}