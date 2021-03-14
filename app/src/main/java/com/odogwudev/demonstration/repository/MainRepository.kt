package com.odogwudev.demonstration.repository

import android.content.Context
import com.google.gson.Gson
import com.odogwudev.demonstration.model.FunctionType
import com.odogwudev.demonstration.model.ItemMenuEntity
import com.odogwudev.demonstration.repository.retrofit.FileService
import com.odogwudev.demonstration.repository.retrofit.Menu
import com.odogwudev.demonstration.repository.retrofit.MenuItemNetworkMapper
import com.odogwudev.demonstration.utils.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.IOException

class MainRepository
constructor(
    @ApplicationContext private val context: Context,
    private val fileService: FileService,
    private val menuItemNetworkMapper: MenuItemNetworkMapper
) {
    suspend fun getMenuItems(): Flow<DataState<List<ItemMenuEntity>>> = flow {
        emit(DataState.Loading)
        try {
            val stringResult = getStringFromBodyStream(fileService.getJSONObject())
            val list = Gson().fromJson(stringResult, Menu::class.java)
            val resultList = menuItemNetworkMapper.mapFromEntityList(list.items)
            emit(DataState.Success(resultList))
        } catch (ex: Exception) {
            val list = listOf(
                ItemMenuEntity(
                    "Item 1",
                    FunctionType.Text("There is no connection to the internet")
                )
            )
            emit(DataState.Success(list))
        }
    }

    private fun getStringFromBodyStream(body: ResponseBody): String {
        return try {
            String(body.byteStream().readBytes())
        } catch (e: IOException) {
            String()
        }
    }

    companion object {
        const val TAG = "MainRepository"
    }
}