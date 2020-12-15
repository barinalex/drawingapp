package barinalex.drawwithyourbro

import android.graphics.*
import androidx.core.graphics.set
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import barinalex.drawwithyourbro.drawableObjects.pathBased.PathLine
import java.util.*

class DrawSurfaceModel : Observable{

    companion object{
        private var INSTANCE: DrawSurfaceModel? = null

        fun getInstance(size : Point? = null): DrawSurfaceModel{
            val tempInstance = INSTANCE
            return if (tempInstance != null){
                tempInstance
            }
            else{
                val instance = if (size != null) DrawSurfaceModel(size) else DrawSurfaceModel()
                INSTANCE = instance
                instance
            }
        }

        fun destroy(){ INSTANCE = null }
    }

    private val doStack = Stack<Bitmap>()
    private val undoStack = Stack<Bitmap>()
    private val MAXSTACKSIZE = 10

    private var drawing : DrawableObject
    private var canvas: Canvas
    private lateinit var prevPoint : PointF

    lateinit var bitmap : Bitmap
    var position: PointF
    var size : Point
    private var backgroundColor : Int

    enum class DrawMode{
        PATHLINE
    }
    private var drawMode = DrawMode.PATHLINE

    enum class Mode{
        DRAW, MOVE
    }
    private var mode = Mode.DRAW

    constructor(size : Point = Point(1000,1000),
                drawing : DrawableObject = PathLine(),
                backgroundColor: Int = Color.BLACK)  : super(){
        this.size = Point(size.x, size.y)
        this.drawing = drawing
        this.backgroundColor = backgroundColor
        position = PointF(0f,0f)
        canvas = Canvas()
        newSurface()
    }

    fun newSurface(size: Point = this.size){
        mode = Mode.DRAW
        bitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        canvas.drawColor(backgroundColor)
        notifyAllOnChange()
    }

    fun centered(){
        position.set(0f, 0f)
        notifyAllOnChange()
    }

    fun switchMode(){
        mode = when(mode) {
            Mode.MOVE -> Mode.DRAW
            Mode.DRAW -> Mode.MOVE
        }
    }

    fun loadBitmap(bitmap: Bitmap){
        newSurface(Point(bitmap.width, bitmap.height))
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        notifyAllOnChange()
    }

    fun onDown(point: PointF){
        when(mode){
            Mode.DRAW -> {
                drawing.clear()
                drawing.initObject(Utils.substract(point, position))
                drawing.draw(canvas)
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
                drawing.drawObject(Utils.substract(point, position))
            }
            Mode.MOVE -> {
                position.x += point.x - prevPoint.x
                position.y += point.y - prevPoint.y
                prevPoint = PointF(point.x, point.y)
            }
        }
        drawing.draw(canvas)
        notifyAllOnChange()
    }

    fun notifyAllOnChange(){
        setChanged()
        notifyObservers()
    }
}