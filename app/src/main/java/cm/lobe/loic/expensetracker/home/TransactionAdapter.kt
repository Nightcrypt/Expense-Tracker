package cm.lobe.loic.expensetracker.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cm.lobe.loic.expensetracker.databinding.TransactionItemBinding
import cm.lobe.loic.expensetracker.model.TransactionModel
import javax.inject.Inject

class TransactionAdapter @Inject constructor(): RecyclerView.Adapter<TransactionItemViewHolder>() {

    private var listOfTransaction = mutableListOf<TransactionModel>()

    fun setTransactionItemsList(transactionItemList : List<TransactionModel>?){
        if(transactionItemList != null){
            this.listOfTransaction = transactionItemList.toMutableList()
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TransactionItemBinding.inflate(inflater, parent, false)
        return  TransactionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        val transactionItem = listOfTransaction[position]

        holder.binding.txtPriceTransaction.text = transactionItem.price
        holder.binding.txtCategoryTransaction.text = transactionItem.category
    }

    override fun getItemCount(): Int {
        return listOfTransaction.size
    }
}

class TransactionItemViewHolder(val binding : TransactionItemBinding) : RecyclerView.ViewHolder(binding.root)