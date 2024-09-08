package com.example.desafio3.Dtos;

import java.util.List;
import java.util.UUID;

public record UserListResponseDTO(UUID id, String title, List<UserItemResponseDTO> itens) {}

