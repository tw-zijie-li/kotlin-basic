package com.thoughtworks.kotlin_basic.product.vo

import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/20
 */
data class ProductWithInventoryVO(
    val id: Int,
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val image: String,
    val inventory: Int
)

data class InventoryVO(
    val id: Int,
    val sku: String,
    val quantity: Int
)

