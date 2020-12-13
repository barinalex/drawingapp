package barinalex.drawwithyourbro.data

import androidx.lifecycle.LiveData

class DrawingRepository (private val drawingDao: DrawingDao) {
    var readAllData: LiveData<List<Drawing>> = drawingDao.readAllData()

    suspend fun addDrawing(drawing: Drawing){
        drawingDao.addUser(drawing)
    }
}