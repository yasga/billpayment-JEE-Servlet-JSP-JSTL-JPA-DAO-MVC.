package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name = "abonnee")
public class UserModel {
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	int id;
	
	@Column (name = "email")
	String email;
	
	@Column (name = "password")
	String password;
	
	@Column (name = "nom")
	String nom;
	
	@Column (name = "prenom")
	String prenom;

	@Column (name = "tel")
	int tel;
	
	@Column (name = "solde")
	int solde;
	@Column (name = "montantAbonnement")	
	int montantAbonnement;
	
	@Column (name = "typeUtil")
	int typeUtil;

	public UserModel() {
		super();
	}
	public UserModel(String email, String password, String nom, String prenom, int tel, int solde,
			int montantAbonnement, int typeUtil) {
		super();
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.solde = solde;
		this.montantAbonnement = montantAbonnement;
		this.typeUtil = typeUtil;
	}
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getNom() { return nom; }
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getSolde() { return solde; }
	public void setSolde(int solde) {
		this.solde = solde;
	}
	public int getMontantAbonnement() {
		return montantAbonnement;
	}
	public void setMontantAbonnement(int montantAbonnement) {
		this.montantAbonnement = montantAbonnement;
	}
	public int getTypeUtil() {
		return typeUtil;
	}
	public void setTypeUtil(int typeUtil) {
		this.typeUtil = typeUtil;
	}

}
