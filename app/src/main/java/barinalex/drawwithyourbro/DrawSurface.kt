package barinalex.drawwithyourbro

import android.content.Context
import android.graphics.*
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.util.*

class DrawSurface : View, Observer{
    val model : SurfaceModel
    lateinit var bitmap :Bitmap
    lateinit var extraCanvas: Canvas
    val gestureDetector : GestureDetector
    val screenBorders : Point

    constructor(context: Context, model: SurfaceModel): super(context){
        this.model = model

        val displayMetrics = context.resources.displayMetrics
        screenBorders = Point(displayMetrics.widthPixels,displayMetrics.heightPixels)
        this.minimumWidth = screenBorders.x
        this.minimumHeight = screenBorders.y

        gestureDetector = GestureDetector(context, MygestureListener())
        model.addObserver(this)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::bitmap.isInitialized)
            bitmap.recycle()
        bitmap = Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(bitmap)
        extraCanvas.drawColor(Color.BLACK)
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
        //extraCanvas.drawColor(Color.BLACK)
        model.onDraw(extraCanvas)
        canvas.drawBitmap(bitmap, model.bitmapCoordinates.x, model.bitmapCoordinates.y, null)
    }

    override fun update(o: Observable?, arg: Any?) {
        invalidate()
    }

}