package barinalex.drawwithyourbro.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DrawingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(drawing: Drawing)

    @Update
    suspend fun updateDraw(drawing: Drawing)

    @Delete
    suspend fun deleteDraw(drawing: Drawing)

    @Query("DELETE FROM drawing_table")
    suspend fun deleteAllDrawings()

    @Query("SELECT * FROM drawing_table ORDER BY name ASC")
    suspend fun readCurrentData() : List<Drawing>

    @Transaction
    suspend fun readAllAndDelete() : List<Drawing>{
        val drawings = readCurrentData()
        deleteAllDrawings()
        return drawings
    }

    @Query("SELECT * FROM drawing_table ORDER BY name ASC")
    fun readAllData() : LiveData<List<Drawing>>

}