package com.test.testtrinitywizards.data.repository

import com.test.testtrinitywizards.data.localasset.ContactLocalAssetSource
import com.test.testtrinitywizards.di.module.IODispatcher
import com.test.testtrinitywizards.domain.model.Contact
import com.test.testtrinitywizards.domain.repository.ContactRepository
import com.test.testtrinitywizards.misc.LoadDataResult
import com.test.testtrinitywizards.misc.ext.getAsyncRequestLoadDataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultContactRepository @Inject constructor(
    @IODispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val contactLocalAssetSource: ContactLocalAssetSource
): ContactRepository {
    override fun loadContactData(): Flow<LoadDataResult<List<Contact>>> {
        return flow {
            emit(LoadDataResult.Loading("Loading"));
            emit(
                getAsyncRequestLoadDataResult(coroutineDispatcher) {
                    contactLocalAssetSource.loadContactData().map { value ->
                        value.toDomainModel()
                    }
                }
            )
        }
    }
}