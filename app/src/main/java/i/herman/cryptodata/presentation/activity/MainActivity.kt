package i.herman.cryptodata.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iherman.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import i.herman.cryptodata.R
import i.herman.cryptodata.presentation.fragment.DashboardFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(frameId = R.id.container, fragment = DashboardFragment.newInstance())
        }
    }
}