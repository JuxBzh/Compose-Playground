package com.jux.composeplayground.data

import com.jux.composeplayground.R
import com.jux.composeplayground.model.Topic

object TopicDataSource {

    fun loadAll(): List<Topic> = listOf(
        Topic(R.string.architecture, R.drawable.architecture, 100),
        Topic(R.string.business, R.drawable.business, 103),
        Topic(R.string.crafts, R.drawable.crafts, 105),
        Topic(R.string.culinary, R.drawable.culinary, 107),
        Topic(R.string.design, R.drawable.design, 110),
        Topic(R.string.drawing, R.drawable.drawing, 120),
        Topic(R.string.fashion, R.drawable.fashion, 130),
        Topic(R.string.film, R.drawable.film, 140),
        Topic(R.string.gaming, R.drawable.gaming, 150),
        Topic(R.string.lifestyle, R.drawable.lifestyle, 167),
        Topic(R.string.music, R.drawable.music, 176),
        Topic(R.string.painting, R.drawable.painting, 230),
        Topic(R.string.photography, R.drawable.photography, 564),
        Topic(R.string.tech, R.drawable.tech, 333)
    )
}