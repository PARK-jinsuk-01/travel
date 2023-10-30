package com.example.travel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Hotplaces {
    @Id
    int id;
    String title;
    String ctprvn_nm;
    String signgu_nm;
    String legaldong_nm;
    Double longitude;
    Double latitude;
} 

