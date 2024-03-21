package com.thoughtworks.kotlin_basic.product.dto

import com.google.gson.annotations.SerializedName

/**
 * @author zijie.li
 * @since 2024/3/20
 */
data class InventoryDTO(
    val id: Int,
    @SerializedName("SKU")
    val sku: String,
    val zone: String,
    val quantity: Int
)