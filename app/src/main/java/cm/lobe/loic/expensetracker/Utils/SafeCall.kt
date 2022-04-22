package cm.lobe.loic.expensetracker.Utils

// function to allow safe network requests.
inline fun <T> safeCall(action: () -> Result<T>): Result<T> {
    return try {
        action()
    } catch (e: Exception) {
        Result.Error(e.message ?: "An unknown Error Occurred")
    }
}
