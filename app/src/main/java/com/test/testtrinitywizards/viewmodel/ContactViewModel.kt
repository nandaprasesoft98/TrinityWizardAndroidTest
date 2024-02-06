package com.test.testtrinitywizards.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.domain.usecase.GetAllContactUseCase;
import com.test.testtrinitywizards.domain.usecase.SearchContactUseCase
import com.test.testtrinitywizards.misc.LoadDataResult
import com.test.testtrinitywizards.ui.ContactListUiState

import javax.inject.Inject;

import dagger.Lazy;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getAllContactUseCase: Lazy<GetAllContactUseCase>,
    private val searchContactUseCase: Lazy<SearchContactUseCase>,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ContactListUiState())
    val uiState = _uiState.asStateFlow()

    private var resultContactList: MutableList<Contact> = mutableListOf()

    fun refreshAllContact() {
        _uiState.update {
            it.copy(
                isRefreshing = true
            )
        }
        getAllContact()
        _uiState.update {
            it.copy(
                isRefreshing = false
            )
        }
    }

    fun getAllContact() {
        viewModelScope.launch {
            getAllContactUseCase.get().execute { result ->
                when (result) {
                    is LoadDataResult.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is LoadDataResult.Success -> {
                        resultContactList = result.value.toMutableList()
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                contactList = resultContactList
                            )
                        }
                    }
                    is LoadDataResult.Failed -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.t.message ?: ""
                            )
                        }
                    }
                }
            }
        }
    }

    fun searchContact(searchText: String) {
        viewModelScope.launch {
            searchContactUseCase.get().execute(searchText, resultContactList) { searchResultContactList ->
                _uiState.update {
                    it.copy(
                        contactList = searchResultContactList.toMutableList(),
                        searchText = searchText
                    )
                }
            }
        }
    }

    fun changeContact(contact: Contact) {
        viewModelScope.launch {
            val contactList = ArrayList(resultContactList)
            var selectedIndex: Int = -1;
            for ((index, iteratedContact) in contactList.withIndex()) {
                if (iteratedContact.id == contact.id) {
                    selectedIndex = index
                    break
                }
            }
            if (selectedIndex > -1) {
                contactList[selectedIndex] = contact.copy()
            }
            _uiState.update {
                it.copy(
                    contactList = contactList
                )
            }
            resultContactList = contactList
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            val contactList = ArrayList(resultContactList)
            contactList.add(contact)
            _uiState.update {
                it.copy(
                    contactList = contactList
                )
            }
            resultContactList = contactList
        }
    }
}