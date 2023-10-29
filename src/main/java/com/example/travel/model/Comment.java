package com.example.travel.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.ToString;

@ToString(exclude = { "member", "travelBoard", "travelPlan" })
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue
    int id;

    String content;

    LocalDateTime commentTime;

    @PrePersist
    public void createdAt() {
        this.commentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    @ManyToOne
    Member member;

    @ManyToOne
    TravelBoard travelBoard;

    @ManyToOne
    TravelPlan travelPlan;

}
