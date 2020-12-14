package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.SurfaceModel
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
        Toast.makeText(activity, "save draw fragment created", Toast.LENGTH_LONG).show()
        mDrawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        view.save_btn.setOnClickListener{
            insertToDatabase()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    private fun insertToDatabase(){
        val name = saveDrawingName.text.toString()
        if (!TextUtils.isEmpty(name)){
            val surfaceModel = SurfaceModel.getInstance()
            //store bitmap and save (?path) to database
            val file = File(requireContext().filesDir, name)
            //if (file.exists()) {
                Utils.bitmapToFile(surfaceModel.bitmap, file)
                val drawing = Drawing(0, name)
                mDrawingViewModel.addDrawing(drawing)
                Toast.makeText(requireActivity(), "drawing saved", Toast.LENGTH_LONG).show()
            //}else{
                //Toast.makeText(requireActivity(), "failed to save", Toast.LENGTH_LONG).show()
            //}
            saveDrawingName.setText("")
        }
        else{
            Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
        }
    }
}