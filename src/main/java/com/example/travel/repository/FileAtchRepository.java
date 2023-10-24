package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.FileAtch;

@Repository
public interface FileAtchRepository extends JpaRepository<FileAtch, Integer> {
    
}
