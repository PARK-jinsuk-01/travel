package com.example.travel.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

// 여행계획

@Entity
@Data
public class TravelPlan {
    @Id @GeneratedValue
     int id;
    String title;

    @Lob
    String content;

    LocalDateTime planData;
    
    @PrePersist
    public void prePersist() {
        this.planData = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

    @ManyToOne
    Member member;
}
