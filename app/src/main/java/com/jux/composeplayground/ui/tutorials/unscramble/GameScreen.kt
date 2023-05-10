/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jux.composeplayground.ui.tutorials.unscramble

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jux.composeplayground.R
import com.jux.composeplayground.ui.components.ButtonWithText
import com.jux.composeplayground.ui.components.EditTextField
import com.jux.composeplayground.ui.components.MediumDisplayWithText
import com.jux.composeplayground.ui.components.MediumHeadlineWithText
import com.jux.composeplayground.ui.components.MediumTitleWithText
import com.jux.composeplayground.ui.components.OutlinedButtonWithText
import com.jux.composeplayground.ui.components.TextButtonWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun GameScreen(
    paddingValues: PaddingValues = PaddingValues(),
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    val defaultPadding = dimensionResource(R.dimen.component_default_spacing)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameLayout(
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            wordCount = gameUiState.currentWordCount,
            userGuess = gameViewModel.userGuess,
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            currentScrambledWord = gameUiState.currentScrambledWord,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(defaultPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding),
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ButtonWithText(
                modifier = Modifier.fillMaxWidth(),
                resId = R.string.submit
            ) { gameViewModel.checkUserGuess() }

            OutlinedButtonWithText(
                modifier = Modifier.fillMaxWidth(),
                resId = R.string.skip
            ) { gameViewModel.skipWord() }
        }

        GameStatus(score = gameUiState.score, modifier = Modifier.padding(20.dp))

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        MediumHeadlineWithText(
            text = stringResource(R.string.score, score),
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    wordCount: Int,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mediumPadding = dimensionResource(R.dimen.component_default_spacing)
    val largePadding = dimensionResource(R.dimen.component_default_spacing_large)

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.elevation_card))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(largePadding)
        ) {
            MediumTitleWithText(
                modifier = Modifier
                    .clip(shapes.small)
                    .background(colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End),
                text = stringResource(R.string.word_count, wordCount),
                color = colorScheme.onPrimary
            )
            MediumDisplayWithText(text = currentScrambledWord)
            MediumTitleWithText(R.string.instructions)
            EditTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userGuess,
                onValueChange = onUserGuessChanged,
                onKeyboardDone = onKeyboardDone,
                placeholderResId = when (isGuessWrong) {
                    true -> R.string.wrong_guess
                    else -> R.string.enter_your_word
                },
                isError = isGuessWrong
            )
        }
    }
}

/*
 * Creates and shows an AlertDialog with final score.
 */
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButtonWithText(R.string.exit) {
                activity.finish()
            }
        },
        confirmButton = {
            TextButtonWithText(R.string.play_again) {
                onPlayAgain()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    ComposePlaygroundTheme {
        GameScreen()
    }
}
