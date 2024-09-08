package com.example.desafio3.Dtos;

import java.util.UUID;

public record UserItemResponseDTO(UUID id, String title, Boolean isPriority, String listID) {}
