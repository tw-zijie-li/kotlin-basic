package com.thoughtworks.kotlin_basic.product.adapter

import com.thoughtworks.kotlin_basic.product.dto.InventoryDTO
import com.thoughtworks.kotlin_basic.product.dto.ProductDTO
import com.thoughtworks.kotlin_basic.util.GsonUtil
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.reflect.KClass

/**
 * @author zijie.li
 * @since 2024/3/21
 */

object HttpClient {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(GsonConverterFactory.create(GsonUtil.GSON_INSTANCE))
            .build()
    }

    inline fun <reified T : Any> getService(kClass: KClass<T>): T {
        return retrofit.create(kClass.java)
    }

}

interface ExternalJsonService {

    @GET("/products")
    fun listProducts(): Call<List<ProductDTO>>

    @GET("/inventories")
    fun listInventories(): Call<List<InventoryDTO>>

}
