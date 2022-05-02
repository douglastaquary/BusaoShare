package com.douglastaquary.busaoshare.model

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}