package com.example.desafio3.Usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.desafio3.Dtos.UserItemRequestDTO;
import com.example.desafio3.Dtos.UserListRequestDTO;
import com.example.desafio3.Dtos.UserListResponseDTO;
import com.example.desafio3.Entities.UserItemEntity;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Entities.UserListEntity;
import com.example.desafio3.Mappers.ListMapper;
import com.example.desafio3.Repositories.ListRepository;

@Transactional
@Service
public class ListUseCase {
    private ListRepository repository;
    private final ListMapper userMapper;

    public ListUseCase(ListMapper userMapper, ListRepository repository) {
        this.userMapper = userMapper;
        this.repository = repository;
    }

    public Optional<List<UserListEntity>> getAllUserList() {
        List<UserListEntity> entityListDatabase = repository.findAll();

        return Optional.of(entityListDatabase);
    }

    public Optional<UserListResponseDTO> deleteUserList(String idUserList) {
        Optional<UserListEntity> entityDatabase = repository.findById(UUID.fromString(idUserList));

        if (entityDatabase.isPresent()) {
            repository.deleteById(UUID.fromString(idUserList));

            UserListResponseDTO dto = userMapper.toUserListResponseDTO(entityDatabase.get());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public Optional<UserListResponseDTO> updateUserList(String id, UserListRequestDTO dto) {
        Optional<UserListEntity> optional = repository.findById(UUID.fromString(id));

        if (optional.isPresent()) {
            if (dto.title() != null) {
                optional.get().setTitle(dto.title());
            }

            if (dto.isPriority() != null) {
                optional.get().setIsPriority(dto.isPriority());
            }

            if (dto.itens() != null) {
                List<UserItemEntity> itens = optional.get().getItens();
                List<UserItemRequestDTO> itensDto = dto.itens();

                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).getTitle() != itensDto.get(i).title()) {
                        itens.get(i).setTitle(itensDto.get(i).title());
                    }

                    if (itens.get(i).getIsPriority() != itensDto.get(i).isPriority()) {
                        itens.get(i).setIsPriority(itensDto.get(i).isPriority());
                    }

                    if (itens.get(i).getListID() != UUID.fromString(itensDto.get(i).listID())) {
                        itens.get(i).setListID(UUID.fromString(itensDto.get(i).listID()));
                    }
                }

                optional.get().setItens(itens);
            }

            UserListEntity entity = repository.save(optional.get());

            UserListResponseDTO userListResponseDTO = userMapper.toUserListResponseDTO(entity);

            return Optional.of(userListResponseDTO);
        }

        return Optional.empty();
    }

    public UserListResponseDTO createUserList() {
        UserListEntity entity = new UserListEntity();
        entity.setTitle("Nova Tarefa");

        repository.save(entity);

        return userMapper.toUserListResponseDTO(entity);
    }

    public Optional<UserListEntity> findEntityById(UUID id) {
        Optional<UserListEntity> entity = repository.findById(id);

        return entity;
    }
}
