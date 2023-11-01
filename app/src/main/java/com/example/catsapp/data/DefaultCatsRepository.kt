package com.example.catsapp.data

import com.example.catsapp.domain.Cat
import com.example.catsapp.domain.CatsRepository

class DefaultCatsRepository(
    private val api: CatsApi
) : CatsRepository {
    override suspend fun fetchCats(): List<Cat> {
        return try {
            api.fetchCats().toCats()
        } catch (ex: Exception) {
            emptyList()
        }
    }
}
private fun List<CatsResponse>.toCats(): List<Cat> = this.map {
    Cat(
        id = it.id,
        breedName = it.breedName,
        origin = it.origin,
        referenceId = it.referenceId,
        intelligence = it.intelligence
    )
}