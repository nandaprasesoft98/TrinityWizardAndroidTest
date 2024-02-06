package com.test.testtrinitywizards.domain.repository

import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.misc.LoadDataResult
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun loadContactData(): Flow<LoadDataResult<List<Contact>>>
}