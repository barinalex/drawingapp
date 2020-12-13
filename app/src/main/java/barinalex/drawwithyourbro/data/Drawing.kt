package barinalex.drawwithyourbro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drawing_table")
data class Drawing (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String
)