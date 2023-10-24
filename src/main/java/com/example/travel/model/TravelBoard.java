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
import lombok.ToString;

@ToString(exclude = {"member"})
@Entity
@Data
public class TravelBoard {
    @Id @GeneratedValue
    int id;
    String title;

    @Lob
    String content;
    

    LocalDateTime boardDate;

    @PrePersist
    public void prePersist() {
        this.boardDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }
    
    @ManyToOne
    Member member;


}
