package com.example.travel.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.Data;

// 여행계획

@Entity
@Data
public class TravelPlan {
    @Id
    @GeneratedValue
    int id;

    String title;

    @Lob
    String content;

    int viewCount;

    LocalDateTime planDate;

    @PrePersist
    public void prePersist() {
        this.planDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

    @ManyToOne
    Member member;
    @OneToMany(mappedBy = "travelPlan")
    List<View> views = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    List<TravelLike> likes = new ArrayList<>();
}
