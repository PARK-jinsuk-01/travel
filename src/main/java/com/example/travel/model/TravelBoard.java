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
import lombok.ToString;

@ToString(exclude = { "member" })
@Entity
@Data
public class TravelBoard {
    @Id
    @GeneratedValue
    int id;
    String title;

    @Lob
    String content;

    LocalDateTime boardDate;
    int viewCount;

    @PrePersist
    public void prePersist() {
        this.boardDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

    @ManyToOne
    Member member;

    @OneToMany(mappedBy = "travelBoard")
    List<View> views = new ArrayList<>();

    @OneToMany(mappedBy = "travelBoard")
    List<TravelLike> likes = new ArrayList<>();
}
