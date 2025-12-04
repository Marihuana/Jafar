package kr.yooreka.jafar.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.yooreka.jafar.data.local.CareerRepositoryImpl
import kr.yooreka.jafar.data.local.PortfolioRepositoryImpl
import kr.yooreka.jafar.data.local.ProfileRepositoryImpl
import kr.yooreka.jafar.domain.repository.CareerRepository
import kr.yooreka.jafar.domain.repository.PortfolioRepository
import kr.yooreka.jafar.domain.repository.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindCareerRepository(
        careerRepositoryImpl: CareerRepositoryImpl
    ): CareerRepository

    @Binds
    @Singleton
    abstract fun bindPortfolioRepository(
        portfolioRepositoryImpl: PortfolioRepositoryImpl
    ): PortfolioRepository
}
