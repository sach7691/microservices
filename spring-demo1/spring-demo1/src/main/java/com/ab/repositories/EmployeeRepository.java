package com.ab.repositories;

import com.ab.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Employee e where e.id = :id")
    public void deleteAnEmployee(@Param("id") int id);
}
