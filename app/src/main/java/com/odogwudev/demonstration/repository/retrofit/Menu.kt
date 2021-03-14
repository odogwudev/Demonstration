package com.odogwudev.demonstration.repository.retrofit

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("menu")
    val items: List<MenuItemNetworkEntity>
)