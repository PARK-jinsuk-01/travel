package com.example.travel.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.Member;
import com.example.travel.model.TravelBoard;
import com.example.travel.model.TravelPlan;
import com.example.travel.model.View;

@Repository
public interface ViewRepository extends JpaRepository<View, Long>{
    Optional<View>findByMemberAndTravelBoardAndViewTimeBetween(Member member, TravelBoard travelBoard, LocalDateTime startDate, LocalDateTime endDate);
    Optional<View>findByMemberAndTravelPlanAndViewTimeBetween(Member member, TravelPlan travelPlan, LocalDateTime startDate, LocalDateTime endDate);
}
 