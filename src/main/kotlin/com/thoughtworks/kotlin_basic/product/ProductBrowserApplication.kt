package com.thoughtworks.kotlin_basic.product

import com.thoughtworks.kotlin_basic.product.adapter.ExternalJsonService
import com.thoughtworks.kotlin_basic.product.adapter.HttpClient
import com.thoughtworks.kotlin_basic.product.adapter.InventoryAdapter
import com.thoughtworks.kotlin_basic.product.adapter.ProductAdapter

/**
 * @author zijie.li
 * @since 2024/3/21
 */

fun main(args: Array<String>) {
    val externalJsonService = HttpClient.getService(ExternalJsonService::class)
    val inventoryAdapter = InventoryAdapter(externalJsonService)
    val productAdapter = ProductAdapter(externalJsonService)
    val inventories = inventoryAdapter.listInventory()
    val products = productAdapter.listProduct()
    println(inventories)
    println(products)
}
