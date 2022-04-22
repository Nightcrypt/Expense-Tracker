package cm.lobe.loic.expensetracker.model

class TransactionModel(
    val id : String,
    val title : String,
    val price : String,
//    0 --> depenses, 1--> entree
    val type : Int,
    val tag : String,
    val date : String
) {
    override fun toString(): String {
        return "\n" +
                "TransactionModel(" +
//                "id='$id', " +
                "title='$title'," +
                " prix=$price," +
                " type=$tag," +
                " tag=$tag +" +
                "date=$date)"
    }

}