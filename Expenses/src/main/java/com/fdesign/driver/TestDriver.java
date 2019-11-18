package com.fdesign.driver;

import java.util.List;

import com.fdesign.daos.ReimbursementDao;
import com.fdesign.daos.UserDao;
import com.fdesign.daos.UserDaoSQL;
import com.fdesign.models.Reimbursement;
import com.fdesign.models.User;

public class TestDriver {

	public static void main(String[] args) {

		UserDao userDao = UserDao.currentImplementation;
		ReimbursementDao reimDao = ReimbursementDao.currentImplementation; 
		
		System.out.println("What is a man");
		
//		System.out.println(userDao.findAll());//Works, but prints all in a line
//		System.out.println(userDao.findByUsernameAndPassword("vvonreal", "pass"));//Works
//		userDao.findAll().forEach(expense ->{
//			System.out.println(expense);
//		}); //To get all users
		
//		reimDao.findAll().forEach(expense ->{
//		System.out.println(expense);
//		}); //Works
//		System.out.println("this");
//		reimDao.findReimbursementByUserAuthor(3).forEach(expense ->{
//			System.out.println(expense);
//		});
//		System.out.println(reimDao.updateReimbursement("What even", 3, 1, 22));//Works
//		
//		reimDao.findAll().forEach(expense ->{
//			System.out.println(expense);
//			}); //Works
	}

}
