package com.relocation.relocation.Repository;


import com.relocation.relocation.Model.Relocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface relocationRepository extends JpaRepository<Relocation, String> {
}
