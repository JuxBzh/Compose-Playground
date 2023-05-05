package com.jux.composeplayground.data

import com.jux.composeplayground.R
import com.jux.composeplayground.model.Tutorial
import com.jux.composeplayground.ui.tutorials.AffirmationsActivity
import com.jux.composeplayground.ui.tutorials.CoursesActivity
import com.jux.composeplayground.ui.tutorials.TipCalculatorActivity

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
        Tutorial(R.string.courses, R.drawable.outline_grid_on_24, CoursesActivity::class.java)
    )
}