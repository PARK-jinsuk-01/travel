package com.example.travel.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@ToString(exclude = {"board"})
@Entity
@Data
public class FileAtch {
    @Id @GeneratedValue
    int id;
    String  originalName;
    String  saveName;   
    

    @ManyToOne
    TravelBoard board;
}
