package com.dpflsy.todoserver.service;

import com.dpflsy.todoserver.model.TodoModel;
import com.dpflsy.todoserver.model.TodoRequest;
import com.dpflsy.todoserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 아이템 추가
    // 특정 아이템 조회
    // 리스트 전체 목록 조회
    // 특정 아이템을 수정
    // 특정 아이템을 삭제
    // 전체 목록 삭제

    public TodoModel add(TodoRequest request) {
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(request.getTitle());
        todoModel.setOrder(request.getOrder());
        todoModel.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoModel);
    }

    public TodoModel searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest request) {
        TodoModel todoModel = this.searchById(id);
        if(request.getTitle() != null) {
            todoModel.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            todoModel.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoModel.setCompleted(request.getCompleted());
        }

        return this.todoRepository.save(todoModel);
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }
}
