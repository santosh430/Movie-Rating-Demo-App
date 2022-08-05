package com.example.movieratingapp.data

data class DataAPIClass(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)