package com.ahmrh.todoapp.data.model

data class Todo(
    val title: String,
    val description: String,
    val isDone: Boolean = false,
)

