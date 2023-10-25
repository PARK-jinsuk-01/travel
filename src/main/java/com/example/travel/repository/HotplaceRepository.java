package com.example.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.travel.model.Hotplaces;

@Repository
public interface HotplaceRepository extends JpaRepository<Hotplaces, Integer> {

    // 여기에 필요한 메서드를 추가할 수 있습니다.

    // List<Hotplaces> findByCTPRVN_NM(String query);
}

