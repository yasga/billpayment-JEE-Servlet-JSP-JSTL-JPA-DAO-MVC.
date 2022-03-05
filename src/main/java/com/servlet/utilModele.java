package com.servlet;

import java.util.ArrayList;
import java.util.List;

import com.model.UserModel;

public class utilModele {
	
List<UserModel> UserModels = new ArrayList<>();
	
	public List<UserModel> getUserModels() {
		return UserModels;
	}
	
	public void setUserModels(List<UserModel> UserModels) {
		this.UserModels = UserModels;
	}

}
