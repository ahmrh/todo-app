package com.ahmrh.todoapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ahmrh.todoapp.data.model.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onDone:() -> Unit= {},
    onDelete:() -> Unit= {}
){

    var isDone by remember { mutableStateOf(todo.isDone) }

    ListItem(
        headlineContent = { Text(todo.title) },
        supportingContent = { Text(todo.description) },
        leadingContent = { Icon(Icons.Filled.Face, contentDescription = null) },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        trailingContent = {
            Checkbox(
                checked = isDone,
                onCheckedChange = { isDone = !isDone }
            )
        },
        modifier = Modifier
            .clickable{

            }
    )
}