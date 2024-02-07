package me.csehviktor.plugins.property

import kotlinx.serialization.SerialName
import me.csehviktor.data.Request
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

fun KProperty<*>.serializedName(defaultValue: String) =
    (annotations.find { it is SerialName } as? SerialName)
        ?.value ?: defaultValue

fun allStringsValid(vararg args: String?): Boolean {
    args.forEach {
        return it !== null && it.isNotEmpty() && it.isNotBlank()
    }
    return false
}

fun findFieldProperties(clazz: Request): Map<String, String> {
    val map = hashMapOf<String, String>()

    clazz::class.memberProperties.forEach { field ->
        val fieldName: String = field.serializedName(field.name)
        val fieldValue: String = field.getter.call(clazz) as String

        map[fieldName] = fieldValue
    }

    return map
}