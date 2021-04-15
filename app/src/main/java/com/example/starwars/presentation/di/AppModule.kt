package com.example.starwars.presentation.di
//
import com.example.starwars.data.mapper.FilmDetailsResponseMapper
import com.example.starwars.data.mapper.PlanetDetailsResponseMapper
import com.example.starwars.data.mapper.SearchCharacterResponseMapper
import com.example.starwars.data.mapper.SpecieDetailsResponseMapper
import com.example.starwars.data.repository.FilmDetailsRepositoryImpl
import com.example.starwars.data.repository.PlanetDetailsRepositoryImpl
import com.example.starwars.data.repository.SearchCharacterRepositoryImpl
import com.example.starwars.data.repository.SpecieDetailsRepositoryImpl
import com.example.starwars.data.source.DataSource
import com.example.starwars.data.source.remote.RemoteDataSourceImpl
import com.example.starwars.data.source.remote.service.ApiService
import com.example.starwars.domain.repository.FilmRepository
import com.example.starwars.domain.repository.PlanetRepository
import com.example.starwars.domain.repository.SearchCharacterRepository
import com.example.starwars.domain.repository.SpecieRepository
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import com.example.starwars.domain.usecase.SearchCharacterUseCase
import com.example.starwars.presentation.mapper.CharacterDetailMapper
import com.example.starwars.presentation.mapper.CharacterInfoMapper
import com.example.starwars.presentation.mapper.CharacterNameMapper
import com.example.starwars.presentation.ui.detail.DetailsViewModel
import com.example.starwars.presentation.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): DataSource.RemoteDataSource =
        RemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideSearchCharacteResponseMapper() = SearchCharacterResponseMapper()

    @Singleton
    @Provides
    fun providePlanetResponseMapper() = PlanetDetailsResponseMapper()

    @Singleton
    @Provides
    fun provideFilmResponseMapper() = FilmDetailsResponseMapper()

    @Singleton
    @Provides
    fun provideSpecieResponseMapper() = SpecieDetailsResponseMapper()

    @Singleton
    @Provides
    fun provideCharacterSearchRepository(
        remoteDataSource: DataSource.RemoteDataSource, responseMapper: SearchCharacterResponseMapper
    ): SearchCharacterRepository =
        SearchCharacterRepositoryImpl(responseMapper, remoteDataSource)

    @Singleton
    @Provides
    fun providePlanetDetailsRepository(
        remoteDataSource: DataSource.RemoteDataSource, responseMapper: PlanetDetailsResponseMapper
    ): PlanetRepository =
        PlanetDetailsRepositoryImpl(responseMapper, remoteDataSource)

    @Singleton
    @Provides
    fun provideFilmDetailsRepository(
        remoteDataSource: DataSource.RemoteDataSource, responseMapper: FilmDetailsResponseMapper
    ): FilmRepository =
        FilmDetailsRepositoryImpl(responseMapper, remoteDataSource)

    @Singleton
    @Provides
    fun provideSpecieDetailRepository(
        remoteDataSource: DataSource.RemoteDataSource, responseMapper: SpecieDetailsResponseMapper
    ): SpecieRepository =
        SpecieDetailsRepositoryImpl(responseMapper, remoteDataSource)

    @Singleton
    @Provides
    fun provideSearchCharacterUseCase(repository: SearchCharacterRepository): SearchCharacterUseCase =
        SearchCharacterUseCase(repository)

    @Singleton
    @Provides
    fun provideGetPlanetDetailsUseCase(repository: PlanetRepository): GetPlanetDetailsUseCase =
        GetPlanetDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetFilmDetailsUseCase(repository: FilmRepository): GetFilmDetailsUseCase =
        GetFilmDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetSpecieDetailsUseCase(repository: SpecieRepository): GetSpecieDetailsUseCase =
        GetSpecieDetailsUseCase(repository)


    @Singleton
    @Provides
    fun provideCharacterDetailModelMapper() = CharacterInfoMapper()

    @Singleton
    @Provides
    fun provideCharacterNameModelMapper() = CharacterNameMapper()

    @Singleton
    @Provides
    fun provideCharacterDetailMapper() = CharacterDetailMapper()

    @Singleton
    @Provides
    fun provideHomeViewModel(
        searchCharacterUseCase: SearchCharacterUseCase,
        characterInfoMapper: CharacterInfoMapper,
        characterNameMapper: CharacterNameMapper
    ): HomeViewModel =
        HomeViewModel(null, searchCharacterUseCase, characterInfoMapper, characterNameMapper)

    @Provides
    fun provideDetailsViewModel(
        getSpecieDetailsUseCase: GetSpecieDetailsUseCase,
        getFilmDetailsUseCase: GetFilmDetailsUseCase,
        getPlanetDetailsUseCase: GetPlanetDetailsUseCase,
        characterDetailMapper: CharacterDetailMapper
    ): DetailsViewModel =
        DetailsViewModel(
            null,
            getSpecieDetailsUseCase,
            getFilmDetailsUseCase,
            getPlanetDetailsUseCase,
            characterDetailMapper
        )
}