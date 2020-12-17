package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.SurfaceViewModel
import barinalex.drawwithyourbro.data.Drawing
import kotlinx.android.synthetic.main.fragment_save_draw.*
import kotlinx.android.synthetic.main.fragment_save_draw.view.*


class SaveDrawFragment : Fragment(R.layout.fragment_save_draw) {
    private val surfaceViewModel: SurfaceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.save_btn.setOnClickListener{
            insertToDatabase()
        }
    }


    private fun insertToDatabase(){
        val name = drawing_name.text.toString()
        if (!TextUtils.isEmpty(name)){
            surfaceViewModel.save(Drawing(0, name))
            Toast.makeText(requireActivity(), "drawing is saved", Toast.LENGTH_LONG).show()
            drawing_name.setText("")
            requireActivity().supportFragmentManager.popBackStack()
        }
        else{
            Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
        }
    }
}