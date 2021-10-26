package i.herman.cryptodata.data.repository

import android.util.Log
import androidx.room.withTransaction
import i.herman.cryptodata.data.db.CryptoDatabase
import i.herman.cryptodata.data.db.dao.CryptoDao
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.data.remote.CryptoApi
import i.herman.cryptodata.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Illia Herman on 18.05.2021.
 */
class DashboardRepository @Inject constructor(
    private val api: CryptoApi,
    private val db: CryptoDatabase,
    private val cryptoDao: CryptoDao
) {

    fun getCrypto() = networkBoundResource(
        fetchFromLocal = {
            Log.i(TAG, "Fetching from local cache")
            cryptoDao.getAllCrypto()
        },
        shouldFetchFromRemote = {
            val isRemoteNeeded = it.isNullOrEmpty()
            Log.i(TAG, "Is remote fetch needed? --> $isRemoteNeeded")
            isRemoteNeeded
        },
        fetchFromRemote = {
            Log.i(TAG, "Fetching from remote server")
            api.getAllCrypto()
        },
        saveRemoteData = {
            Log.i(TAG, "Saving from remote data to local cache")
            db.withTransaction {
                cryptoDao.deleteAllCrypto()
                cryptoDao.insertList(it)
            }
        }
    )

    suspend fun getApiCrypto() : Flow<List<CryptoModel>> = flow{ api.getAllCrypto() }

    companion object{
        val TAG: String = DashboardRepository::class.java.simpleName
    }
}