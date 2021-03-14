package com.odogwudev.demonstration.repository.retrofit

import com.odogwudev.demonstration.model.FunctionType
import com.odogwudev.demonstration.model.ItemMenuEntity
import com.odogwudev.demonstration.utils.EntityMapper
import javax.inject.Inject

class MenuItemNetworkMapper @Inject constructor() :
    EntityMapper<MenuItemNetworkEntity, ItemMenuEntity> {
    override fun mapFromEntity(entity: MenuItemNetworkEntity): ItemMenuEntity {
        return ItemMenuEntity(
            entity.name,
            when (entity.function) {
                "text" -> FunctionType.Text(entity.param)
                "image" -> FunctionType.Image(entity.param)
                "url" -> FunctionType.Url(entity.param)
                else -> FunctionType.Text("Unnamed function type!")
            }
        )
    }

    override fun mapToEntity(domainModel: ItemMenuEntity): MenuItemNetworkEntity {
        return MenuItemNetworkEntity(
            domainModel.name,
            domainModel.function.param,
            when (domainModel.function) {
                is FunctionType.Text -> "text"
                is FunctionType.Image -> "image"
                is FunctionType.Url -> "Url"
            }
        )
    }

    fun mapFromEntityList(entities: List<MenuItemNetworkEntity>): List<ItemMenuEntity> {
        return entities.map { mapFromEntity(it) }
    }
}