package kr.yooreka.jafar.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.yooreka.jafar.data.datasource.local.DisplayPreferencesDataSource
import kr.yooreka.jafar.data.datasource.local.displayDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideDisplayDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.displayDataStore

    @Provides
    @Singleton
    fun provideDisplayPreferencesDataSource(
        dataStore: DataStore<Preferences>
    ): DisplayPreferencesDataSource {
        return DisplayPreferencesDataSource(dataStore)
    }

    // TODO: 추후 Room Database / DAO / 다른 LocalDataSource도 여기로 추가
}