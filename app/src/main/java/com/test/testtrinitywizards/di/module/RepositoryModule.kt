package com.test.testtrinitywizards.di.module

import com.test.testtrinitywizards.data.repository.DefaultContactRepository
import com.test.testtrinitywizards.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindContactRepositoryImpl(defaultContactRepository: DefaultContactRepository): ContactRepository
}