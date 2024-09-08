package com.example.desafio3.Mappers;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserItemResponseDTO;
import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Entities.UserListEntity;

@Mapper(componentModel = "spring")
public interface ListMapper {
    UserListEntity toUserListEntity(UserListRequestDTO dto);

    UserListResponseDTO toUserListResponseDTO(UserListEntity entity);

    UserItemEntity toUserItemEntity(UserItemRequestDTO dto);

    UserItemResponseDTO toUserItemResponseDTO(UserItemEntity entity);
}
