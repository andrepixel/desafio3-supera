package com.example.desafio3.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserItemResponseDTO;
import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Usecases.ItemListUseCase;
import com.example.desafio3.Usecases.ListUseCase;

@Service
public class ListService {
    private ListUseCase listUseCase;
    private ItemListUseCase itemListUseCase;

    public ListService(ListUseCase listUseCase, ItemListUseCase itemListUseCase) {
        this.listUseCase = listUseCase;
        this.itemListUseCase = itemListUseCase;
    }

    public UserListResponseDTO createUserList() {
        return listUseCase.createUserList();
    }

    public Optional<List<UserListEntity>> getAllUserList() {
        return listUseCase.getAllUserList();
    }

    public Optional<UserListResponseDTO> updateUserList(String id, UserListRequestDTO dto) {
        return listUseCase.updateUserList(id, dto);
    }

    public Optional<UserListResponseDTO> deleteUserListByID(String idUserList) {
        return listUseCase.deleteUserList(idUserList);
    }

    public Optional<UserItemResponseDTO> createItemList(UserItemRequestDTO dto) {
        Optional<UserListEntity> entityDatabase = listUseCase.findEntityById(UUID.fromString(dto.listID()));

        Optional<UserItemResponseDTO> userItemListDatabase = itemListUseCase.createUserItemList(entityDatabase, dto);

        return userItemListDatabase;
    }

    public Optional<List<UserItemEntity>> getAllItemList() {
        return itemListUseCase.getAllUserItemList();
    }

    public Optional<UserItemResponseDTO> updateItemList(String id, UserItemRequestDTO dto) {
        return itemListUseCase.updateUserItemList(id, dto);
    }

    public Optional<UserItemResponseDTO> deleteItemListByID(String idUserItemList) {
        return itemListUseCase.deleteUserItemList(idUserItemList);
    }
}
