package abandonedstudio.app.currencyinfo.model.remote.util

sealed class Resource<T>(val data: T?, val errorMessage: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(msg: String) : Resource<T>(null, msg)
}
