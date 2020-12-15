package barinalex.drawwithyourbro.fragments

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import barinalex.drawwithyourbro.DrawSurfaceView
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.DrawSurfaceModel


class DrawFragment() : Fragment(R.layout.fragment_draw) {

    val newDrawFragment = NewDrawFragment()
    val saveDrawFragment = SaveDrawFragment()
    val loadDrawFragment = LoadDrawFragment()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayMetrics = requireActivity().resources.displayMetrics
        val size = Point(displayMetrics.widthPixels,displayMetrics.heightPixels)
        val surfaceModel = DrawSurfaceModel.getInstance(size)
        val drawSurface = DrawSurfaceView(requireActivity(), surfaceModel)
        val draw_layout : FrameLayout = view.findViewById(R.id.fragment_draw_main_frame);
        draw_layout.addView(drawSurface)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.draw_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.newdrawing -> {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, newDrawFragment)
                    addToBackStack("NewDrawFragment")
                    commit()
                }
                true
            }
            R.id.save -> {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, saveDrawFragment)
                    addToBackStack("SaveDrawFragment")
                    commit()
                }
                true
            }
            R.id.load -> {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, loadDrawFragment)
                    addToBackStack("LoadDrawFragment")
                    commit()
                }
                true
            }
            else -> { super.onOptionsItemSelected(item) }
        }
    }
}