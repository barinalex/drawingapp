package barinalex.drawwithyourbro.data

import androidx.lifecycle.LiveData
import barinalex.drawwithyourbro.drawableObjects.DrawableObject
import java.util.*

class UserRepository (private val userDao: UserDao) {
    var readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}