
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
public class View {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Member member;

    @ManyToOne
    TravelBoard travelBoard;

    @ManyToOne
    TravelPlan travelPlan;
    
    LocalDateTime viewTime;

     @PrePersist
    public void createdAt() {
    this.viewTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
    }
}
