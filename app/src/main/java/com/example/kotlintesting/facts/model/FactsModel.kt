package com.example.kotlintesting.facts.model

data class FactsModel(
    val rows: List<FactsRowModel>,
    val title: String
)

data class FactsRowModel(
    val description: String?,
    val imageHref: String?,
    val title: String
)