package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.data.AffirmationDataSource
import com.jux.composeplayground.model.Affirmation
import com.jux.composeplayground.ui.components.DefaultPage
import com.jux.composeplayground.ui.components.DefaultScaffold
import com.jux.composeplayground.ui.components.SmallTitleWithText

class AffirmationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPage {
                AffirmationsList(affirmations = AffirmationDataSource.loadAll())
            }
        }
    }
}

@Composable
fun AffirmationsList(affirmations: List<Affirmation>, modifier: Modifier = Modifier) {
    DefaultScaffold(topAppBarTitleResId = R.string.affirmations) {
        LazyColumn(modifier = modifier, contentPadding = it) {
            items(affirmations) { affirmation ->
                AffirmationCard(affirmation = affirmation)
            }
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
    DefaultPage {
        AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    }
}