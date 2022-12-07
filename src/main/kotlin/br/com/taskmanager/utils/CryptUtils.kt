package br.com.taskmanager.utils

import java.security.MessageDigest

fun String.md5(): String {
    return hashString(this)
}

private fun hashString(input: String): String {
    return MessageDigest
            .getInstance("MD5")
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
}