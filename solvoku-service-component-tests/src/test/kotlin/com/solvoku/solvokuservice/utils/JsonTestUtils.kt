package com.solvoku.solvokuservice.utils

object JsonTestUtils {
    fun loadJsonFromResources(filename: String): String {
        val resource = JsonTestUtils::class.java.classLoader.getResource("json/$filename")
            ?: throw IllegalArgumentException("JSON resource not found in: json/$filename")

        return resource.readText()
    }
}
