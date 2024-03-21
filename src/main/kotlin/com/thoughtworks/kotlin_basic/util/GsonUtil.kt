package com.thoughtworks.kotlin_basic.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author zijie.li
 * @since 2024/3/21
 */
object GsonUtil {

    val GSON_INSTANCE: Gson by lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    fun toJson(obj: Any): String {
        return GSON_INSTANCE.toJson(obj)
    }

}