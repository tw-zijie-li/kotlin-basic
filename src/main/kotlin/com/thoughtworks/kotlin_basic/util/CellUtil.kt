package com.thoughtworks.kotlin_basic.util

/**
 * @author zijie.li
 * @since 2024/3/19
 */
object CellUtil {

    fun getRowRange(range: CellRange): List<String> {
        val result = arrayListOf<String>()

        for (n in range.start until range.start + range.range) {
            result.add(decTo26(n))
        }
        return result
    }

    fun decTo26(num: Int): String {
        val s = StringBuilder()
        var a = num
        while (a > 0) {
            a--
            s.insert(0, Char(a % 26 + 65).toString())
            a /= 26
        }
        return s.toString()
    }
}


data class CellRange(
    val start: Int,
    val range: Int
) {

    init {
        checkRangeParam(start, range)
    }

    private fun checkRangeParam(start: Int, range: Int) {
        if (start !in MIN_RANGE..MAX_RANGE) {
            throw IllegalArgumentException("Range start number out of range (${MIN_RANGE},${MAX_RANGE})")
        }
        if (range < MIN_RANGE) {
            throw IllegalArgumentException("Range count number less than $MIN_RANGE")
        }
        if (start + range - 1 > MAX_RANGE) {
            throw IllegalArgumentException("Range total greater than $MAX_RANGE")
        }
    }

    companion object {
        private const val MAX_RANGE = 17576
        private const val MIN_RANGE = 1
    }

}
