package com.andreabonatti92.clockcompose

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    style: ClockStyle = ClockStyle(),
    seconds: Float = 0f,
    minutes: Float = 0f,
    hours: Float = 0f,
) {
    Canvas(modifier = Modifier.size(style.radius * 2f)) {
        drawContext.canvas.nativeCanvas.apply {

            val radiusInPx = style.radius.toPx()

            drawCircle(
                center.x,
                center.y,
                radiusInPx,
                Paint().apply {
                    color = Color.WHITE
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )

            // Lines
            for (i in 0..59) {
                // 360 / 60 = 2PI / n°minutes = base unit of the cake
                val angleInRad = i * (360f / 60f) * (PI.toFloat() / 180f)
                val lineType = when {
                    i % 5 == 0 -> LineType.Hour
                    else -> LineType.Normal
                }
                val lineLength = when (lineType) {
                    LineType.Hour -> style.hourLineLength.toPx()
                    LineType.Normal -> style.normalLineLength.toPx()
                }
                val strokeWidth = when (lineType) {
                    LineType.Hour -> style.hourLineWidth.toPx()
                    LineType.Normal -> style.normalLineWidth.toPx()
                }
                val color = when (lineType) {
                    LineType.Hour -> style.hourLineColor
                    LineType.Normal -> style.normalLineColor
                }

                val lineStart = Offset(
                    x = radiusInPx * cos(angleInRad) + center.x,
                    y = radiusInPx * sin(angleInRad) + center.y,
                )

                val lineEnd = Offset(
                    x = (radiusInPx - lineLength) * cos(angleInRad) + center.x,
                    y = (radiusInPx - lineLength) * sin(angleInRad) + center.y,
                )

                drawLine(
                    color = color,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = strokeWidth
                )
            }

            // Seconds
            rotate(degrees = seconds * (360f / 60f)) {
                drawLine(
                    color = style.secondHandColor,
                    start = center,
                    end = Offset(center.x, style.secondHandLength.toPx()),
                    strokeWidth = style.secondHandWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
            // Minutes
            rotate(degrees = minutes * (360f / 60f)) {
                drawLine(
                    color = style.minuteHandColor,
                    start = center,
                    end = Offset(center.x, style.minuteHandLength.toPx()),
                    strokeWidth = style.minuteHandWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
            // Hours
            rotate(degrees = hours * (360f / 12f)) {
                drawLine(
                    color = style.hourHandColor,
                    start = center,
                    end = Offset(center.x, style.hourHandLength.toPx()),
                    strokeWidth = style.hourHandWidth.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}
