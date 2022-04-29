package cm.lobe.loic.expensetracker.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cm.lobe.loic.expensetracker.data.TransactionDataSource
import cm.lobe.loic.expensetracker.data.TransactionRepository
import cm.lobe.loic.expensetracker.di.ApplicationScope
import cm.lobe.loic.expensetracker.model.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import cm.lobe.loic.expensetracker.Utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    @ApplicationScope private val coroutineScope: CoroutineScope,
) : ViewModel() {

    private var _transactionList = MutableLiveData<Result<List<TransactionModel>>>()
    val transactionList: LiveData<Result<List<TransactionModel>>> get() = _transactionList

    private var _taskCompleted = MutableLiveData<Result<Boolean>>()
    val taskCompleted: LiveData<Result<Boolean>> get() = _taskCompleted

    fun getAllTransaction(){
        _transactionList.postValue(Result.Loading())
        viewModelScope.launch (Dispatchers.Main){
            val data = transactionRepository.getAllTransaction()
            _transactionList.postValue(data)
        }
    }

    fun saveTransaction(transactionModel: TransactionModel){
        _taskCompleted.postValue(Result.Loading())
        viewModelScope.launch (Dispatchers.Main){
            val v = transactionRepository.saveTransaction(transactionModel)
            _taskCompleted.postValue(v)
        }
    }
}