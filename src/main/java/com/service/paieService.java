package com.service;


import com.dao.paieDAO;
import com.model.CarteModel;

public class paieService {

    public CarteModel check(String numCarte, String cryptogramme) {

        paieDAO dao = new paieDAO();
        CarteModel ans = dao.check(numCarte, cryptogramme);
        return ans;
    }

}

