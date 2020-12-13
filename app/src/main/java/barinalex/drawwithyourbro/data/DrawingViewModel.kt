package barinalex.drawwithyourbro.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrawingViewModel(application: Application): AndroidViewModel(application) {
    private var readAllData: LiveData<List<Drawing>>
    private var repository: DrawingRepository

    init{
        var drawingDao = DrawingDatabase.getDatabase(application).drawingDao()
        repository = DrawingRepository(drawingDao)
        readAllData = repository.readAllData
    }

    fun addUser(drawing: Drawing){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrawing(drawing)
        }
    }
}