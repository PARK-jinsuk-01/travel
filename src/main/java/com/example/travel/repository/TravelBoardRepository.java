package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.TravelBoard;

@Repository
public interface TravelBoardRepository extends JpaRepository<TravelBoard, Integer> {
    
}
