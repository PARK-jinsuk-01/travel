package com.example.travel.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.travel.model.Hotplaces;

@Repository
public interface HotplaceRepository extends JpaRepository<Hotplaces, Integer> {

}

