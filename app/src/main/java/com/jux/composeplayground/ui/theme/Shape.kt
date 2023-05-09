package com.jux.composeplayground.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
    large = RoundedCornerShape(0.dp)
)

val shapeCircle = RoundedCornerShape(50.dp)