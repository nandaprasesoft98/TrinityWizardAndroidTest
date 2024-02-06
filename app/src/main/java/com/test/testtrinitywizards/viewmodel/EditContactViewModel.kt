package com.test.testtrinitywizards.viewmodel

import androidx.lifecycle.ViewModel
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.ui.EditContactUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditContactViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(EditContactUiState())
    val uiState = _uiState.asStateFlow()

    private fun checkEditContactExisting(onEditContactExists: (Contact) -> Unit) {
        if (_uiState.value.editedContact != null) {
            onEditContactExists(_uiState.value.editedContact!!)
        }
    }

    fun setSelectedContact(selectedContact: Contact) {
        _uiState.update {
            it.copy(
                editedContact = selectedContact
            )
        }
    }

    fun editFirstNameContact(firstName: String) {
        checkEditContactExisting { contact ->
            _uiState.update {
                it.copy(
                    editedContact = contact.copy(
                        firstName = firstName
                    )
                )
            }
        }
    }

    fun editLastNameContact(lastName: String) {
        checkEditContactExisting { contact ->
            _uiState.update {
                it.copy(
                    editedContact = contact.copy(
                        lastName = lastName
                    )
                )
            }
        }
    }

    fun editEmailContact(email: String) {
        checkEditContactExisting { contact ->
            _uiState.update {
                it.copy(
                    editedContact = contact.copy(
                        email = email
                    )
                )
            }
        }
    }

    fun editDobContact(dob: String) {
        checkEditContactExisting { contact ->
            _uiState.update {
                it.copy(
                    editedContact = contact.copy(
                        dob = dob
                    )
                )
            }
        }
    }
}