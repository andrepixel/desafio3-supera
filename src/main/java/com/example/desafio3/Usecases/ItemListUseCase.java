package com.example.desafio3.Usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserItemResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Mappers.ListMapper;
import com.example.desafio3.Repositories.ItemListRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ItemListUseCase {
    private final ListMapper userMapper;
    private ItemListRepository repository;

    public ItemListUseCase(ListMapper userMapper, ItemListRepository repository) {
        this.userMapper = userMapper;
        this.repository = repository;
    }

    public Optional<List<UserItemEntity>> getAllUserItemList() {
        return Optional.of(repository.findAll());
    }

    public Optional<UserItemResponseDTO> deleteUserItemList(String idUserItemList) {
        Optional<UserItemEntity> entity = repository.findById(UUID.fromString(idUserItemList));

        if (entity.isPresent()) {
            repository.deleteById(UUID.fromString(idUserItemList));

            UserItemResponseDTO userItemResponseDTO = userMapper.toUserItemResponseDTO(entity.get());

            return Optional.of(userItemResponseDTO);
        }

        return Optional.empty();
    }

    public Optional<UserItemResponseDTO> updateUserItemList(String id, UserItemRequestDTO dto) {
        Optional<UserItemEntity> optional = repository.findById(UUID.fromString(id));

        if (optional.isPresent()) {
            if (dto.title() != null) {
                optional.get().setTitle(dto.title());
            }
            
            if (dto.isPriority() != null) {
                optional.get().setIsPriority(dto.isPriority());
            }

            UserItemEntity userItemEntityDatabase = repository.save(optional.get());

            UserItemResponseDTO userItemResponseDTO = userMapper.toUserItemResponseDTO(userItemEntityDatabase);

            return Optional.of(userItemResponseDTO);
        }

        return Optional.empty();
    }

    public Optional<UserItemResponseDTO> createUserItemList(Optional<UserListEntity> optional,
            UserItemRequestDTO userItemRequestDTO) {
        if (optional.isPresent()) {
            UserItemEntity entity = userMapper.toUserItemEntity(userItemRequestDTO);

            repository.save(entity);

            UserItemResponseDTO responseDTO = userMapper.toUserItemResponseDTO(entity);

            return Optional.of(responseDTO);
        }

        return Optional.empty();
    }
}
