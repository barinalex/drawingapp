package barinalex.drawwithyourbro.fragments

import android.graphics.Point
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.SurfaceViewModel
import kotlinx.android.synthetic.main.fragment_new_draw.*
import kotlinx.android.synthetic.main.fragment_new_draw.view.*


class NewDrawFragment : Fragment(R.layout.fragment_new_draw) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val surfaceViewModel: SurfaceViewModel by activityViewModels()

        new_drawing_width.setText(surfaceViewModel.surface.size.x.toString())
        new_drawing_height.setText(surfaceViewModel.surface.size.y.toString())
        view.create_btn.setOnClickListener{
            val widthstr = new_drawing_width.text
            val heightstr = new_drawing_height.text
            if (inputCheck(widthstr, heightstr)) {
                val width = Integer.parseInt(widthstr.toString())
                val height = Integer.parseInt(heightstr.toString())
                surfaceViewModel.create(Point(width,height))
                requireActivity().supportFragmentManager.popBackStack()
            }
            else{
                Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inputCheck(width: Editable, height: Editable): Boolean{
        return !width.isEmpty() && !height.isEmpty()
    }
}