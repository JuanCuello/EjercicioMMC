package com.todoware.ejerciciomeli.utils

import com.google.gson.annotations.SerializedName

// Let enums to define it's serialize name per item
val Enum<*>?.serializedName: String?
    get() {
        return this?.javaClass?.getField(this.name)
            ?.getAnnotation(SerializedName::class.java)?.value
    }
