package i.herman.cryptodata.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import i.herman.cryptodata.R
import i.herman.cryptodata.databinding.FragmentCryptoDetailBinding
import i.herman.cryptodata.utils.downloadImage

/**
 * Created by Illia Herman on 18.05.2021.
 */
@AndroidEntryPoint
class CryptoDetailFragment : BaseFragment(R.layout.fragment_crypto_detail) {

    private lateinit var _binding: FragmentCryptoDetailBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewListeners()
    }

    private fun initView() {
        dashboardViewModel.selectedItem.observe(getMainActivity(), Observer { item ->
            Log.i(TAG, "initView for data: $item")
            item.apply {
                _binding.tvTitle.text = item.coin_name
                _binding.tvSubTitle.text = item.acronym
                _binding.ivLogo.downloadImage(item.logo)
            }
        })
    }

    private fun initViewListeners() {
        _binding.ivBack.setOnClickListener {
            getMainActivity().onBackPressed()
        }
    }

    companion object {
        val TAG: String = CryptoDetailFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = CryptoDetailFragment()
    }
}