package com.hartwig.controller

import com.hartwig.model.Todo
import com.hartwig.repository.TodoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("todos")
class TodoController(private val todoRepository: TodoRepository) {

    @Get
    fun getAll(): List<Todo> {
        return todoRepository.findAll();
    }

    @Post
    fun save(todo: Todo): HttpResponse<Todo> {
        return HttpResponse.created(todoRepository.save(todo));
    }
}