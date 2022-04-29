package cm.lobe.loic.expensetracker.data

import cm.lobe.loic.expensetracker.Utils.Result
import cm.lobe.loic.expensetracker.Utils.safeCall
import cm.lobe.loic.expensetracker.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface TransactionRepository {
    suspend fun getAllTransaction(): Result<List<TransactionModel>>
    suspend fun saveTransaction(transactionModel: TransactionModel): Result<Boolean>
}

@Singleton
open class DefaultTransactionRepository @Inject constructor(
    private val transactionDataSource: TransactionDataSource,
) : TransactionRepository{

    override suspend fun getAllTransaction(): Result<List<TransactionModel>> {
        return withContext(Dispatchers.IO){
            safeCall {
                val results = transactionDataSource.getAllTransaction()
                Result.Success(results)
            }
        }
    }

    override suspend fun saveTransaction(transactionModel: TransactionModel): Result<Boolean> {
        return withContext(Dispatchers.IO){
            safeCall {
                val result = transactionDataSource.saveTransaction(transactionModel)
                Result.Success(result)
            }
        }
    }
}
