package com.thoughtworks.kotlin_basic.product.dto

import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class ProductDTO(
    val id: Int,
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val type: String,
    val image: String
)
