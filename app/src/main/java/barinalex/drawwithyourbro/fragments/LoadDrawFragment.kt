package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.data.DrawingViewModel
import kotlinx.android.synthetic.main.fragment_load_draw.view.*


class LoadDrawFragment : Fragment(R.layout.fragment_load_draw) {
    private lateinit var drawingViewModel: DrawingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(activity, "list fragment created", Toast.LENGTH_LONG).show()
        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        drawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        drawingViewModel.readAllData.observe(viewLifecycleOwner, Observer {drawing ->
            adapter.setData(drawing)
        })
    }
}