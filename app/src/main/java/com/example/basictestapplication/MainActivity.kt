package com.example.basictestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basictestapplication.ui.theme.BasicTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
                    AppStuff(vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppStuff(vm: MainViewModel) {

    // val coroutineScope = rememberCoroutineScope()

    val data = vm.data.collectAsState(initial = emptyList()).value

    Data(data)
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = { vm.setDataToCache() }) {
        Text("Set cache value")
    }
}

@Composable
fun Data(text: List<TodoList?>, modifier: Modifier = Modifier) {
    Text(
        text = "data: $text",
        modifier = modifier
    )

}