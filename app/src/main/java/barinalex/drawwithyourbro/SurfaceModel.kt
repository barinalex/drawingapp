package barinalex.drawwithyourbro

import android.graphics.Canvas
import android.graphics.PointF
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import barinalex.drawwithyourbro.drawableObjects.pathBased.PathLine
import java.util.*

class SurfaceModel : Observable{

    //val drawableObjects : Stack<DrawableObject>
    private var currentDrawing : DrawableObject
    var bitmapCoordinates: PointF
    private lateinit var prevPoint : PointF
    enum class Mode{
        DRAW, MOVE
    }
    private var mode = Mode.DRAW

    constructor() : super(){
        //drawableObjects = Stack()
        bitmapCoordinates = PointF(0f,0f)
        currentDrawing = PathLine()
    }

    fun switchMode(){
        mode = when(mode) {
            Mode.MOVE -> Mode.DRAW
            Mode.DRAW -> Mode.MOVE
        }
    }

    fun onDown(point: PointF){
        when(mode){
            Mode.DRAW -> {
                currentDrawing.clear()
                point.x -= bitmapCoordinates.x
                point.y -= bitmapCoordinates.y
                currentDrawing.initObject(point)
                //drawableObjects.push(currentDrawing)
                notifyAllOnChange()
            }
            Mode.MOVE -> {
                prevPoint = point
            }
        }
    }

    fun onMove(point: PointF){
        when(mode){
            Mode.DRAW -> {
                point.x -= bitmapCoordinates.x
                point.y -= bitmapCoordinates.y
                currentDrawing.drawObject(point)
            }
            Mode.MOVE -> {
                //val offset = PointF(point.x - prevPoint.x, point.y - prevPoint.y)
                bitmapCoordinates.x += point.x - prevPoint.x
                bitmapCoordinates.y += point.y - prevPoint.y
                /*
                for (drawableObject in drawableObjects){
                    drawableObject.moveObject(offset)
                }
                */
                prevPoint = point
            }
        }
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

    fun onDraw(canvas: Canvas){
        currentDrawing.draw(canvas)
        /*
        for (drawableObject in drawableObjects){
            drawableObject.draw(canvas)
        }
        */
    }

    fun notifyAllOnChange(){
        setChanged()
        notifyObservers()
    }
}