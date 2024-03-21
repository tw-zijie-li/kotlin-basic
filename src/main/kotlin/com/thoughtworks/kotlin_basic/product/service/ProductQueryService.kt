package com.thoughtworks.kotlin_basic.product.service

import com.thoughtworks.kotlin_basic.product.Product
import com.thoughtworks.kotlin_basic.product.ProductType
import com.thoughtworks.kotlin_basic.product.adapter.InventoryAdapter
import com.thoughtworks.kotlin_basic.product.adapter.ProductAdapter
import com.thoughtworks.kotlin_basic.product.model.Inventory
import com.thoughtworks.kotlin_basic.product.vo.ProductWithInventoryVO
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author zijie.li
 * @since 2024/3/20
 */
class ProductQueryService(
    private val productAdapter: ProductAdapter,
    private val inventoryAdapter: InventoryAdapter
) {

    fun listAggregateProductInfo(): List<ProductWithInventoryVO> {
        //TODO use coroutine
        val products = productAdapter.listProduct()
        val skuToInventories: Map<String, List<Inventory>> = inventoryAdapter.listInventory().groupBy(Inventory::sku)
        return products.map { product ->
            val inventories = skuToInventories.getOrDefault(product.sku, listOf())
            val totalQuantity = inventories.sumOf(Inventory::quantity)
            val salesPrice = resolveDynamicPrice(product, totalQuantity)
            ProductWithInventoryVO(
                id = product.id,
                sku = product.sku,
                name = product.name,
                image = product.image,
                price = salesPrice.setScale(
                    4,
                    RoundingMode.HALF_EVEN
                ), // TODO should define what's the rounding mode and precision
                inventory = totalQuantity
            )
        }
    }

    private fun resolveDynamicPrice(product: Product, inventoryQuantity: Int): BigDecimal {
        return when (product.type) {
            ProductType.HIGH_DEMAND -> {
                return when (inventoryQuantity) {
                    in 0..30 -> BigDecimal("1.5").multiply(product.price)
                    in 31..100 -> BigDecimal("1.2").multiply(product.price)
                    else -> product.price
                }
            }
            ProductType.NORMAL -> product.price
        }
    }


}