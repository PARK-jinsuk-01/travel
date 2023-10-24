package com.example.travel.model;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.Data;

@Entity
@Data
public class Member implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String email;
   private String name;
   private String pw;
   private String phone;
   public String birth;
   public String yy;
   public String mm;
   public String dd;

   
   
   LocalDateTime mData;
   @PrePersist
    public void prePersist() {
    this.mData = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
}
public boolean isEmpty() {
    return false;
}

@OneToMany(mappedBy = "member")
List<TravelBoard> baords = new ArrayList<>();

@OneToMany(mappedBy = "member")
List<TravelPlan> plans = new ArrayList<>();
}
 