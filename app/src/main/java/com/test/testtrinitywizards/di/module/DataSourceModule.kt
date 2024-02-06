package com.test.testtrinitywizards.di.module

import android.app.Application
import com.test.testtrinitywizards.data.localasset.ContactLocalAssetSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideContactLocalAssetSource(
        application: Application
    ): ContactLocalAssetSource {
        return ContactLocalAssetSource(application.applicationContext);
    }
}