package com.odogwudev.demonstration.ui.mainActivity

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.odogwudev.demonstration.model.ItemMenuEntity
import com.odogwudev.demonstration.repository.MainRepository
import com.odogwudev.demonstration.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivityViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemsList: MutableLiveData<DataState<List<ItemMenuEntity>>> = MutableLiveData()
    private val _selectedItem: MutableLiveData<ItemMenuEntity> = MutableLiveData()
    private var _listOfItems: List<ItemMenuEntity> = ArrayList()

    val itemsList: LiveData<DataState<List<ItemMenuEntity>>>
        get() = _itemsList

    val selectedItem: LiveData<ItemMenuEntity>
        get() = _selectedItem

    fun uploadItemsList() {
        viewModelScope.launch {
            mainRepository.getMenuItems()
                .onEach {
                    _itemsList.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    fun itemSelected(name: CharSequence) {
        _selectedItem.value = _listOfItems.find { it.name == name as String }
        Log.d(TAG, "itemSelected: $name")
    }

    fun setItemsList(items: List<ItemMenuEntity>) {
        _listOfItems = items
        if (_selectedItem.value == null) {
            _selectedItem.value = items[0]
        }
    }

    sealed class StateEvent {
        object GetMenuItems : StateEvent()
        object None : StateEvent()
    }

    companion object {
        const val TAG = "MainActivityViewModel"
    }
}