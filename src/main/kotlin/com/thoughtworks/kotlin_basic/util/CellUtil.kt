package com.thoughtworks.kotlin_basic.util

/**
 * @author zijie.li
 * @since 2024/3/19
 */
object CellUtil {

    private const val START_CHAR_CODE = 'A'.code
    private const val BASE_26 = 26

    fun getRowRange(range: CellRange): List<String> {
        val result = arrayListOf<String>()
        for (n in range.start until range.start + range.count) {
            result.add(base10ToBase26(n))
        }
        return result
    }

    fun base10ToBase26(num: Int): String {
        val s = StringBuilder()
        var a = num
        while (a > 0) {
            a--
            s.insert(0, Char(a % BASE_26 + START_CHAR_CODE).toString())
            a /= BASE_26
        }
        return s.toString()
    }
}


data class CellRange(
    val start: Int,
    val count: Int
) {

    init {
        checkRangeParam(start, count)
    }

    private fun checkRangeParam(start: Int, count: Int) {
        if (start !in MIN_RANGE..MAX_RANGE) {
            throw IllegalArgumentException("Range start number out of range (${MIN_RANGE},${MAX_RANGE})")
        }
        if (count < MIN_RANGE) {
            throw IllegalArgumentException("Range count number less than $MIN_RANGE")
        }
        if (start + count - 1 > MAX_RANGE) {
            throw IllegalArgumentException("Range total greater than $MAX_RANGE")
        }
    }

    companion object {
        private const val MAX_RANGE = 18278 // max up to ZZZ = 26*26^2 + 26*26^1 + 26*26^0
        private const val MIN_RANGE = 1
    }

}
