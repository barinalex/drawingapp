package barinalex.drawwithyourbro.fragments.loaddrawfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.data.Drawing
import barinalex.drawwithyourbro.fragments.RenameDrawFragment
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter(fragment: LoadDrawFragment): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private val fragment: LoadDrawFragment
    init {
        this.fragment = fragment
    }

    private var drawingList = emptyList<Drawing>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return drawingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = drawingList[position]
        holder.itemView.draw_name.text = currentItem.name
        holder.itemView.data_row_layout.setOnClickListener {
            fragment.surfaceViewModel.load(currentItem)
            fragment.requireActivity().supportFragmentManager.popBackStack()
        }
        holder.itemView.change_button.setOnClickListener {
            val renameDrawFragment = RenameDrawFragment(currentItem)
            fragment.requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, renameDrawFragment)
                addToBackStack("RenameDrawFragment")
                commit()
            }
        }
        holder.itemView.delete_button.setOnClickListener {
            fragment.drawingViewModel.deleteDrawing(currentItem)
        }
    }

    fun setData(drawing: List<Drawing>){
        this.drawingList = drawing
        notifyDataSetChanged()
    }
}