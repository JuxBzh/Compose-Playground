package com.jux.composeplayground.ui.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.jux.composeplayground.ui.components.SmallBodyWithText
import com.jux.composeplayground.ui.components.SmallTitleWithText
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
    var expanded by remember { mutableStateOf(false) }
    val cardColor by animateColorAsState(
        targetValue = when (expanded) {
            true -> MaterialTheme.colorScheme.tertiaryContainer
            else -> MaterialTheme.colorScheme.secondaryContainer
        }
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DogIcon(dogImage = dog.imageResId)
                DogDescription(dog = dog)
                Spacer(modifier = modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = { expanded = expanded.not() })
            }
            if (expanded) {
                DogHobbies(
                    hobbiesResId = dog.hobbies,
                    modifier.padding(top = dimensionResource(id = R.dimen.component_default_spacing))
                )
            }
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

@Composable
fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            painter = when (expanded) {
                true -> painterResource(id = R.drawable.outline_expand_less_24)
                else -> painterResource(id = R.drawable.outline_expand_more_24)
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DogHobbies(@StringRes hobbiesResId: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SmallTitleWithText(text = stringResource(id = R.string.about))
        SmallBodyWithText(resId = hobbiesResId)
    }
}

@Preview
@Composable
fun WoofPreview() {
    ComposePlaygroundTheme(darkTheme = true) {
        WoofList(dogs = DogDataSource.loadAll())
    }
}