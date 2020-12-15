package barinalex.drawwithyourbro

import android.graphics.*
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import barinalex.drawwithyourbro.drawableObjects.pathBased.PathLine
import java.util.*

class DrawSurfaceModel : Observable{

    companion object{
        private var INSTANCE: DrawSurfaceModel? = null

        fun getInstance(): DrawSurfaceModel{
            return INSTANCE!!
        }

        fun destroy(){
            INSTANCE = null
        }

        fun getInstance(screenBorders : Point): DrawSurfaceModel{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                tempInstance.SCREENBORDERS.set(screenBorders.x, screenBorders.y)
                return tempInstance
            }
            else{
                val instance = DrawSurfaceModel(screenBorders)
                INSTANCE = instance
                return instance
            }
        }
    }

    private var currentDrawing : DrawableObject
    private var canvas: Canvas
    private lateinit var prevPoint : PointF

    var bitmap : Bitmap
    var position: PointF
    private val BACKGROUNDCOLOR = Color.BLACK
    private var SCREENBORDERS : Point

    enum class Mode{
        DRAW, MOVE
    }
    private var mode = Mode.DRAW

    constructor(screenBorders : Point)  : super(){
        position = PointF(0f,0f)
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
        clearSurface()
        position = PointF(0f,0f)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    fun clearSurface(){
        position = PointF(0f,0f)
        canvas.drawColor(BACKGROUNDCOLOR)
    }

    fun onDown(point: PointF){
        when(mode){
            Mode.DRAW -> {
                currentDrawing.clear()
                currentDrawing.initObject(Utils.substract(point, position))
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
                currentDrawing.drawObject(Utils.substract(point, position))
            }
            Mode.MOVE -> {
                position.x += point.x - prevPoint.x
                position.y += point.y - prevPoint.y
                prevPoint = PointF(point.x, point.y)
            }
        }
        currentDrawing.draw(canvas)
        notifyAllOnChange()
    }

    fun notifyAllOnChange(){
        setChanged()
        notifyObservers()
    }
}