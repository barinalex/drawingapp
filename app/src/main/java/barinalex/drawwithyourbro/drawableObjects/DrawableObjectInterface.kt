package barinalex.drawwithyourbro.drawableObjects

import android.graphics.Canvas
import android.graphics.PointF

interface DrawableObjectInterface {
    fun initObject(point: PointF)
    fun drawObject(point: PointF)
    fun moveObject(point: PointF)
    fun draw(canvas: Canvas)
    fun clear()
}