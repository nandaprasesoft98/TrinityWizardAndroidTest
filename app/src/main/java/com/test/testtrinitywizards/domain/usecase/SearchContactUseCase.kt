package com.test.testtrinitywizards.domain.usecase

import com.test.testtrinitywizards.di.module.DefaultDispatcher
import com.test.testtrinitywizards.domain.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchContactUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    private val scope = CoroutineScope(coroutineDispatcher)

    fun execute(searchText: String, localContactList: List<Contact>, onResult: (List<Contact>) -> Unit) {
        scope.launch {
            onResult(
                localContactList.filter { contact ->
                    contact.firstName.contains(searchText, ignoreCase = true) ||
                    contact.lastName.contains(searchText, ignoreCase = true) ||
                    contact.email.contains(searchText, ignoreCase = true)
                }
            )
        }
    }
}