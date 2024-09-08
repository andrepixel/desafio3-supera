package com.example.desafio3.Mappers;

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserItemResponseDTO;
import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Entities.UserListEntity;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T19:06:32-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ListMapperImpl implements ListMapper {

    @Override
    public UserListEntity toUserListEntity(UserListRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserListEntity userListEntity = new UserListEntity();

        return userListEntity;
    }

    @Override
    public UserListResponseDTO toUserListResponseDTO(UserListEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        List<UserItemResponseDTO> itens = null;

        UserListResponseDTO userListResponseDTO = new UserListResponseDTO( id, title, itens );

        return userListResponseDTO;
    }

    @Override
    public UserItemEntity toUserItemEntity(UserItemRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserItemEntity userItemEntity = new UserItemEntity();

        return userItemEntity;
    }

    @Override
    public UserItemResponseDTO toUserItemResponseDTO(UserItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        Boolean isPriority = null;
        String listID = null;

        UserItemResponseDTO userItemResponseDTO = new UserItemResponseDTO( id, title, isPriority, listID );

        return userItemResponseDTO;
    }
}
