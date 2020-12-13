package barinalex.drawwithyourbro.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DrawingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(drawing: Drawing)

    @Query("SELECT * FROM drawing_table ORDER BY name ASC")
    fun readAllData() : LiveData<List<Drawing>>
}