package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healsmart.entities.Ward;

public interface WardRepository extends JpaRepository<Ward, Long> {

}
