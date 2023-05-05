package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jux.composeplayground.R
import com.jux.composeplayground.data.TopicDataSource
import com.jux.composeplayground.model.Topic
import com.jux.composeplayground.ui.components.MediumLabelWithTextAndDrawableStart
import com.jux.composeplayground.ui.components.SmallBodyWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                CoursesWithTopics(topics = TopicDataSource.loadAll())
            }
        }
    }
}

@Composable
fun CoursesWithTopics(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(all = dimensionResource(id = R.dimen.component_default_spacing))
            .height(76.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_card)
        )
    ) {
        Row(modifier = modifier) {
            Image(
                modifier = modifier
                    .fillMaxHeight()
                    .width(76.dp),
                painter = painterResource(id = topic.imageResId),
                contentDescription = stringResource(
                    id = topic.nameResId
                ),
                contentScale = ContentScale.Crop
            )

            Column(modifier = modifier.padding(all = dimensionResource(id = R.dimen.component_default_spacing_large))) {
                SmallBodyWithText(resId = topic.nameResId)
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.component_default_spacing)))
                MediumLabelWithTextAndDrawableStart(
                    text = topic.numberOfCourses.toString(),
                    drawableResId = R.drawable.ic_grain
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicCardPreview() {
    ComposePlaygroundTheme {
        val topic = Topic(R.string.architecture, R.drawable.architecture, 100)
        TopicCard(topic)
    }
}