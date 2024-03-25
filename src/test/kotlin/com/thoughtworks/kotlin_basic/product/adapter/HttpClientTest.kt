package com.thoughtworks.kotlin_basic.product.adapter

import com.thoughtworks.kotlin_basic.product.datatransferobject.InventoryDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/**
 * @author zijie.li
 * @since 2024/3/21
 */
internal class HttpClientTest {

    @Disabled("depends on external api service") //
    @Test
    fun `should return product data object given json api `() {
        // given
        val api = HttpClient.getService(ExternalProductApi::class)
        // when
        val result = api.listProducts().execute()
        //then
        assertThat(result.body()).isNotEmpty
        val map = result.body()!!.associateBy { it.sku }
        assertThat(map.keys).contains("ABC123", "DEF456", "GHI789")
        with(map["ABC123"]!!) {
            assertThat(sku).isEqualTo("ABC123")
            assertThat(name).isEqualTo("Electronic Watch")
            assertThat(price).isEqualTo(BigDecimal("299.99"))
            assertThat(type).isEqualTo("NORMAL")
            assertThat(image).isEqualTo("image1.jpg")
        }
    }

    @Disabled("depends on external api service")
    @Test
    fun `should return inventory data object given json api `() {
        // given
        val api = HttpClient.getService(ExternalInventoryApi::class)
        // when
        val result = api.listInventories().execute()
        // then
        assertThat(result.body()).isNotEmpty
        val map = result.body()!!.groupBy { it.sku }
        assertThat(map.keys).contains("ABC123", "DEF456", "GHI789")
        val list = map["ABC123"]!!.sortedBy { it.id }
        assertThat(list).contains(
            InventoryDTO(1, "ABC123", "CN_NORTH", 120),
            InventoryDTO(2, "ABC123", "US_WEST", 80)
        )
    }

}