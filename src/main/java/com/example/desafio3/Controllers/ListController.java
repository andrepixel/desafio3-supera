package com.example.desafio3.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Services.ListService;

@RestController
@RequestMapping(path = "api/lists")
public class ListController {
    private ListService service;

    public ListController(ListService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserListResponseDTO> createUserList() {
        UserListResponseDTO userList = service.createUserList();

        return ResponseEntity.accepted().body(userList);
    }

    @GetMapping
    public ResponseEntity<List<UserListEntity>> getAllUserList() {
        return ResponseEntity.ok().body(service.getAllUserList().get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserListResponseDTO> updateUserList(@RequestParam String id, @RequestBody UserListRequestDTO body) {
        Optional<UserListResponseDTO> optional = service.updateUserList(id, body);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserListResponseDTO> deleteUserList(@RequestParam String id) {
        Optional<UserListResponseDTO> optional = service.deleteUserListByID(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
