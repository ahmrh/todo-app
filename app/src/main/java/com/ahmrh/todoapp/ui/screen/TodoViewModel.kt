package com.ahmrh.todoapp.ui.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmrh.todoapp.data.TodoRepository
import com.ahmrh.todoapp.data.database.entity.TodoEntity
import com.ahmrh.todoapp.data.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel(){

    private var _todoList: MutableStateFlow<List<Todo>?> = MutableStateFlow(null)
    val todoList: StateFlow<List<Todo>?>
        get() = _todoList

    init {
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch {

            val result = todoRepository.getAllTodo()
            _todoList.value = result.getOrNull()
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch {

            val result = todoRepository.addTodo(todo)
            if(result.isFailure) {
                Log.e(TAG, "Error: ${result.exceptionOrNull()}")
            }
            getTodos()
        }
    }

    fun deleteTodo(id: Int){
        viewModelScope.launch {

            val result = todoRepository.deleteTodoById(id)
            if(result.isFailure) {
                Log.e(TAG, "Error: ${result.exceptionOrNull()}")
            }
            getTodos()
        }
    }

    companion object{
        const val TAG = "TodoViewModel"
    }

}