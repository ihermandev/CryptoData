package i.herman.cryptodata.presentation.fragment

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.iherman.util.applyViewInvisible
import com.iherman.util.applyViewVisible
import com.iherman.util.hideKeyboard
import com.iherman.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import i.herman.cryptodata.R
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.databinding.FragmentSearchCryptoBinding
import i.herman.cryptodata.presentation.adapter.CryptoRecyclerAdapter

@AndroidEntryPoint
class SearchCryptoFragment : BaseFragment(R.layout.fragment_search_crypto) {

    private lateinit var _binding: FragmentSearchCryptoBinding

    private val cryptoAdapter: CryptoRecyclerAdapter by lazy {
        CryptoRecyclerAdapter(listener = { item ->
            getMainActivity().hideKeyboard()
            shortToast(item.toString())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCryptoBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewListeners()
        initRecyclerView()
        setDataToAdapter()
    }

    private fun initView() {
        val searchTextView =
            _binding.searchView.findViewById<SearchView.SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
        searchTextView.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_background_color
                )
            )
            setHintTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            setPadding(64, 0, 64, 0)
        }
    }

    private fun initViewListeners() {
        _binding.ivBack.setOnClickListener {
            getMainActivity().hideKeyboard()
            getMainActivity().onBackPressed()
        }

        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when {
                    newText.isNullOrEmpty() -> applyViewVisible(_binding.ivSearch)
                    else -> applyViewInvisible(_binding.ivSearch)
                }
                cryptoAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun initRecyclerView() {
        _binding.rvCrypto.adapter = cryptoAdapter
    }

    private fun updateRecyclerData(data: List<CryptoModel>) {
        cryptoAdapter.addItems(data)
    }

    private fun setDataToAdapter() {
        dashboardViewModel.cryptoList.value?.data?.let { updateRecyclerData(it) }
    }

    companion object {
        val TAG: String = SearchCryptoFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = SearchCryptoFragment()
    }
}