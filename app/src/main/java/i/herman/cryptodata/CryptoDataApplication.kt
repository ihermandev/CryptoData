package i.herman.cryptodata

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Illia Herman on 18.05.2021.
 */
@HiltAndroidApp
class CryptoDataApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
