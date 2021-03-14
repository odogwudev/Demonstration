package com.odogwudev.demonstration.repository.retrofit

import com.google.gson.annotations.SerializedName

data class MenuItemNetworkEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("param")
    val param: String,
    @SerializedName("function")
    val function: String
)