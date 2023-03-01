package com.andreabonatti92.clockcompose

sealed class LineType {
    object Normal : LineType()
    object Hour : LineType()
}
