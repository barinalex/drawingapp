package barinalex.drawwithyourbro.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import barinalex.drawwithyourbro.R
import barinalex.drawwithyourbro.data.Drawing
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var drawingList = emptyList<Drawing>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return drawingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = drawingList[position]
        holder.itemView.draw_id.text = currentItem.id.toString()
        holder.itemView.draw_name.text = currentItem.name.toString()
    }

    fun setData(drawing: List<Drawing>){
        this.drawingList = drawing
        notifyDataSetChanged()
    }
}