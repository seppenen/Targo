package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao_business;
import dao.dao;
import model.Company;
import model.User;

/**
 * Servlet implementation class Servlet_editCompany
 */
@WebServlet("/Servlet_editCompany")
public class Servlet_editCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_editCompany.doGet()");
		String id= request.getParameter("id");
		Dao_business dao_business = new Dao_business();
		dao dao = new dao();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("session");
		 if(user == null) {
			
			response.sendRedirect("index.jsp");
			
		} 
		
		try {
			
			if(dao.iftrue("*", "business", "business_id", id, "user_id", user.getId())) {
				
				Company company = dao_business.haeCompany(id);
				request.setAttribute("company", company);
			
				String jsp = "/editCompany.jsp"; 
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
				dispatcher.forward(request, response);	
			}else{
				request.setAttribute("errMsg", "NO ACCESS");
				request.getRequestDispatcher("/dash.jsp").forward(request, response);
			}
			
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_editCompany.doPost()");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("session");
		String name = request.getParameter("name");
		String alias = request.getParameter("alias");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		String address = request.getParameter("address");
		String id = request.getParameter("business_id");
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		Dao_business dao = new Dao_business();
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		company.setAlias(alias);
		company.setPhone(phone);
		company.setAddress(address);
		company.setDescription(description);
		company.setOwner(user.getId());
		company.setLat(lat);
		company.setLng(lng);
		
		try {
			
			if(dao.updateCompany(company)) {
				
			
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		

	}

}
