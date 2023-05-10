package com.jux.composeplayground.data

import com.jux.composeplayground.data.words.WordsDataSource

class WordsData : WordsDataSource {
    override val allWords: Set<String>
        get() = setOf(
            "at",
            "sea",
            "home",
            "arise",
            "banana",
            "android",
            "birthday",
            "briefcase",
            "motorcycle",
            "cauliflower"
        )
    override val maxNumberOfWords: Int
        get() = 10
    override val scoreIncrease: Int
        get() = 20

    private val wordLengthMap: Map<Int, String> = allWords.associateBy({ it.length }, { it })

    internal fun getUnscrambledWord(scrambledWord: String) =
        wordLengthMap[scrambledWord.length].orEmpty()
}