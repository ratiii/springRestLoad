package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entities.Loads;

@Repository
public interface LoadDao extends JpaRepository<Loads, Long> {

}
