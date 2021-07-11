package com.liauli.trysqldelight

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}