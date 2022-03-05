package com.servlet;



import java.io.IOException;
import com.dao.LoginDAO;
import com.model.UserModel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public InscriptionServlet() {
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
        
        String emailu = request.getParameter("email");
        String passwordu = request.getParameter("password");
        String nomu = request.getParameter("nom");
        String prenomu = request.getParameter("prenom");
        int telu = Integer. parseInt(request.getParameter("tel"));
        int soldeu = Integer. parseInt(request.getParameter("solde"));
        int montantAbonnementu = Integer. parseInt(request.getParameter("montantAbonnement"));
        int typeUtilu = 1;

        LoginDAO ls = new LoginDAO();
        
        ls.save(new UserModel(emailu, passwordu, nomu, prenomu, telu, soldeu, montantAbonnementu, typeUtilu));

            response.sendRedirect("login.jsp");
        
    }
}
