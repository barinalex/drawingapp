package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.data.Drawing
import barinalex.drawwithyourbro.data.DrawingViewModel
import kotlinx.android.synthetic.main.fragment_update_draw.*
import kotlinx.android.synthetic.main.fragment_update_draw.view.*
import java.io.File


class UpdateDrawFragment(drawing: Drawing): Fragment(R.layout.fragment_update_draw) {
    private lateinit var drawingViewModel: DrawingViewModel
    private val drawing: Drawing

    init {
        this.drawing = drawing
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(activity, "update draw fragment created", Toast.LENGTH_LONG).show()
        drawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        view.updateButton.setOnClickListener{
            updateDatabase()
            requireActivity().supportFragmentManager.popBackStack()
            //if stack is empty?
        }
    }

    private fun updateDatabase(){
        val newName = new_drawing_name.text.toString()
        if (!TextUtils.isEmpty(newName)){
            val file = File(requireActivity().filesDir, drawing.name)
            file.renameTo(File(requireActivity().filesDir, newName))
            drawingViewModel.updateDrawing(Drawing(drawing.id, newName))
            Toast.makeText(requireActivity(), "drawing is updated", Toast.LENGTH_LONG).show()
            new_drawing_name.setText("")
        }
        else{
            Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
        }
    }
}