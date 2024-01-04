package com.example.graphqlcountriesapp.domain

class GetCountryUseCase(
    private val countryClient: CountryClient,
) {

    suspend operator fun invoke(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}
