package cm.lobe.loic.expensetracker.bill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cm.lobe.loic.expensetracker.R
import cm.lobe.loic.expensetracker.databinding.AddBillFragmentBinding


class AddBill : Fragment() {

    companion object {
        fun newInstance() = AddBill()
    }

    private var _binding: AddBillFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AddBillFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}