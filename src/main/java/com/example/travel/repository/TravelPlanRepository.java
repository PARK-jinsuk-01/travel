package com.example.travel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.TravelPlan;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Integer> {
}
 