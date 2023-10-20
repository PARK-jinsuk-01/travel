package com.example.travel.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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




}
 