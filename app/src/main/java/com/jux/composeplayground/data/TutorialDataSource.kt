package com.jux.composeplayground.data

import com.jux.composeplayground.R
import com.jux.composeplayground.model.Tutorial
import com.jux.composeplayground.ui.tutorials.AffirmationsActivity
import com.jux.composeplayground.ui.tutorials.CoursesActivity
import com.jux.composeplayground.ui.tutorials.WoofActivity
import com.jux.composeplayground.ui.tutorials.cupcake.CupcakeActivity
import com.jux.composeplayground.ui.tutorials.tip.TipCalculatorActivity
import com.jux.composeplayground.ui.tutorials.unscramble.UnscrambleActivity

object TutorialDataSource {

    fun loadAll(): List<Tutorial> = listOf(
        Tutorial(
            R.string.tip_calculator,
            R.drawable.outline_calculate_24,
            TipCalculatorActivity::class.java
        ),
        Tutorial(
            R.string.affirmations,
            R.drawable.outline_format_list_bulleted_24,
            AffirmationsActivity::class.java
        ),
        Tutorial(R.string.courses, R.drawable.outline_grid_on_24, CoursesActivity::class.java),
        Tutorial(R.string.woof, R.drawable.outline_animation_24, WoofActivity::class.java),
        Tutorial(
            R.string.unscramble_activity,
            R.drawable.outline_game_24,
            UnscrambleActivity::class.java
        ),
        Tutorial(R.string.cupcakes, R.drawable.outline_navigation_24, CupcakeActivity::class.java)
    )
}