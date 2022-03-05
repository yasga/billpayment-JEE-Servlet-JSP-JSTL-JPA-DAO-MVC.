package com.service;

import com.dao.LoginDAO;
import com.model.UserModel;

public class LoginService {
	
	public UserModel check(String email, String password) {
		
		LoginDAO dao = new LoginDAO();
		UserModel ans = dao.check(email, password);
		return ans;
	}

}
