package cm.lobe.loic.expensetracker.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cm.lobe.loic.expensetracker.Utils.Result
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cm.lobe.loic.expensetracker.R
import cm.lobe.loic.expensetracker.databinding.HomeFragmentBinding
import cm.lobe.loic.expensetracker.model.TransactionModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val transactionViewModel : TransactionViewModel by activityViewModels()
    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv_transaction = binding.rvTransactionItem
        rv_transaction.layoutManager = LinearLayoutManager(context)
        rv_transaction.adapter = transactionAdapter

        transactionViewModel.getAllTransaction()
        transactionViewModel.transactionList.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Loading ->{

                }
                is Result.Success -> {
                    val transactionList = it.data
                    transactionAdapter.setTransactionItemsList(transactionList)
                }
                is Result.Error -> {

                }
            }
        })

        binding.btnAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addBill)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}