package pl.speednet.cshadow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val DefaultCornerRadius: Dp = 12.dp

fun Modifier.shadow(
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    borderRadius: Dp = DefaultCornerRadius,
    blurRadius: Dp = DefaultBlurRadius,
    color: Color = DefaultShadowColor,
) = drawWithCache {
    onDrawBehind {
        drawIntoCanvas { canvas ->

            canvas.drawRoundRect(
                left = offsetX.toPx(),
                top = offsetY.toPx(),
                right = offsetX.toPx() + this.size.width,
                bottom = offsetY.toPx() + this.size.height,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = createPaint(blurRadius.toPx(), color),
            )
        }
    }
}

fun Modifier.shadow(
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    shape: Shape,
    blurRadius: Dp = DefaultBlurRadius,
    color: Color = DefaultShadowColor,
) = drawWithCache {
    onDrawBehind {
        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetX.toPx(), offsetY.toPx())
            val outline = shape.createOutline(size, layoutDirection, this)
            val paint = createPaint(blurRadius.toPx(), color)
            canvas.drawOutline(outline, paint)
            canvas.restore()
        }
    }
}

@Preview
@Composable
private fun PreviewShadowBox() = PreviewSliders { offsetX, offsetY, blurRadius ->
    Box(
        modifier = Modifier
            .padding(32.dp)
            .size(80.dp)
            .shadow(offsetY = offsetX, offsetX = offsetY, blurRadius = blurRadius)
            .background(Color.Cyan, RoundedCornerShape(DefaultCornerRadius))
    )
}

