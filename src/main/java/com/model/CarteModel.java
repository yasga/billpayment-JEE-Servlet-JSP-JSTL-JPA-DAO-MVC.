package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
@Table (name = "cartes")
public class CarteModel {
	
	@Id
	@Column (name = "numCarte")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	String numCarte;
	@Temporal(TemporalType.DATE)
	@Column (name = "dateExp")
    Date dateExp;
	@Column (name = "cryptogramme")
	String cryptogramme;
	
    public String getNumCarte() { return numCarte; }
    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }
    public Date getDateExp() {
        return dateExp;
    }
    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }
    public String getCryptogramme() { return cryptogramme; }
    public void setCryptogramme(String cryptogramme) {
        this.cryptogramme = cryptogramme;
    }
}
