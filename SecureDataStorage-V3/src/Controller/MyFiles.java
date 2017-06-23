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
import Model.FileDetails;
import Model.User;

/**
 * Servlet implementation class FileDetails
 */

@WebServlet("/MyFiles")
public class MyFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFiles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileDao fd=new FileDao();
		
		List<FileDetails> allFiles=new ArrayList<FileDetails>();
		List<FileDetails> files=new ArrayList<FileDetails>();
	
		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("user");
										
		allFiles = fd.getAllfiles();
		for(int i=0; i < allFiles.size(); i++){
			if(allFiles.get(i).getUploadedby() == u.getUserid()){
				files.add(allFiles.get(i));
			}
		}
		
		 request.setAttribute("files", files);
		 
		 RequestDispatcher view = request.getRequestDispatcher("myfiles.jsp");
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
