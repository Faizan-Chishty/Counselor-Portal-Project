package com.legendit.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legendit.student.entity.Student;
import com.legendit.student.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public void save(Student s){
        repo.save(s);
    }

    public List<Student> getAll(Long counselorId){
        return repo.findByCounselor_Id(counselorId);
    }

    public List<Student> searchPhone(String phone){
        return repo.findByPhone(phone);
    }

    public Student getById(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public long getOpen(Long counselorId){
        return repo.countByStatusAndCounselor_Id("OPEN", counselorId);
    }

    public long getEnrolled(Long counselorId){
        return repo.countByStatusAndCounselor_Id("ENROLLED", counselorId);
    }

    public long getLost(Long counselorId){
        return repo.countByStatusAndCounselor_Id("LOST", counselorId);
    }

}