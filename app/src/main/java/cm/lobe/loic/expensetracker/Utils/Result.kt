package cm.lobe.loic.expensetracker.Utils

// function to allow safe network requests.
inline fun <T> safeCall(action: () -> Result<T>): Result<T> {
    return try {
        action()
    } catch (e: Exception) {
        Result.Error(e.message ?: "An unknown Error Occurred")
    }
}

// Inspirer de https://github.com/JoelKanyi/FirebaseCoroutinesDemo

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)
}