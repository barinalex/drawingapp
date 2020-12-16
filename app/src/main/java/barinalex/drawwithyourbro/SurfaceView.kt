package barinalex.drawwithyourbro

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class SurfaceView : View{
    val surfaceViewModel: SurfaceViewModel
    val gestureDetector : GestureDetector
    lateinit var surface: Surface

    constructor(context: Context, surfaceViewModel: SurfaceViewModel): super(context){
        this.surfaceViewModel = surfaceViewModel
        gestureDetector = GestureDetector(context, MygestureListener())
        surfaceViewModel.surfaceLive.observeForever({
            surface -> this.surface = surface
            invalidate()
        })
    }

    inner class MygestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            surfaceViewModel.switchMode()
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            surfaceViewModel.centered()
            surfaceViewModel.switchMode()
            return true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                surfaceViewModel.onDown(PointF(event.x, event.y))
                //Toast.makeText(context, "down", Toast.LENGTH_LONG).show()
            }
            MotionEvent.ACTION_MOVE -> {
                surfaceViewModel.onMove(PointF(event.x, event.y))
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(surface.bitmap, surface.position.x, surface.position.y, null)
    }

}