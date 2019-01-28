package com.example.rest;

import com.example.rest.model.JsonList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<JsonList, Long> {

    JsonList findByHash(String hash);

}