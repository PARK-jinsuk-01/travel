package com.example.travel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@ToString(exclude = { "member", "travelBoard", "travelPlan" })
@Entity
@Data
public class TravelLike {
    
    @Id @GeneratedValue
    int id;

    @ManyToOne  
    Member member;

    @ManyToOne
    TravelBoard travelBoard; 

    @ManyToOne
    TravelPlan travelPlan;

}
