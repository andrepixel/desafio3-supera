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

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserItemResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Services.ListService;

@RestController
@RequestMapping(path = "api/itens")
public class ItemListController {
    private ListService service;

    public ItemListController(ListService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserItemResponseDTO> createUserItemList(@RequestBody UserItemRequestDTO body) {
        Optional<UserItemResponseDTO> optional = service.createItemList(body);

        if (optional.isPresent()) {
            return ResponseEntity.accepted().body(optional.get());
        }

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    public ResponseEntity<List<UserItemEntity>> getAllUserItemList() {
        return ResponseEntity.ok().body(service.getAllItemList().get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserItemResponseDTO> updateUserItemList(@RequestParam String id,
            @RequestBody UserItemRequestDTO body) {
        Optional<UserItemResponseDTO> optional = service.updateItemList(id, body);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserItemResponseDTO> deleteUserItemList(@RequestParam String id) {
        Optional<UserItemResponseDTO> optional = service.deleteItemListByID(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
