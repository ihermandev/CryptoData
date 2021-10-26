package i.herman.cryptodata.presentation.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.data.repository.DashboardRepository
import javax.inject.Inject

/**
 * Created by Illia Herman on 18.05.2021.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository,
): ViewModel() {

    private val mutableSelectedItem = MutableLiveData<CryptoModel>()

    val selectedItem: LiveData<CryptoModel> get() = mutableSelectedItem

    fun selectItem(item: CryptoModel) {
        mutableSelectedItem.value = item
    }

    val cryptoList = liveData {
        emitSource(repository.getCrypto().asLiveData())
    }

    val apiCrypto = liveData {
        emitSource(repository.getApiCrypto().asLiveData())
    }
}