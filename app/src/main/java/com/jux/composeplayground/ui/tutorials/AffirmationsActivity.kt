package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.data.AffirmationDataSource
import com.jux.composeplayground.model.Affirmation
import com.jux.composeplayground.ui.components.EditNumberField
import com.jux.composeplayground.ui.components.LargeTitleWithText
import com.jux.composeplayground.ui.components.SmallTitleWithText
import com.jux.composeplayground.ui.components.SwitchWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme
import java.text.NumberFormat
import kotlin.math.ceil

class AffirmationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                AffirmationsList(affirmations = AffirmationDataSource.loadAll())
            }
        }
    }
}

@Composable
fun AffirmationsList(affirmations: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmations) { affirmation ->
            AffirmationCard(affirmation = affirmation)
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(all = dimensionResource(id = R.dimen.component_default_spacing)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_card)
        )
    ) {
        Column(modifier = modifier) {
            Image(
                modifier = modifier.fillMaxWidth(),
                painter = painterResource(id = affirmation.drawableResId),
                contentDescription = stringResource(id = affirmation.stringResId),
                contentScale = ContentScale.Crop
            )
            SmallTitleWithText(
                text = stringResource(id = affirmation.stringResId), modifier = modifier.padding(
                    all = dimensionResource(id = R.dimen.component_default_spacing)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationsPreview() {
    ComposePlaygroundTheme {
        AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    }
}