package com.example.travel.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

// 여행계획

@Entity
@Data
public class TravelPlan {
    @Id @GeneratedValue
    int id;
    String name;
    String email;

    LocalDateTime planTime;
    
    @PrePersist
    public void prePersist() {
        this.planTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @ManyToOne
    Member member;
}
