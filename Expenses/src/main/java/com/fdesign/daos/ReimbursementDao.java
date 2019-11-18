package com.fdesign.daos;

import java.sql.Timestamp;
import java.util.List;

import com.fdesign.models.Reimbursement;
import com.fdesign.models.User;

public interface ReimbursementDao {
	
	ReimbursementDao currentImplementation = new ReimbursementDaoSQL(); 
	
	//For employees
	
	void save(Reimbursement r);
	
	List<Reimbursement> findReimbursementByUserAuthor(int author);
		
	//For both
	
	List<Reimbursement>findByAuthorAndStatus(int author, int statusId);

	//For managers
	
	List<Reimbursement> findReimbursementByUserStatus(int statusId);
	
	List<Reimbursement> findAll();
	
	void updateReimbursement(String rsResolution, int resolver, int statusId, int id);
	
}
