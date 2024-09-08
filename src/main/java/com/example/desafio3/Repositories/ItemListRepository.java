package com.example.desafio3.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio3.Entities.UserItemEntity;

public interface ItemListRepository extends JpaRepository<UserItemEntity, UUID> {
    
}