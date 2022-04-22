package cm.lobe.loic.expensetracker.data

import cm.lobe.loic.expensetracker.Utils.Constants
import cm.lobe.loic.expensetracker.Utils.safeCall
import cm.lobe.loic.expensetracker.model.TransactionModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class TransactionDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getAllTransaction() : List<TransactionModel>{
        /*return try{
            transactionCollection.get().await().toObjects(TransactionModel::class.java)
        }*/
        /*return withContext(Dispatchers.IO){
            safeCall {
                val task = firestore
                    .collection(TRANSACTION_COLLECTION)
                    .get()
                val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
                val transaction = snapshot.documents.map { parseTransaction(it)}.sortedBy { it.date }
                Result.success(transaction)
            }
            }
*/
        val transactionList = withContext(Dispatchers.IO){
            val task = firestore
                .collection(TRANSACTION_COLLECTION)
                .get()
            val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
            snapshot.documents.map { parseTransaction(it)}.sortedBy { it.date }
        }
            return transactionList
        }

    suspend fun saveTransaction(transactionModel: TransactionModel) : Boolean{
        return withContext(Dispatchers.IO){
            val task = firestore
                .collection(TRANSACTION_COLLECTION)
                .add(transactionModel)
            task.isSuccessful
        }
    }

    private fun parseTransaction(snapshot: DocumentSnapshot) : TransactionModel{
        return  TransactionModel(
            id = snapshot.id,
            title = snapshot[TITLE] as? String ?: "",
            price = snapshot[PRICE] as? String ?: "",
            type = (snapshot[TYPE] as? Long ?: 0).toInt(),
            tag = snapshot[TAG] as? String ?: "",
            date = snapshot[DATE] as? String ?: "",

        )
    }
    companion object{
        const val TRANSACTION_COLLECTION = "transactions"
        const val ID = "id"
        const val TITLE = "title"
        const val PRICE = "price"
        //    0 --> depenses, 1--> entree
        const val TYPE = "price"
        const val TAG = "tag"
        const val DATE = "date"
    }
}
