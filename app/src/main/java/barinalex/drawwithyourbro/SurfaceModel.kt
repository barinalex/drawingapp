package barinalex.drawwithyourbro

import android.graphics.*
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import barinalex.drawwithyourbro.drawableObjects.pathBased.PathLine
import java.util.*

class SurfaceModel : Observable{

    private var currentDrawing : DrawableObject
    private var canvas: Canvas
    private lateinit var prevPoint : PointF

    var bitmap : Bitmap
    var bitmapCoordinates: PointF
    private val BACKGROUNDCOLOR = Color.BLACK
    private val SCREENBORDERS : Point

    val surfaceName = "basic surface"

    enum class Mode{
        DRAW, MOVE
    }
    private var mode = Mode.DRAW

    constructor(screenBorders : Point)  : super(){
        bitmapCoordinates = PointF(0f,0f)
        currentDrawing = PathLine()
        SCREENBORDERS = Point(screenBorders.x, screenBorders.y)
        bitmap = Bitmap.createBitmap(SCREENBORDERS.x, SCREENBORDERS.y, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        canvas.drawColor(BACKGROUNDCOLOR)
    }

    fun switchMode(){
        mode = when(mode) {
            Mode.MOVE -> Mode.DRAW
            Mode.DRAW -> Mode.MOVE
        }
    }

    fun drawBitmap(bitmap: Bitmap){
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    fun onDown(point: PointF){
        when(mode){
            Mode.DRAW -> {
                currentDrawing.clear()
                currentDrawing.initObject(Utils.substract(point, bitmapCoordinates))
                currentDrawing.draw(canvas)
                notifyAllOnChange()
            }
            Mode.MOVE -> {
                prevPoint = PointF(point.x, point.y)
            }
        }
    }

    fun onMove(point: PointF){
        when(mode){
            Mode.DRAW -> {
                currentDrawing.drawObject(Utils.substract(point, bitmapCoordinates))
            }
            Mode.MOVE -> {
                bitmapCoordinates.x += point.x - prevPoint.x
                bitmapCoordinates.y += point.y - prevPoint.y
                prevPoint = PointF(point.x, point.y)
            }
        }
        currentDrawing.draw(canvas)
        notifyAllOnChange()
    }

    fun onUp(){
        // save drawing to model
        when(mode){
            Mode.DRAW -> {

            }
            Mode.MOVE -> {

            }
        }
    }

    fun notifyAllOnChange(){
        setChanged()
        notifyObservers()
    }
}