package cm.lobe.loic.expensetracker.bill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cm.lobe.loic.expensetracker.R
import cm.lobe.loic.expensetracker.databinding.AddBillFragmentBinding
import cm.lobe.loic.expensetracker.home.TransactionViewModel
import cm.lobe.loic.expensetracker.model.TransactionModel


class AddBill : Fragment() {

    companion object {
        fun newInstance() = AddBill()
    }

    private var _binding: AddBillFragmentBinding? = null
    private val binding get() = _binding!!

    private val transactionViewModel : TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AddBillFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = binding.txtTitre.text.toString()
        val price = binding.txtMontant.text.toString()
//        val tag = binding.autoCompleteTag.text
        val tag = "Enoe"
        val type = 0
        val date = "date"

        binding.btnAddTransaction.setOnClickListener {
            if(title.isEmpty() ||
                    price.isEmpty()){
                Toast.makeText(requireContext(), "Remplir les donnees", Toast.LENGTH_LONG).show()

            }else{
                transactionViewModel.saveTransaction(
                    TransactionModel(
                        id = "",
                        title = title,
                        price = price,
                        type = type,
                        tag = tag,
                        date = date
                    )
                )
                Toast.makeText(requireContext(), "Donnees enregistress", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addBill_to_homeFragment)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addBill_to_homeFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}