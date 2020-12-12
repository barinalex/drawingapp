package barinalex.drawwithyourbro.drawableObjects.pathBased

import android.graphics.Color
import android.graphics.Paint

class PathLine : PathBased {
    constructor() : super(){
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }
}