package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.TravelLike;

import java.util.Optional;


@Repository
public interface TravelLikeRepository extends JpaRepository<TravelLike, Integer> {
    Optional<TravelLike>findByMemberIdAndTravelBoardId(Integer  memberId, Integer  travelBoardId);
    Optional<TravelLike>findByMemberIdAndTravelPlanId(Integer  memberId, Integer  travelPlanId);
    Optional<TravelLike>countByTravelPlanId(Integer travelPlanId);   
    Optional<TravelLike>countByTravelBoardId(Integer travelBoardId);   

}
