package com.thoughtworks.kotlin_basic.product.service

import com.thoughtworks.kotlin_basic.product.Inventory
import com.thoughtworks.kotlin_basic.product.InventoryZone
import com.thoughtworks.kotlin_basic.product.Product
import com.thoughtworks.kotlin_basic.product.ProductType
import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/20
 */

fun buildProduct(
    sku: String,
    price: BigDecimal,
    type: ProductType,
) = Product(
    id = 1,
    sku = sku,
    name = "name",
    price = price,
    type = type,
    image = "xxx.png"
)

fun buildInventory(sku: String, quantity: Int) = Inventory(
    id = 1,
    sku = sku,
    zone = InventoryZone.CN_EAST,
    quantity = quantity
)
