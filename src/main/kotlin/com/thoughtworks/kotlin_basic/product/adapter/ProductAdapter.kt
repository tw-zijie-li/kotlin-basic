package com.thoughtworks.kotlin_basic.product.adapter

import com.thoughtworks.kotlin_basic.product.Product

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class ProductAdapter(
    private val externalService: ExternalProductApi
) {

    fun listProduct(): List<Product> {
        val result = externalService.listProducts().execute().body() ?: emptyList()
        return result.map {
            Product(it.id, it.sku, it.name, it.price, enumValueOf(it.type), it.image)
        }
    }

}