package com.jux.composeplayground.ui.tutorials.unscramble

import com.jux.composeplayground.data.WordsData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameViewModelTest {
    private val dataSource = WordsData()
    private lateinit var viewModel: GameViewModel

    @BeforeEach
    fun before() {
        viewModel = GameViewModel(dataSource = dataSource)
    }

    @Test
    @DisplayName("Given the game gets initialized")
    fun initialState() {
        // GIVEN
        val currentUiState = viewModel.uiState.value

        // THEN
        assertEquals(0, currentUiState.score, "Wrong score")
        assertFalse(currentUiState.isGameOver, "The game is not over")
        assertEquals(1, currentUiState.currentWordCount, "Wrong number of word")
        assertFalse(currentUiState.isGuessedWordWrong, "Guessed word is correct")
    }

    @Test
    @DisplayName("Given the correct word is guessed")
    fun correctWordGuessed() {
        // GIVEN
        val currentUiState = viewModel.uiState.value
        val correctWord = dataSource.getUnscrambledWord(currentUiState.currentScrambledWord)

        // WHEN
        viewModel.updateUserGuess(correctWord)
        viewModel.checkUserGuess()

        // THEN
        val newState = viewModel.uiState.value
        assertEquals(20, newState.score, "Wrong score")
        assertFalse(newState.isGameOver, "The game is not over")
        assertEquals(2, newState.currentWordCount, "Wrong number of word")
        assertFalse(newState.isGuessedWordWrong, "Guessed word is correct")
    }

    @Test
    @DisplayName("Given the current word is skipped")
    fun skipWord() {
        // WHEN
        viewModel.skipWord()

        // THEN
        val newState = viewModel.uiState.value
        assertEquals(0, newState.score, "The score shouldn't have been updated")
        assertFalse(newState.isGameOver, "The game is not over")
        assertEquals(2, newState.currentWordCount, "Wrong number of word")
        assertFalse(newState.isGuessedWordWrong, "Guessed word is correct")
    }
}