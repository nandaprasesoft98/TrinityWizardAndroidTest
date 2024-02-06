package com.test.testtrinitywizards.viewmodel;

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.domain.usecase.GetAllContactUseCase;
import com.test.testtrinitywizards.misc.LoadDataResult

import javax.inject.Inject;

import dagger.Lazy;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.launch

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getAllContactUseCase: Lazy<GetAllContactUseCase>,
) : ViewModel() {
    fun getAllContact() {
        viewModelScope.launch {
            getAllContactUseCase.get().execute { result ->
                Log.d("After Loaded", result.toString())
                if (result is LoadDataResult.Success<List<Contact>>) {
                    Log.d("Result", result.value.toString())
                }
            }
        }
    }
}