package barinalex.drawwithyourbro.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class DrawingViewModel(application: Application): AndroidViewModel(application) {
    val app: Application
    init {
        app = application
    }
    var readAllData: LiveData<List<Drawing>>
    private var repository: DrawingRepository

    init{
        var drawingDao = DrawingDatabase.getDatabase(application).drawingDao()
        repository = DrawingRepository(drawingDao)
        readAllData = repository.readAllData
    }

    fun addDrawing(drawing: Drawing){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrawing(drawing)
        }
    }

    fun updateDrawing(drawing: Drawing){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDrawing(drawing)
        }
    }

    fun deleteDrawing(drawing: Drawing){
        viewModelScope.launch(Dispatchers.IO) {
            val file = File(app.filesDir, drawing.name)
            file.delete()
            repository.deleteDrawing(drawing)
        }
    }

    fun deleteAllDrawings(){
        viewModelScope.launch(Dispatchers.IO) {
            for (drawing in repository.readAllAndDelete()){
                val file = File(app.filesDir, drawing.name)
                file.delete()
            }
        }
    }
}