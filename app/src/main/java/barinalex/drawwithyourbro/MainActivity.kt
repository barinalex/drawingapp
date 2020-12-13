package barinalex.drawwithyourbro

import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import barinalex.drawwithyourbro.data.DrawingViewModel
import barinalex.drawwithyourbro.fragments.DrawFragment
import barinalex.drawwithyourbro.fragments.ListOfDrawsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawingViewModel: DrawingViewModel
    private lateinit var surfaceModel: SurfaceModel
    private lateinit var drawSurface: DrawSurface


    lateinit var drawFragment : DrawFragment
    lateinit var listOfDrawsFragment : ListOfDrawsFragment

    val surfaceName = "basic surface"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawFragment = DrawFragment()
        listOfDrawsFragment = ListOfDrawsFragment()

        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.flFragment, drawFragment)
                commit()
            }
        }
        /*

        val displayMetrics = this.resources.displayMetrics
        val borders = Point(displayMetrics.widthPixels,displayMetrics.heightPixels)
        surfaceModel = SurfaceModel(borders)

        mDrawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)

        val draw_layout : LinearLayout = findViewById(R.id.draw_layout);
        drawSurface = DrawSurface(this, surfaceModel)
        draw_layout.addView(drawSurface)
0
        val file = File(this.filesDir, surfaceName)
        if (file.exists())
            surfaceModel.drawBitmap(Utils.fileToBitmap(file))
        surfaceModel.notifyAllOnChange()

         */
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
            R.id.draw -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, drawFragment)
                    commit()
                }
                return true
            }
            R.id.list -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, listOfDrawsFragment)
                    commit()
                }
                return true
            }
            R.id.settings -> {
                /*
                val drawing = Drawing(0, surfaceModel.surfaceName)
                mDrawingViewModel.addUser(drawing)
                Toast.makeText(this, "drawing added", Toast.LENGTH_LONG).show()

                 */
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
        val file = File(this.filesDir, surfaceModel.surfaceName)
        if (file.exists())
            Utils.bitmapToFile(surfaceModel.bitmap, file)

         */
    }
}
