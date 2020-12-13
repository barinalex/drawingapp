package barinalex.drawwithyourbro.drawableObjects.pathBased

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import barinalex.drawwithyourbro.drawableObjects.DrawableObject

open class PathBased : DrawableObject{
    protected val path : Path
    protected val paint : Paint

    constructor(){
        path = Path()
        paint = Paint()
    }

    override fun initObject(point : PointF){
        path.moveTo(point.x, point.y)
    }

    override fun drawObject(point: PointF){
        path.lineTo(point.x, point.y)
    }

    override fun moveObject(point: PointF){
        path.offset(point.x, point.y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun clear(){
        path.reset()
    }
}