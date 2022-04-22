package cm.lobe.loic.expensetracker.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cm.lobe.loic.expensetracker.data.TransactionDataSource
import cm.lobe.loic.expensetracker.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(
//    private val transactionDataSource: TransactionDataSource
) : ViewModel() {

    val transactionDataSource = TransactionDataSource()

    private var _transactionList = MutableLiveData<List<TransactionModel>>()
    val transactionList: LiveData<List<TransactionModel>> get() = _transactionList

    fun getTransaction(){
//        _transactionList.postValue(Result.Loading())

        viewModelScope.launch (Dispatchers.Main){
            val data = transactionDataSource.getAllTransaction()
            _transactionList.postValue(data)
        }
    }

    fun saveTransaction(transactionModel: TransactionModel){
        viewModelScope.launch (Dispatchers.Main){
            val v = transactionDataSource.saveTransaction(transactionModel)
        }
    }
}