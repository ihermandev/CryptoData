package i.herman.cryptodata.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iherman.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import i.herman.cryptodata.R
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.databinding.FragmentDashboardBinding
import i.herman.cryptodata.presentation.adapter.CryptoRecyclerAdapter
import i.herman.cryptodata.utils.ApiResponse

@AndroidEntryPoint
class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    private lateinit var _binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        dashboardViewModel.cryptoList.observe(getMainActivity(), { cryptoData ->
            when (cryptoData) {
                is ApiResponse.Success -> {
                    cryptoData.data?.let { setupRecyclerView(data = it) } ?: shortToast("Empty list data")
                    switchLoader(isLoading = false)
                }
                is ApiResponse.Error -> {
                    shortToast(cryptoData.error?.localizedMessage ?: "Something went wrong")
                    switchLoader(isLoading = false)
                }
                is ApiResponse.Loading -> switchLoader(isLoading = true)
            }
        })
    }

    private fun setupRecyclerView(data: List<CryptoModel>) {
        Log.i(TAG, "Setup recycler view with data: $data")
        _binding.rvList.adapter = CryptoRecyclerAdapter(data){
            dashboardViewModel.selectItem(it)
            addFragmentAnimated(CryptoDetailFragment.newInstance(), addToBackStack = true)
        }
    }

    private fun switchLoader(isLoading: Boolean) {
        when (isLoading) {
            true -> _binding.loader.visibility = View.VISIBLE
            else -> _binding.loader.visibility = View.GONE
        }
    }

    companion object {
        val TAG: String = DashboardFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}