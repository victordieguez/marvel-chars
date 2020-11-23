package com.android.marvelcharacters.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class APIInterceptor(private val publicKey: String, private val privateKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis()
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", timestamp.toString())
            .addQueryParameter("hash", generateHash(timestamp, publicKey, privateKey))
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    private fun generateHash(timestamp: Long, publicKey: String, privateKey: String): String {
        return try {
            val value = timestamp.toString() + privateKey + publicKey
            val md5Encoder = MessageDigest.getInstance("MD5")
            val md5Bytes: ByteArray = md5Encoder.digest(value.toByteArray())
            val md5 = StringBuilder()
            for (i in md5Bytes.indices) {
                md5.append(Integer.toHexString(md5Bytes[i] and 0xFF or 0x100).substring(1, 3))
            }
            md5.toString()
        } catch (e: NoSuchAlgorithmException) {
            ""
        }
    }
}
