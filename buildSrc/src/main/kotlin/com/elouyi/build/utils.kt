package com.elouyi.build

import java.io.File
import java.util.*

fun kotlinx(id: String, version: String): String {
    return "org.jetbrains.kotlinx:kotlinx-$id:$version"
}


fun decryptBase64(base64: String): String {
    println("generate ring file")
    val fileName = "C:/secring.gpg"
    val file = File(fileName)
    file.createNewFile()
    val b = Base64.getDecoder().decode(base64)
    file.outputStream().use {
        it.write(b)
    }
    return file.absolutePath
}