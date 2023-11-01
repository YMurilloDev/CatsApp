package com.example.catsapp.domain

import com.example.catsapp.domain.Cat

interface CatsRepository {
    suspend fun fetchCats(): List<Cat>
}
