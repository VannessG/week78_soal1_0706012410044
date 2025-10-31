package com.example.week78_0706012410044

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.week78_0706012410044.ui.theme.Week78_0706012410044Theme
import com.example.week78_0706012410044.ui.view.ViewWeather

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week78_0706012410044Theme {
                ViewWeather()
            }
        }
    }
}