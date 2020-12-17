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
import kotlinx.android.synthetic.main.fragment_rename_draw.*
import kotlinx.android.synthetic.main.fragment_rename_draw.view.*
import java.io.File


class RenameDrawFragment(drawing: Drawing): Fragment(R.layout.fragment_rename_draw) {
    private lateinit var drawingViewModel: DrawingViewModel
    private val drawing: Drawing

    init {
        this.drawing = drawing
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        view.renameButton.setOnClickListener{
            updateDatabase()
            //if stack is empty?
        }
    }

    private fun updateDatabase(){
        val newName = new_drawing_name.text.toString()
        if (!TextUtils.isEmpty(newName)){
            drawingViewModel.renameDrawing(drawing, newName)
            Toast.makeText(requireActivity(), "drawing is updated", Toast.LENGTH_LONG).show()
            new_drawing_name.setText("")
            requireActivity().supportFragmentManager.popBackStack()
        }
        else{
            Toast.makeText(activity, "fill out the filds", Toast.LENGTH_LONG).show()
        }
    }
}