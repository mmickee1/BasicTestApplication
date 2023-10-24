package com.example.basictestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basictestapplication.ui.theme.BasicTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel()
                    MainScreenContent(vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreenContent(vm: MainViewModel) {
    val data = vm.data.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .testTag("MainScreen")
            .fillMaxSize()
    ) {
        Text(
            text = "data: $data",
            modifier = Modifier.testTag("DataTextField")
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("CacheButton")
                .wrapContentHeight(),
            onClick = { vm.setDataToCache() }) {
            Text("Set cache value")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("SampleList")
        ) {
            items(50) { index ->
                Text(text = "${index + 1}", Modifier.testTag("position=$index"))
            }
            item {
                Text("Footer", modifier = Modifier.testTag("FooterTextField"))
            }
        }
    }
}
