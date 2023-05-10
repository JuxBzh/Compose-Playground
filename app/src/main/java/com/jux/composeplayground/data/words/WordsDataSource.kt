package com.jux.composeplayground.data.words

interface WordsDataSource {
    val allWords: Set<String>
    val maxNumberOfWords: Int
    val scoreIncrease: Int
}