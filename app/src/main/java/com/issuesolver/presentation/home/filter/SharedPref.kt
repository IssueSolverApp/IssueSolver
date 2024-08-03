package com.issuesolver.presentation.home.filter

import android.content.Context
import android.content.SharedPreferences

fun saveFilterPreferences(context: Context, categoryName: String, organizationName: String, days: String, status: String) {
    val sharedPreferences = context.getSharedPreferences("filter_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("categoryName", categoryName)
        putString("organizationName", organizationName)
        putString("days", days)
        putString("status", status)
        apply() // Асинхронное сохранение
    }
}