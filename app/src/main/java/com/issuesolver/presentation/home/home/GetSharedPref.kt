package com.issuesolver.presentation.home.home

import android.content.Context

fun getFilterPreferences(context: Context): Map<String, String?> {
    val sharedPreferences = context.getSharedPreferences("filter_prefs", Context.MODE_PRIVATE)
    return mapOf(
        "categoryName" to sharedPreferences.getString("categoryName", ""),
        "organizationName" to sharedPreferences.getString("organizationName", ""),
        "days" to sharedPreferences.getString("days", ""),
        "status" to sharedPreferences.getString("status", "")
    )
}


fun clearFilterPreferences(context: Context) {
    val filterPrefs = context.getSharedPreferences("filter_prefs", Context.MODE_PRIVATE)
    with(filterPrefs.edit()) {
        clear()
        apply()
    }
}