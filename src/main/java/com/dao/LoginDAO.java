package com.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import com.model.UserModel;
import com.util.JPAutil;

public class LoginDAO {
	
	private EntityManager entityManager=JPAutil.getEntityManager("test2jdbc");

	public UserModel check(String emailu, String password) {
				
		UserModel m;
		Query query=entityManager.createQuery("select m from UserModel m where m.password= :passwordu and m.email= :emailu");
		query.setParameter("emailu", emailu);
		query.setParameter("passwordu", password);		
		m= (UserModel) query.getSingleResult();
		   
		if(m.getNom() != null) {
            	//String email = rs.getString(1);
            	String nom = m.getNom();
				String prenom = m.getPrenom();
				int typeUtil = m.getTypeUtil();
				int tel = m.getTel();
				int solde = m.getSolde();
				int montantAbonnement = m.getMontantAbonnement();

            	m.setEmail(emailu);
				m.setNom(nom);
				m.setPrenom(prenom);
				m.setTypeUtil(typeUtil);
				m.setTel(tel);
				m.setSolde(solde);
				m.setMontantAbonnement(montantAbonnement);
		}
		
		return m;
	}
	
	public void inscriptionAbonnee(String email, String password, String nom, String prenom, String tel, String solde, String montantAbonnement) {
		Query query=entityManager.createQuery("insert into abonnee (email, password, nom, prenom, tel, solde, montantAbonnement , typeUtil)"
				+ "VALUES (:emailu, :passwordu, :nomu, :prenomu, :telu, :soldeu, :montantAbonnement, :typeUtil)");
		query.setParameter("emailu", email);
		query.setParameter("passwordu", password);
		query.setParameter("nomu", nom);
		query.setParameter("prenomu", prenom);	
		query.setParameter("telu", tel);
		query.setParameter("soldeu", solde);	
		query.setParameter("montantAbonnementu", montantAbonnement);			
		query.setParameter("typeUtil", 1);			

	}

	public void save(UserModel p) {
		EntityTransaction tx = entityManager.getTransaction();
	 	tx.begin();
	 	entityManager.persist(p);
	 	tx.commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserModel> avoirutilisateurs() {
	
		List<UserModel> utilisateurs =
		         entityManager.createQuery("select m from UserModel m where m.typeUtil= :mc")
		         .setParameter("mc", 1)
		         .getResultList();
			return utilisateurs;
	}
	
}
