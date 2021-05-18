package i.herman.cryptodata.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import i.herman.cryptodata.data.db.TAB_CRYPTO_NAME

@Entity(tableName = TAB_CRYPTO_NAME)
data class CryptoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val acronym: String,
    val coin_name: String,
    val logo: String,
    val uid: String,
)