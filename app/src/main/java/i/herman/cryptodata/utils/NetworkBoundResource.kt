package i.herman.cryptodata.utils

import kotlinx.coroutines.flow.*

/**
 * Created by Illia Herman on 18.05.2021.
 */
inline fun <DB, REMOTE> networkBoundResource(
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline shouldFetchFromRemote: suspend (DB?) -> Boolean = { true },
    crossinline fetchFromRemote: suspend () -> REMOTE,
    crossinline saveRemoteData: suspend (REMOTE) -> Unit = { Unit }
) = flow<ApiResponse<DB>> {
    emit(ApiResponse.Loading(null))

    val localData = fetchFromLocal().first()

    val flow = if (shouldFetchFromRemote(localData)) {
        emit(ApiResponse.Loading(localData))
        try {
            saveRemoteData(fetchFromRemote())
            fetchFromLocal().map { ApiResponse.Success(it) }
        } catch (throwable: Throwable) {
            fetchFromLocal().map { ApiResponse.Error(throwable, it) }
        }
    } else {
        fetchFromLocal().map { ApiResponse.Success(it) }
    }

    emitAll(flow)
}