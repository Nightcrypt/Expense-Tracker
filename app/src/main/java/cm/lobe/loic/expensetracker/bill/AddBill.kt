package cm.lobe.loic.expensetracker.bill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cm.lobe.loic.expensetracker.R
import cm.lobe.loic.expensetracker.Utils.Result
import cm.lobe.loic.expensetracker.databinding.AddBillFragmentBinding
import cm.lobe.loic.expensetracker.home.TransactionViewModel
import cm.lobe.loic.expensetracker.model.TransactionModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
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
//        val tag = binding.autoCompleteTag.text
        val category = "Enoe"
        val type = 0
        val date = "date"


        binding.btnAddTransaction.setOnClickListener {
            val price = binding.txtMontant.text.toString()

            println("Montant : $price")
            if( price.isEmpty()){
                Toast.makeText(requireContext(), "Remplir les donnees", Toast.LENGTH_LONG).show()

            }else{
                transactionViewModel.saveTransaction(
                    TransactionModel(
                        id = "",
                        price = price,
                        type = type,
                        category = category,
                        date = date
                    )
                )
                transactionViewModel.taskCompleted.observe(viewLifecycleOwner, Observer {
                    when(it){
                        is Result.Loading -> {

                        }
                        is Result.Error -> {

                        }
                        is Result.Success -> {
                            Toast.makeText(requireContext(), "Transaction Ajoutee", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addBill_to_homeFragment)
        }

    }

    override fun onResume() {
        super.onResume()
//
//        //liste pour le choix des categories
//        val categories = resources.getStringArray(R.array.category)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_transaction_category)
//        binding.autoCompleteTransactionCategory.setAdapter(arrayAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}