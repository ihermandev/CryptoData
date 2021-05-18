package i.herman.cryptodata.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import i.herman.cryptodata.BuildConfig
import i.herman.cryptodata.data.db.CRYPTO_DB_NAME
import i.herman.cryptodata.data.db.CryptoDatabase
import i.herman.cryptodata.data.db.dao.CryptoDao
import i.herman.cryptodata.data.remote.CryptoApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Illia Herman on 18.05.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Database
    @Provides
    fun provideCryptoDao(appDatabase: CryptoDatabase): CryptoDao = appDatabase.cryptoDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CryptoDatabase =
        Room.databaseBuilder(
            appContext,
            CryptoDatabase::class.java,
            CRYPTO_DB_NAME
        ).build()

    //Network
    @Provides
    fun cryptoAPI(retrofit: Retrofit): CryptoApi = retrofit.create(CryptoApi::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}