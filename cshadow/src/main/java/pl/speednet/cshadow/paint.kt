package pl.speednet.cshadow

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val DefaultBlurRadius: Dp = 8.dp

internal fun createPaint(blur: Float, color: Color): Paint {
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()

    if (blur != 0f) {
        frameworkPaint.maskFilter = BlurMaskFilter(blur, BlurMaskFilter.Blur.NORMAL)
    }
    paint.color = color
    return paint
}
