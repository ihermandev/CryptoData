package i.herman.cryptodata.utils

/**
 * Created by Illia Herman on 18.05.2021.
 */
sealed class ApiResponse<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Loading<T>(data: T? = null) : ApiResponse<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : ApiResponse<T>(data, throwable)
}