package i.herman.cryptodata.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import i.herman.cryptodata.data.db.dao.CryptoDao
import i.herman.cryptodata.data.db.entity.CryptoModel

/**
 * Created by Illia Herman on 18.05.2021.
 */

const val CRYPTO_DB_NAME = "crypto.db"
const val TAB_CRYPTO_NAME = "table_crypto"

@Database(entities = [CryptoModel::class], version = 1, exportSchema = false)
abstract class CryptoDatabase: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao
}