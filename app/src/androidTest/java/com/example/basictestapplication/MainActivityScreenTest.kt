@file:OptIn(ExperimentalTestApi::class)

package com.example.basictestapplication

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.basictestapplication.screens.LazyListItemNode
import com.example.basictestapplication.screens.MainActivityScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class MainActivityScreenTest {

    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testCacheButtonIsDisplayed() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            cacheButton {
                assertIsDisplayed()
                assertTextContains("Set cache value")
            }
        }
    }

    @Test
    fun lazyListTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            list {
                childWith<LazyListItemNode> {
                    hasText("1")
                } perform {
                    assertTextEquals("1")
                }
            }
        }
    }

    @Test
    fun testFooterIsDisplayedAfterScrolling() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            list {
                assertIsDisplayed()
                performScrollToIndex(50)
            }
            footerText {
                assertIsDisplayed()
            }
        }
    }
}