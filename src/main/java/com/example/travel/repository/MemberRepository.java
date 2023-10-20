package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.model.Member;



@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
    Member findByEmail(String email);
    Member findByid(String id);
    Member findByPhone(String phone);
    Member findByPw(String Pw);
    Member findByBirth(String birth);

}
