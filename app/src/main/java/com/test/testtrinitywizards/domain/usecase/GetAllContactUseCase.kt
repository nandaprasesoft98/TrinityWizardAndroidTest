package com.test.testtrinitywizards.domain.usecase

import com.test.testtrinitywizards.di.module.DefaultDispatcher
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.domain.repository.ContactRepository
import com.test.testtrinitywizards.misc.LoadDataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllContactUseCase @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val contactRepository: ContactRepository
) {
    private val scope = CoroutineScope(coroutineDispatcher)

    fun execute(onResult: (LoadDataResult<List<Contact>>) -> Unit) {
        scope.launch {
            contactRepository
                .loadContactData()
                .collectLatest { onResult(it) }
        }
    }
}