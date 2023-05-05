package com.jux.composeplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jux.composeplayground.data.TutorialDataSource
import com.jux.composeplayground.model.Tutorial
import com.jux.composeplayground.ui.components.LargeTitleWithText
import com.jux.composeplayground.ui.theme.ComposePlaygroundTheme
import com.jux.composeplayground.ui.tutorials.TipCalculatorActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlaygroundWithTutorials(tutorials = TutorialDataSource.loadAll()) {
                        val intent = Intent(this, it.activity)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun PlaygroundWithTutorials(
    tutorials: List<Tutorial>,
    modifier: Modifier = Modifier,
    onTutorialClick: (Tutorial) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tutorials) { tutorial ->
            TutorialRow(
                tutorial = tutorial,
                modifier = modifier.clickable { onTutorialClick.invoke(tutorial) })
            Divider(
                modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.component_default_spacing_large)),
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun TutorialRow(tutorial: Tutorial, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = dimensionResource(id = R.dimen.component_default_spacing_large)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = tutorial.iconResId),
            contentDescription = stringResource(id = tutorial.nameResId)
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.component_default_spacing)))
        LargeTitleWithText(resId = tutorial.nameResId)
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialRowPreview() {
    ComposePlaygroundTheme {
        val tutorial = Tutorial(
            R.string.tip_calculator,
            R.drawable.outline_calculate_24,
            TipCalculatorActivity::class.java
        )
        TutorialRow(tutorial = tutorial)
    }
}