package com.thoughtworks.kotlin_basic.product.dto

/**
 * @author zijie.li
 * @since 2024/3/20
 */
data class InventoryDTO(
    val id: Int,
    val sku: String,
    val zone: String,
    val quantity: Int
)