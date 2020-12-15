package barinalex.drawwithyourbro.data

import androidx.lifecycle.LiveData

class DrawingRepository (private val drawingDao: DrawingDao) {
    var readAllData: LiveData<List<Drawing>> = drawingDao.readAllData()

    suspend fun addDrawing(drawing: Drawing){
        drawingDao.addUser(drawing)
    }

    suspend fun updateDrawing(drawing: Drawing){
        drawingDao.updateDraw(drawing)
    }

    suspend fun deleteDrawing(drawing: Drawing){
        drawingDao.deleteDraw(drawing)
    }

    suspend fun deleteAllDrawings(){
        drawingDao.deleteAllDrawings()
    }

    suspend fun readAllAndDelete() : List<Drawing>{
        return drawingDao.readAllAndDelete()
    }
}