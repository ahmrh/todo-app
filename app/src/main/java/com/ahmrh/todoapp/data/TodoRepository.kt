package com.ahmrh.todoapp.data

import com.ahmrh.todoapp.data.database.AppDatabase
import com.ahmrh.todoapp.data.database.dao.TodoDao
import com.ahmrh.todoapp.data.database.entity.TodoEntity
import com.ahmrh.todoapp.data.model.Todo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    suspend fun getAllTodo(): Result<List<Todo>> {
        return try {
            val todoEntities = todoDao.getAllTodos()
            val todos = todoEntities.map{ todoEntity ->
                Todo(
                    title = todoEntity.title,
                    description = todoEntity.description ?: "",
                    isDone = todoEntity.isDone
                )
            }
            Result.success(todos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addTodo(todo: Todo): Result<Unit> {
        return try {
            todoDao.insertTodo(
                TodoEntity(
                    title = todo.title,
                    description = todo.description,
                    isDone = todo.isDone
                )
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteTodoById(id: Int): Result<Unit> {
        return try {
            todoDao.deleteTodoById(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}