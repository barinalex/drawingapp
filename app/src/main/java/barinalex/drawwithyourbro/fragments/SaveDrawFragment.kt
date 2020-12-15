package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.DrawSurfaceModel
import barinalex.drawwithyourbro.Utils
import barinalex.drawwithyourbro.data.Drawing
import barinalex.drawwithyourbro.data.DrawingViewModel
import kotlinx.android.synthetic.main.fragment_save_draw.*
import kotlinx.android.synthetic.main.fragment_save_draw.view.*
import java.io.File


class SaveDrawFragment : Fragment(R.layout.fragment_save_draw) {
    private lateinit var mDrawingViewModel: DrawingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDrawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        view.save_btn.setOnClickListener{
            insertToDatabase()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    private fun insertToDatabase(){
        val name = saveDrawingName.text.toString()
        if (!TextUtils.isEmpty(name)){
            val surfaceModel = DrawSurfaceModel.getInstance()
            val file = File(requireContext().filesDir, name)
            Utils.bitmapToFile(surfaceModel.bitmap, file)
            mDrawingViewModel.addDrawing(Drawing(0, name))
            Toast.makeText(requireActivity(), "drawing is saved", Toast.LENGTH_LONG).show()
            saveDrawingName.setText("")
        }
        else{
            Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
        }
    }
}