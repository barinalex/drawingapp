package barinalex.drawwithyourbro

import android.content.Context
import android.graphics.*
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.*

class DrawSurfaceView : View, Observer{
    val drawSurfaceModel : DrawSurfaceModel
    val gestureDetector : GestureDetector

    constructor(context: Context, drawSurfaceModel: DrawSurfaceModel): super(context){
        this.drawSurfaceModel = drawSurfaceModel
        gestureDetector = GestureDetector(context, MygestureListener())
        drawSurfaceModel.addObserver(this)
    }

    inner class MygestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            drawSurfaceModel.switchMode()
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            drawSurfaceModel.centered()
            drawSurfaceModel.switchMode()
            return true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawSurfaceModel.onDown(PointF(event.x, event.y))
            }
            MotionEvent.ACTION_MOVE -> {
                drawSurfaceModel.onMove(PointF(event.x, event.y))
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(drawSurfaceModel.bitmap,
                drawSurfaceModel.position.x,
                drawSurfaceModel.position.y, null)
    }

    override fun update(o: Observable?, arg: Any?) {
        invalidate()
    }

}