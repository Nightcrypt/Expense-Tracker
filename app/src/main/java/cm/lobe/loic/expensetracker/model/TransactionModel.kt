package cm.lobe.loic.expensetracker.model

class TransactionModel(
    val id : String,
    val price : String,
//    0 --> depenses, 1--> entree
    val type : Int,
    val category : String,
    val date : String
) {
    override fun toString(): String {
        return "\n" +
                "TransactionModel(" +
//                "id='$id', " +
                " prix=$price," +
                " type=$type," +
                " category=$category +" +
                "date=$date)"
    }

}