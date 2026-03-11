package com.legendit.counselor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legendit.counselor.entity.Counselor;
import com.legendit.counselor.repo.CounselorRepo;

@Service
public class CounselorService {

	@Autowired
	private CounselorRepo counselor;

	public boolean registerCounselor(Counselor counselors) {
		counselor.save(counselors);
		return true;
	}

	public Counselor login(String email, String password) {
		Counselor c = counselor.findByEmail(email);

		if (c != null && c.getPassword().equals(password)) {
			return c;
		}

		return null;

	}

}
