package home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import home.domain.HomeUseCase
import kotlinx.coroutines.launch
import model.MediaModel

class HomeViewModel: ViewModel() {
    private var _filmList = MutableLiveData<List<MediaModel>>()
    var filmList: LiveData<List<MediaModel>> = _filmList
    val homeUseCase = HomeUseCase()

    fun changeList(){
        filmList = _filmList
    }
    fun returnList(){
        viewModelScope.launch {
            _filmList = MutableLiveData(homeUseCase())
            changeList()
        }
    }
}