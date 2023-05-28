package com.relocation.relocation.Repository;


import com.relocation.relocation.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface staffRepository extends JpaRepository<Staff, String> {
}
