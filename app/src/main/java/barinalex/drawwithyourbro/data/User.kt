package barinalex.drawwithyourbro.data

import android.graphics.Bitmap
import java.util.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import barinalex.drawwithyourbro.drawableObjects.DrawableObject

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String
    //val drawableObjects: Stack<DrawableObject>
)