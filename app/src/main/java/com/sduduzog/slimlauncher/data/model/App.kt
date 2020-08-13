package com.sduduzog.slimlauncher.data.model

import com.sduduzog.slimlauncher.models.HomeApp

data class App(
        val appName: String,
        val packageName: String,
        val activityName: String
){
    companion object{
        fun from(homeApp: HomeApp) = App(homeApp.appName, homeApp.packageName, homeApp.activityName)
    }
}