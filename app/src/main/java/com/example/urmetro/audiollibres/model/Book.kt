package com.example.urmetro.audiollibres.model

import com.example.urmetro.audiollibres.model.Author

data class Book(
    val authors: List<Author>,
    val copyright_year: String,
    val description: String,
    val id: String,
    val language: String,
    val num_sections: String,
    val title: String,
    val totaltime: String,
    val totaltimesecs: Int,
    val url_librivox: String,
    val url_other: String,
    val url_project: String,
    val url_rss: String,
    val url_text_source: String,
    val url_zip_file: String
)