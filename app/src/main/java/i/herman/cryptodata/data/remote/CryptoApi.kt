package i.herman.cryptodata.data.remote

import i.herman.cryptodata.data.db.entity.CryptoModel
import retrofit2.http.GET

/**
 * Created by Illia Herman on 18.05.2021.
 */
interface CryptoApi {

    @GET("raw/6fa7cc6124371a15b29d7f5def0a35831185185b")
    suspend fun getAllCrypto(): List<CryptoModel>
}