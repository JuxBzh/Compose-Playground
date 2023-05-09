package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jux.composeplayground.R
import com.jux.composeplayground.data.DogDataSource
import com.jux.composeplayground.model.Dog
import com.jux.composeplayground.ui.components.DefaultScaffold
import com.jux.composeplayground.ui.components.MediumBodyWithText
import com.jux.composeplayground.ui.components.MediumTitleWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme
import com.jux.composeplayground.ui.theme.shapeCircle

class WoofActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                WoofList(dogs = DogDataSource.loadAll())
            }
        }
    }
}

@Composable
fun WoofList(dogs: List<Dog>, modifier: Modifier = Modifier) {
    DefaultScaffold(modifier = modifier, topAppBarTitleResId = R.string.woof_activity) {
        LazyColumn(modifier = modifier, contentPadding = it) {
            items(dogs) { dog ->
                DogItem(
                    dog = dog,
                    modifier = modifier.padding(dimensionResource(id = R.dimen.component_default_spacing))
                )
            }
        }
    }
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.component_default_spacing)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DogIcon(dogImage = dog.imageResId)
            DogDescription(dog = dog)
        }
    }
}

@Composable
fun DogIcon(@DrawableRes dogImage: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .padding(all = dimensionResource(id = R.dimen.component_default_spacing))
            .clip(shapeCircle),
        painter = painterResource(id = dogImage),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun DogDescription(dog: Dog, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MediumTitleWithText(resId = dog.nameResId)
        MediumBodyWithText(text = stringResource(id = R.string.years_old, dog.age))
    }
}

@Preview
@Composable
fun WoofPreview() {
    ComposePlaygroundTheme(darkTheme = true) {
        WoofList(dogs = DogDataSource.loadAll())
    }
}