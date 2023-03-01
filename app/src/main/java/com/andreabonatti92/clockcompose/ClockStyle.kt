package com.andreabonatti92.clockcompose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val radius: Dp = 100.dp,
    val normalLineColor: Color = Color.Gray,
    val normalLineLength: Dp = 15.dp,
    val normalLineWidth: Dp = 0.5.dp,
    val hourLineColor: Color = Color.Black,
    val hourLineLength: Dp = 20.dp,
    val hourLineWidth: Dp = 1.dp,
    val secondHandColor: Color = Color.Red,
    val secondHandLength: Dp = 20.dp,
    val secondHandWidth: Dp = 2.dp,
    val minuteHandColor: Color = Color.Black,
    val minuteHandLength: Dp = 20.dp,
    val minuteHandWidth: Dp = 3.dp,
    val hourHandColor: Color = Color.Black,
    val hourHandLength: Dp = 35.dp,
    val hourHandWidth: Dp = 4.dp,
)
