package com.example.basictestapplication.glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.Text
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class GlanceDataStoreWidget : GlanceAppWidget() {

    companion object {
        private val SMALL_SQUARE = DpSize(100.dp, 100.dp)
        private val HORIZONTAL_RECTANGLE = DpSize(250.dp, 100.dp)
        private val BIG_SQUARE = DpSize(250.dp, 250.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(
            SMALL_SQUARE,
            HORIZONTAL_RECTANGLE,
            BIG_SQUARE
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            MyContent()
        }
    }


    @Composable
    private fun MyContent() {
        // Size will be one of the sizes defined above.
        val size = LocalSize.current
        val context = LocalContext.current
        val dataStore = GlanceDataProvider(context)
        val glanceText = runBlocking { dataStore.glanceTextFlow.firstOrNull() }

        Column {
            if (size.height >= BIG_SQUARE.height) {
                Text(text = "Big area. Text: $glanceText", modifier = GlanceModifier.padding(12.dp))
            }


            if (size.width >= HORIZONTAL_RECTANGLE.width) {
                Text("Medium area. Text: $glanceText")
            }

            if (size.height >= BIG_SQUARE.height) {
                Text(text ="Small area. Text: $glanceText")
            }
        }
    }
}