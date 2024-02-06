package com.test.testtrinitywizards.ui

import com.test.testtrinitywizards.domain.model.Contact

data class ContactListUiState(
    val contactList: MutableList<Contact> = mutableListOf(),
    val errorMessage: String = "",
    val searchText: String = "",
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)