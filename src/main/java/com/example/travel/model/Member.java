package com.example.travel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Data;

@Entity
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String email;
   private String name;
   private String pw;
   private String phone;
   private Integer birth;
   
   LocalDateTime mData;
   @PrePersist
    public void prePersist() {
    this.mData = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
}
}
