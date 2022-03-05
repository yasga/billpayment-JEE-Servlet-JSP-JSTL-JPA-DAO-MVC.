package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LoginDAO;
import com.model.UserModel;
import com.service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
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
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		LoginService ls = new LoginService();
		UserModel ans = ls.check(uname, pass);
		
		if(ans.getTypeUtil()== 1) {	
			HttpSession session = request.getSession();
			session.setAttribute("email", ans.getEmail());
			session.setAttribute("nom", ans.getNom());
			session.setAttribute("prenom", ans.getPrenom());
			session.setAttribute("tel", ans.getTel());
			//session.setAttribute("type", ans.getTypeUtil());
			//session.setAttribute("solde", ans.getSolde());
			session.setAttribute("solde", ans.getSolde());
			session.setAttribute("montantAbonnement", ans.getMontantAbonnement());
			response.sendRedirect("welcome.jsp");
			}
		else if(ans.getTypeUtil() == 2) {
			HttpSession session = request.getSession();
			session.setAttribute("email", ans.getEmail());
			session.setAttribute("nom", ans.getNom());
			session.setAttribute("prenom", ans.getPrenom());
			session.setAttribute("tel", ans.getTel());
			session.setAttribute("type", ans.getTypeUtil());
			//session.setAttribute("solde", ans.getSolde());
			//session.setAttribute("montantAbonnement", ans.getMontantAbonnement());
			LoginDAO dao = new LoginDAO();
			utilModele model = new utilModele();
			List<UserModel> utilisateurs = dao.avoirutilisateurs();
		    model.setUserModels(utilisateurs);
			session.setAttribute("model",model);
			
			response.sendRedirect("welcome2.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

}
