package com.ahmrh.todoapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmrh.todoapp.data.model.Todo
import com.ahmrh.todoapp.ui.component.TodoItem

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel()
){

    var openAddTodoDialog by remember { mutableStateOf(false) }

    when{
        openAddTodoDialog -> {
            Dialog(
                onDismissRequest = {
                    openAddTodoDialog = false
                }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ){
                    Column (
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){


                        var title by remember { mutableStateOf("") }
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            placeholder = {Text("Title")}
                        )

                        var description by remember { mutableStateOf("") }
                        TextField(
                            value = description,
                            onValueChange = { description = it},
                            placeholder = {Text("Description")}
                        )
                        Button(
                            onClick = {
                                viewModel?.addTodo(
                                    Todo(
                                        title = title,
                                        description = description
                                    )
                                )
                                openAddTodoDialog = false
                            },
                        ) {
                            Text("Add Todo")
                            
                        }


                    }

                }
            }

        }
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openAddTodoDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    ){

        val todoListState = viewModel.todoList.collectAsState()

        when(todoListState.value){
            null -> { Loading() }
            else -> {
                val todoList = todoListState.value!!
                LazyColumn {
                    items(todoList) {
                        TodoItem(todo = it)
                    }
                }
            }

        }


    }

}

@Composable
fun Loading(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodoScreenPreview(){
    Surface {
        TodoScreen()
    }
}