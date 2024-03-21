package com.thoughtworks.kotlin_basic.product.service

import com.thoughtworks.kotlin_basic.product.ProductType
import com.thoughtworks.kotlin_basic.product.adapter.InventoryAdapter
import com.thoughtworks.kotlin_basic.product.adapter.ProductAdapter
import com.thoughtworks.kotlin_basic.product.vo.ProductWithInventoryVO
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/20
 */
@ExtendWith(MockKExtension::class)
internal class ItemQueryServiceTest {

    @MockK
    lateinit var productAdapter: ProductAdapter

    @MockK
    lateinit var inventoryAdapter: InventoryAdapter

    @InjectMockKs
    lateinit var queryService: ItemQueryService

    @Test
    fun `should return aggregate inventory given products and inventories`() {
        //given
        every { productAdapter.listProduct() } returns listOf(
            buildProduct("sku-1", BigDecimal("100"), ProductType.NORMAL),
            buildProduct("sku-2", BigDecimal("100"), ProductType.NORMAL),
            buildProduct("sku-3", BigDecimal("100"), ProductType.HIGH_DEMAND),
        )
        every { inventoryAdapter.listInventory() } returns listOf(
            buildInventory("sku-1", quantity = 10),
            buildInventory("sku-1", quantity = 20),
            buildInventory("sku-2", quantity = 10),
            buildInventory("sku-3", quantity = 10)
        )
        //when
        val result = queryService.listAggregateProductInfo()

        //then
        assertThat(result.size).isEqualTo(3)

        val skuMap: Map<String, ProductWithInventoryVO> = result.associateBy { it.sku }
        assertThat(skuMap.keys).containsAll(listOf("sku-1", "sku-2", "sku-3"))
        with(skuMap["sku-1"]!!) {
            assertThat(inventory).isEqualTo(30)
            assertThat(sku).isEqualTo("sku-1")
            assertThat(price).isEqualTo(BigDecimal("100.0000"))
        }

        with(skuMap["sku-3"]!!) {
            assertThat(inventory).isEqualTo(10)
            assertThat(sku).isEqualTo("sku-3")
            assertThat(price).isEqualTo(BigDecimal("150.0000"))
        }
    }

    @Test
    fun `should return zero given products without inventory`() {
        //given
        every { productAdapter.listProduct() } returns listOf(
            buildProduct("sku-1", BigDecimal("100"), ProductType.NORMAL),
            buildProduct("sku-2", BigDecimal("100"), ProductType.NORMAL),
            buildProduct("sku-3", BigDecimal("100"), ProductType.HIGH_DEMAND),
        )
        every { inventoryAdapter.listInventory() } returns listOf(
            buildInventory("sku-1", quantity = 10),
        )
        //when
        val result = queryService.listAggregateProductInfo()

        //then
        assertThat(result.size).isEqualTo(3)

        val skuMap: Map<String, ProductWithInventoryVO> = result.associateBy { it.sku }
        assertThat(skuMap.keys).containsAll(listOf("sku-1", "sku-2", "sku-3"))
        with(skuMap["sku-1"]!!) {
            assertThat(inventory).isEqualTo(10)
            assertThat(sku).isEqualTo("sku-1")
            assertThat(price).isEqualTo(BigDecimal("100.0000"))
        }

        with(skuMap["sku-3"]!!) {
            assertThat(sku).isEqualTo("sku-3")
            assertThat(inventory).isEqualTo(0)
            assertThat(price).isEqualTo(BigDecimal("150.0000"))
        }
    }


    @Test
    fun `should prices be dynamic depend on inventory`() {

        //given
        every { productAdapter.listProduct() } returns listOf(
            buildProduct("sku-1", BigDecimal("100"), ProductType.HIGH_DEMAND),
            buildProduct("sku-2", BigDecimal("100"), ProductType.HIGH_DEMAND),
            buildProduct("sku-3", BigDecimal("100"), ProductType.HIGH_DEMAND),
        )
        every { inventoryAdapter.listInventory() } returns listOf(
            buildInventory("sku-1", quantity = 10),
            buildInventory("sku-2", quantity = 31),
            buildInventory("sku-3", quantity = 101),
        )
        //when
        val result = queryService.listAggregateProductInfo()

        //then
        assertThat(result.size).isEqualTo(3)

        val skuMap: Map<String, ProductWithInventoryVO> = result.associateBy { it.sku }
        assertThat(skuMap.keys).containsAll(listOf("sku-1", "sku-2", "sku-3"))

        with(skuMap["sku-1"]!!) {
            assertThat(sku).isEqualTo("sku-1")
            assertThat(inventory).isEqualTo(10)
            assertThat(price).isEqualTo(BigDecimal("150.0000"))
        }

        with(skuMap["sku-2"]!!) {
            assertThat(sku).isEqualTo("sku-2")
            assertThat(inventory).isEqualTo(31)
            assertThat(price).isEqualTo(BigDecimal("120.0000"))
        }

        with(skuMap["sku-3"]!!) {
            assertThat(sku).isEqualTo("sku-3")
            assertThat(inventory).isEqualTo(101)
            assertThat(price).isEqualTo(BigDecimal("100.0000"))
        }

    }

}