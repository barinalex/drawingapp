package barinalex.drawwithyourbro

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


    val drawFragment = DrawFragment()
    val listOfDrawsFragment = ListOfDrawsFragment()

    val surfaceName = "basic surface"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, drawFragment)
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
                if (supportFragmentManager.findFragmentByTag(listOfDrawsFragment.fragmentTag) != null){
                    supportFragmentManager.beginTransaction().apply {
                        hide(supportFragmentManager.findFragmentByTag(listOfDrawsFragment.fragmentTag)!!)
                        commit()
                    }
                }
                if (supportFragmentManager.findFragmentByTag(drawFragment.fragmentTag) == null) {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment, drawFragment, drawFragment.fragmentTag)
                        commit()
                    }
                }
                else{
                    supportFragmentManager.beginTransaction().apply {
                        show(supportFragmentManager.findFragmentByTag(drawFragment.fragmentTag)!!)
                        commit()
                    }
                }
            }
            R.id.list -> {
                if (supportFragmentManager.findFragmentByTag(drawFragment.fragmentTag) != null){
                    supportFragmentManager.beginTransaction().apply {
                        hide(supportFragmentManager.findFragmentByTag(drawFragment.fragmentTag)!!)
                        commit()
                    }
                }
                if (supportFragmentManager.findFragmentByTag(listOfDrawsFragment.fragmentTag) == null) {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment, listOfDrawsFragment, listOfDrawsFragment.fragmentTag)
                        commit()
                    }
                }
                else{
                    supportFragmentManager.beginTransaction().apply {
                        show(supportFragmentManager.findFragmentByTag(listOfDrawsFragment.fragmentTag)!!)
                        commit()
                    }
                }
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
