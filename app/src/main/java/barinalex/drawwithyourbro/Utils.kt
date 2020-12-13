package barinalex.drawwithyourbro

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class Utils {
    companion object {
        fun applyToPoint(a: PointF, b: PointF, lmbd: (Float, Float) -> Float): PointF {
            return PointF(lmbd(a.x, b.x), lmbd(a.y, b.y))
        }

        fun add(p1: PointF, p2: PointF): PointF {
            return applyToPoint(p1, p2, { a: Float, b: Float -> a + b })
        }

        fun substract(p1: PointF, p2: PointF): PointF {
            return applyToPoint(p1, p2, { a: Float, b: Float -> a - b })
        }

        fun bitmapToFile(bitmap: Bitmap, file: File){
            try{
                // Compress the bitmap and save in jpg format
                val stream: OutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
            }catch (e: IOException){
                e.printStackTrace()
            }
        }

        fun fileToBitmap(file: File) : Bitmap{
            return BitmapFactory.decodeFile(file.absolutePath)
        }
    }
}