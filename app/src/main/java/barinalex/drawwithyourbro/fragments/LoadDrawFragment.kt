package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.data.DrawingViewModel
import kotlinx.android.synthetic.main.fragment_load_draw.view.*
import java.io.File
import java.io.FileInputStream


class LoadDrawFragment : Fragment(R.layout.fragment_load_draw) {

    lateinit var drawingViewModel: DrawingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        //Recyclerview
        val adapter = ListAdapter(this)
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        drawingViewModel = ViewModelProvider(this).get(DrawingViewModel::class.java)
        drawingViewModel.readAllData.observe(viewLifecycleOwner, Observer {drawings ->
            adapter.setData(drawings)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.load_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteall -> {
                drawingViewModel.deleteAllDrawings()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}