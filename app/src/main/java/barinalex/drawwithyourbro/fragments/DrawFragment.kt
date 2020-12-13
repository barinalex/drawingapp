package barinalex.drawwithyourbro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import barinalex.drawwithyourbro.R
import kotlinx.android.synthetic.main.fragment_draw.*


class DrawFragment : Fragment(R.layout.fragment_draw) {
    val fragmentTag = "DrawFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "draw fragment created", Toast.LENGTH_LONG).show()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}