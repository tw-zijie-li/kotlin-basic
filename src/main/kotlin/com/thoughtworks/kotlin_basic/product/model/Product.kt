package com.thoughtworks.kotlin_basic.product

import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class Product(
    val id: Int,
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val type: ProductType,
    val image: String
)

enum class ProductType {
    NORMAL,
    HIGH_DEMAND
}
