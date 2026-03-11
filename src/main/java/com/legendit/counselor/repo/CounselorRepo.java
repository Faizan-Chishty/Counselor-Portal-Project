package com.legendit.counselor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legendit.counselor.entity.Counselor;

@Repository
public interface CounselorRepo extends JpaRepository<Counselor,Long> {
	
	Counselor findByEmail(String email);

}
