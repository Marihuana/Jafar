package kr.yooreka.jafar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.yooreka.jafar.data.local.CareerRepositoryImpl
import kr.yooreka.jafar.data.repository.PortfolioRepositoryImpl
import kr.yooreka.jafar.data.local.ProfileRepositoryImpl
import kr.yooreka.jafar.data.repository.AppInfoRepositoryImpl
import kr.yooreka.jafar.data.repository.DisplayRepositoryImpl
import kr.yooreka.jafar.data.repository.PremiumRepositoryImpl
import kr.yooreka.jafar.domain.repository.AppInfoRepository
import kr.yooreka.jafar.domain.repository.CareerRepository
import kr.yooreka.jafar.domain.repository.DisplayRepository
import kr.yooreka.jafar.domain.repository.PortfolioRepository
import kr.yooreka.jafar.domain.repository.PremiumRepository
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

    @Binds
    @Singleton
    abstract fun bindDisplayRepository(
        displayRepositoryImpl: DisplayRepositoryImpl
    ): DisplayRepository

    @Binds
    @Singleton
    abstract fun bindPremiumRepository(
        premiumRepositoryImpl: PremiumRepositoryImpl
    ): PremiumRepository

    @Binds
    @Singleton
    abstract fun bindAppInfoRepository(
        appInfoRepositoryImpl: AppInfoRepositoryImpl
    ): AppInfoRepository
}
