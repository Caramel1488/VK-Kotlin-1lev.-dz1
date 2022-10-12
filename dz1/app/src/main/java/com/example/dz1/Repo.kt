package com.example.dz1

import android.content.Context
import android.content.res.Configuration

class Repo(context: Context) {
    private var number = if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
        3
    }else{
        4
    }


}