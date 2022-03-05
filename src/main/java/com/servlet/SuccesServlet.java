package com.servlet;


import java.io.IOException;
import java.time.LocalDate;
import com.dao.paieDAO;
import com.model.CarteModel;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.paieService;


@WebServlet("/SuccesServlet")
public class SuccesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public SuccesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        
        String numCarte = request.getParameter("numCarte");
        
        String date = request.getParameter("dateExp");
        LocalDate dateExp = LocalDate.parse(date);
      
        String cryptogramme = request.getParameter("cryptogramme");
        
        final String emailu = request.getParameter("email");
        String nomu = request.getParameter("nom");
        String prenomu = request.getParameter("prenom");
        String telu = request.getParameter("tel");
        String soldeu = request.getParameter("solde");
        String montantAbonnementu = request.getParameter("montantAbonnement");
        
        paieService ls = new paieService();
        CarteModel ans = ls.check(numCarte, cryptogramme);
        
        if(dateExp.isAfter(LocalDate.now()) && numCarte.length()==16  &&  ans.getDateExp() != null) {
        	if(Integer.parseInt(montantAbonnementu) >= Integer.parseInt(soldeu)) {
        		ServletContext context = getServletContext();
            	String host = context.getInitParameter("host");
            	String port = context.getInitParameter("port");

            	paieDAO dao = new paieDAO();
            	try {
            	dao.sendEmailseul(host, port, "enter a gmail email", "enter this gmail's password", emailu, "noreply", "�chec de paiement : solde inssufisant !");
            	
            	}catch (Exception ex) {
            		ex.printStackTrace();
            	}
            	response.sendRedirect("welcome.jsp");
        	}else {
        	ServletContext context = getServletContext();
        	String host = context.getInitParameter("host");
        	String port = context.getInitParameter("port");
        	paieDAO dao = new paieDAO();
        	try {
        		
        	dao.sendEmail(host, port, "enter a gmail email", "enter this gmail's password", emailu, "noreply", "Facture de paiement",nomu,prenomu,telu,montantAbonnementu);
        	
        	}catch (Exception ex) {
        		ex.printStackTrace();
        	}
        	HttpSession session = request.getSession();
			session.setAttribute("email", emailu);
			session.setAttribute("nom", nomu);
			session.setAttribute("prenom", prenomu);
			session.setAttribute("tel", telu);
			session.setAttribute("montantAbonnement", montantAbonnementu);
            response.sendRedirect("success.jsp");
        }
        	}
        else {
            response.sendRedirect("login.jsp");
        }
    }
}
