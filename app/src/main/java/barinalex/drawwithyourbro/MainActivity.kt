package barinalex.drawwithyourbro

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import barinalex.drawwithyourbro.data.User
import barinalex.drawwithyourbro.data.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var surfaceModel: SurfaceModel
    private lateinit var drawSurface: DrawSurface

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        surfaceModel  = SurfaceModel()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val draw_layout : LinearLayout = findViewById(R.id.draw_layout);
        drawSurface = DrawSurface(this, surfaceModel)
        draw_layout.addView(drawSurface)

        surfaceModel.notifyAllOnChange()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.settings -> {
                val user = User(0, "onlyuser")
                mUserViewModel.addUser(user)
                Toast.makeText(this, "user added", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
