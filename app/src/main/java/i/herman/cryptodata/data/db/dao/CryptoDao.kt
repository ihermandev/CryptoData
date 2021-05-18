package i.herman.cryptodata.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import i.herman.cryptodata.data.db.TAB_CRYPTO_NAME
import i.herman.cryptodata.data.db.entity.CryptoModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Illia Herman on 18.05.2021.
 */
@Dao
interface CryptoDao: BaseDao<CryptoModel> {

    @Query("SELECT * FROM $TAB_CRYPTO_NAME")
    fun getAllCrypto(): Flow<List<CryptoModel>>

    @Query("DELETE FROM $TAB_CRYPTO_NAME")
    suspend fun deleteAllCrypto()
}