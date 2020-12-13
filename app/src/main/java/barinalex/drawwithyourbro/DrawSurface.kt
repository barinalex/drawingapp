package barinalex.drawwithyourbro

import android.content.Context
import android.graphics.*
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.*

class DrawSurface : View, Observer{
    val model : SurfaceModel
    val gestureDetector : GestureDetector

    constructor(context: Context, model: SurfaceModel): super(context){
        this.model = model
        gestureDetector = GestureDetector(context, MygestureListener())
        model.addObserver(this)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
    }

    inner class MygestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            model.switchMode()
            return true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                model.onDown(PointF(event.x, event.y))
            }
            MotionEvent.ACTION_MOVE -> {
                model.onMove(PointF(event.x, event.y))
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(model.bitmap, model.bitmapCoordinates.x, model.bitmapCoordinates.y, null)
    }

    override fun update(o: Observable?, arg: Any?) {
        invalidate()
    }

}