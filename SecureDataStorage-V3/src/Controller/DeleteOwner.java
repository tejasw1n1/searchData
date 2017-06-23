package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FileDao;
import DAO.UserDao;
import Model.FileDetails;
import Model.User;

@WebServlet("/DeleteOwner")
public class DeleteOwner extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOwner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
    	String userId =  request.getParameter("userId");
    	
		UserDao ud = new UserDao();
		ud.deleteUser(userId);
		
		List<User> allusers = new ArrayList<User>();
		List<User> allOwners = new ArrayList<User>();

		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("user");
		
		allusers =   ud.getAllUser();
		for(int i=0; i < allusers.size(); i++){
			if(allusers.get(i).getTypeofuser().equals("Data Owner")){
				allOwners.add(allusers.get(i));
			}
		}
		
		request.setAttribute("users", allOwners);
		 
		 RequestDispatcher view = request.getRequestDispatcher("dataOwnerList.jsp");
	     view.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
