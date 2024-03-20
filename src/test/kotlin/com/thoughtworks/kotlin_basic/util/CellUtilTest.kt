package com.thoughtworks.kotlin_basic.util

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import kotlin.test.assertFailsWith

/**
 * @author zijie.li
 * @since 2024/3/19
 */
internal class CellUtilTest {

    @Test
    fun `should return A-Z string given decimal number`() {
        // given
        val numbers = listOf(1, 26, 27, 16384, 18278)
        // when
        val result = numbers.map(CellUtil::base10ToBase26).toList()
        // then
        Assertions.assertThat(result).isEqualTo(listOf("A", "Z", "AA", "XFD","ZZZ"))
    }


    @Test
    fun `should return single cell address given range 1`() {
        // given
        val range = CellRange(30, 1)
        // when
        val result = CellUtil.getRowRange(range)
        // then
        Assertions.assertThat(result).isEqualTo(listOf("AD"))
    }


    @Test
    fun `should return multiple cell address given range gt 1`() {
        // given
        val range = CellRange(25, 3)
        // when
        val result = CellUtil.getRowRange(range)
        // then
        Assertions.assertThat(result).isEqualTo(listOf("Y", "Z", "AA"))
    }


    @Test
    fun `should return multiple cell address given bigger range`() {
        // given
        val range = CellRange(16382, 3)
        // when
        val result = CellUtil.getRowRange(range)
        // then
        Assertions.assertThat(result).isEqualTo(listOf("XFB", "XFC", "XFD"))
    }

    @Test
    fun `should return ZZZ given range (18278,1)`() {
        //given
        val range = CellRange(18278, 1)
        //when
        val result = CellUtil.getRowRange(range)
        //then
        Assertions.assertThat(result).isEqualTo(listOf("ZZZ"))
    }

    @Test
    fun `should throw error given total range gt 18278`() {
        // when
        assertFailsWith<IllegalArgumentException> {
            val range = CellRange(2, 18278)
            CellUtil.getRowRange(range)
        }
    }

    @Test
    fun `should throw error given range start lt 1`() {
        // when
        assertFailsWith<IllegalArgumentException> {
            val range = CellRange(0, 1)
            CellUtil.getRowRange(range)
        }
    }

    @Test
    fun `should throw error given range count lt 1`() {
        // when
        assertFailsWith<IllegalArgumentException> {
            val range = CellRange(1, 0)
            CellUtil.getRowRange(range)
        }
    }


}