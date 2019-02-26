package pe.devpicon.android.mytestinglabapp.roundedimage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView


class RoundedImageView : ImageView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    @SuppressLint("DrawAllocation")
    protected override fun onDraw(canvas: Canvas) {
        //float radius = 36.0f;
        val clipPath = Path()
        val rect = RectF(0f, 0f, this@RoundedImageView.width * 1f, this@RoundedImageView.height * 1f)
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    companion object {
        var radius = 18.0f
    }
}