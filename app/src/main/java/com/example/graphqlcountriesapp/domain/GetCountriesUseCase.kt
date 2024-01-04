package com.example.graphqlcountriesapp.domain

class GetCountriesUseCase(
    private val countryClient: CountryClient,
) {

    suspend operator fun invoke(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}
