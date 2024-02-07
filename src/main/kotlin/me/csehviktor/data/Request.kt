package me.csehviktor.data

import io.ktor.http.*
import io.ktor.util.*
import me.csehviktor.plugins.property.findFieldProperties

interface Request {
    fun build(builder: StringValuesBuilder): StringValuesBuilder {
        val properties: Map<String, String> = findFieldProperties(this)

        properties.forEach {
            builder.append(it.key, it.value)
        }

        return builder
    }

    fun build(): Parameters {
        return build(ParametersBuilder()).build() as Parameters
    }
}