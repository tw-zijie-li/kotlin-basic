package com.thoughtworks.kotlin_basic.product

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class Inventory(
    val id: Int,
    val sku: String,
    val zone: InventoryZone,
    val quantity: Int
) {
    override fun toString(): String {
        return "Inventory(id=$id, sku='$sku', zone=$zone, quantity=$quantity)"
    }
}

enum class InventoryZone {
    US_WEST,
    EU_CENTRAL,
    CN_EAST,
    CN_SOUTH,
    US_EAST,
    CN_NORTH,
    AU_SOUTH
}