package com.thoughtworks.kotlin_basic.product.adapter

import com.thoughtworks.kotlin_basic.product.model.Inventory

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class InventoryAdapter(
    private val externalService: ExternalInventoryApi
) {

    fun listInventory(): List<Inventory> {
        val result = externalService.listInventories().execute().body() ?: emptyList()
        return result.map {
            Inventory(it.id, it.sku, enumValueOf(it.zone), it.quantity)
        }
    }

}