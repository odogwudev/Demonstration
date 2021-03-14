package com.odogwudev.demonstration.model

data class ItemMenuEntity(
    val name: String,
    val function: FunctionType
)

sealed class FunctionType(val param: String) {
    class Text(param: String) : FunctionType(param)
    class Image(param: String) : FunctionType(param)
    class Url(param: String) : FunctionType(param)
}