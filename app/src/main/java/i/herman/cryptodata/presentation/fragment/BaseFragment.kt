package i.herman.cryptodata.presentation.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.iherman.util.addFragmentAnimated
import com.iherman.util.replaceFragmentAnimated
import i.herman.cryptodata.R
import i.herman.cryptodata.presentation.activity.MainActivity
import i.herman.cryptodata.presentation.viewmodel.DashboardViewModel
import i.herman.cryptodata.utils.obtainViewModel

/**
 * Created by Illia Herman on 18.05.2021.
 */
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    val dashboardViewModel: DashboardViewModel by lazy {
        obtainViewModel(getMainActivity(), DashboardViewModel::class.java, defaultViewModelProviderFactory)
    }

    fun replaceFragmentAnimated(
        fragment: Fragment,
        containerId: Int = R.id.container,
        addToBackStack: Boolean = false,
        clearBackStack: Boolean = false,
        activity: AppCompatActivity = getMainActivity()
    ) {
        activity.replaceFragmentAnimated(fragment, containerId, addToStack = addToBackStack, clearBackStack = clearBackStack)
    }

    fun addFragmentAnimated(
        fragment: Fragment,
        containerId: Int = R.id.container,
        addToBackStack: Boolean = false,
        activity: AppCompatActivity = getMainActivity()
    ) {
        activity.addFragmentAnimated(fragment, containerId, addToStack = addToBackStack)
    }

    fun getMainActivity() = (activity as MainActivity)

}