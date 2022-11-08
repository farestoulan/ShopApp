package com.example.Bank.uiApp

fun <T> filter(list: List<T>?, predicate: (T) -> Boolean): List<T> {
    if (list.isNullOrEmpty()) return emptyList()
    return list.filter(predicate)
}