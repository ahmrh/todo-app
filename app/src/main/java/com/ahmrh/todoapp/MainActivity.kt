package com.ahmrh.todoapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmrh.todoapp.data.TodoRepository
import com.ahmrh.todoapp.ui.screen.TodoScreen
import com.ahmrh.todoapp.ui.screen.TodoViewModel
import com.ahmrh.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                Surface {
                    TodoScreen()
                }
            }
        }
    }
}
