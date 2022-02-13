package com.elouyi.yebi.utils

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

public fun Byte.toHexString(): String {
    var hex = Integer.toHexString(this.toInt() and 0xff)
    if (hex.length < 2) hex = "0$hex"
    return hex
}

public fun ByteArray.toHexString(): String {
    return buildString {
        for (b in this@toHexString) {
            append(b.toHexString())
        }
    }
}

public fun decompress(bytes: ByteArray): ByteArray {
    val decompressor = Inflater()
    decompressor.reset()
    decompressor.setInput(bytes)
    val o = ByteArrayOutputStream(bytes.size)
    val buf = ByteArray(1024)
    try {
        while (!decompressor.finished()) {
            val i = decompressor.inflate(buf)
            o.write(buf,0,i)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        o.close()
    }
    return o.toByteArray()
}
