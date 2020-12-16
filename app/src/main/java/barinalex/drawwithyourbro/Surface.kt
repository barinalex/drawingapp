package barinalex.drawwithyourbro

import android.graphics.*

class Surface {
    var bitmap : Bitmap
    var position: PointF
    var size : Point
    var backgroundColor : Int

    constructor(position : PointF = PointF(0f,0f),
                size : Point = Point(1000,1000),
                backgroundColor: Int = Color.BLACK) {
        this.position = position
        this.size = Point(size.x, size.y)
        this.backgroundColor = backgroundColor
        bitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888)
    }
}