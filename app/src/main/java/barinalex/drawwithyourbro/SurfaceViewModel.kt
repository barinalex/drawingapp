package barinalex.drawwithyourbro

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.PointF
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import barinalex.drawwithyourbro.data.Drawing
import barinalex.drawwithyourbro.data.DrawingDatabase
import barinalex.drawwithyourbro.data.DrawingRepository
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import barinalex.drawwithyourbro.drawableObjects.pathBased.PathLine
import barinalex.drawwithyourbro.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SurfaceViewModel(application: Application): AndroidViewModel(application) {
    private val app: Application
    private val repository: DrawingRepository
    val surfaceLive: MutableLiveData<Surface>
    val surface: Surface
    private var drawing : DrawableObject
    private var canvas: Canvas
    private lateinit var prevPoint : PointF

    enum class Mode{
        DRAW, MOVE
    }
    private var mode = Mode.DRAW

    init {
        app = application
        val drawingDao = DrawingDatabase.getDatabase(application).drawingDao()
        repository = DrawingRepository(drawingDao)
        surface = Surface()
        surfaceLive = MutableLiveData()
        drawing = PathLine()
        canvas = Canvas(surface.bitmap)
        canvas.drawColor(surface.backgroundColor)
        notifyOnChange()
    }

    fun centered(){
        surface.position.set(0f, 0f)
        notifyOnChange()
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
                drawing.clear()
                drawing.initObject(Utils.substract(point, surface.position))
                drawing.draw(canvas)
                surfaceLive.value = surface
            }
            Mode.MOVE -> {
                prevPoint = PointF(point.x, point.y)
            }
        }
    }

    fun onMove(point: PointF){
        when(mode){
            Mode.DRAW -> {
                drawing.drawObject(Utils.substract(point, surface.position))
            }
            Mode.MOVE -> {
                surface.position.x += point.x - prevPoint.x
                surface.position.y += point.y - prevPoint.y
                prevPoint = PointF(point.x, point.y)
            }
        }
        drawing.draw(canvas)
        notifyOnChange()
    }

    fun create(size: Point){
        surface.position.set(0f, 0f)
        surface.bitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(surface.bitmap)
        canvas.drawColor(surface.backgroundColor)
        notifyOnChange()
    }

    fun save(drawing: Drawing){
        viewModelScope.launch(Dispatchers.IO) {
            val file = File(app.filesDir, drawing.name)
            Utils.bitmapToFile(surface.bitmap, file)
            repository.addDrawing(drawing)
        }
    }

    fun setBitmap(bitmap: Bitmap){

        create(Point(bitmap.width, bitmap.height))
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        notifyOnChange()
    }

    fun load(drawing: Drawing){
        val file = File(app.filesDir, drawing.name)
        val bm = Utils.fileToBitmap(file)
        setBitmap(bm)
    }

    fun notifyOnChange(){
        surfaceLive.value = surface
    }
}