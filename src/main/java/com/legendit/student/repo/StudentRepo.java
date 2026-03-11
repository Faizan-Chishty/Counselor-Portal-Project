package com.legendit.student.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legendit.student.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    long countByStatusAndCounselor_Id(String status, Long counselorId);

    List<Student> findByCounselor_Id(Long counselorId);

    List<Student> findByPhone(String phone);

}