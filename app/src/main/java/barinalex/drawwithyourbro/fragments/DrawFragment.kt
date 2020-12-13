package barinalex.drawwithyourbro.fragments

import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import barinalex.drawwithyourbro.DrawSurface
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.SurfaceModel


class DrawFragment() : Fragment(R.layout.fragment_draw) {
    val fragmentTag = "DrawFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(activity, "draw fragment created", Toast.LENGTH_LONG).show()
        val draw_layout : FrameLayout = view.findViewById(R.id.fragment_draw_main_frame);

        val displayMetrics = requireActivity().resources.displayMetrics
        val borders = Point(displayMetrics.widthPixels,displayMetrics.heightPixels)
        val surfaceModel = SurfaceModel.getInstance(borders)
        var drawSurface = DrawSurface(requireActivity(), surfaceModel)
        draw_layout.addView(drawSurface)
    }
}