package com.example.travel.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.Comment;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment>findByMemberAndTravelPlanAndCommentTime(Integer member, Integer travelPlan, Date commentTime);
    Optional<Comment>findByMemberIdAndTravelPlanId(Integer member, Integer travelPlan);
    List<Comment>findByTravelPlanIdOrderByIdDesc(Integer travelPlan);
    List<Comment>findByTravelBoardIdOrderByIdDesc(Integer travelBoard);
    // Optional<Comment>findByCommentIdAndMemberId(Integer memberId);
    // Optional<Comment>findByMemberId(Integer memberId);

                  
}
