package Controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.DbUtil;

import DAO.FileDao;
import Model.FileDetails;
import Model.User;
import DAO.Validate;
import Model.FileRequestModel;

@WebServlet("/Modify")
public class Modify extends HttpServlet{
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String fileId =  request.getParameter("fileId");
    	
		FileDao fd=new FileDao();
		String content = fd.getContentOfFile(fileId);
		request.setAttribute("content", content);
		request.setAttribute("fileid", fileId);
		
		RequestDispatcher view = request.getRequestDispatcher("modify.jsp");
        view.forward(request, response);


    }
    	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		doGet(request, response);
    	}
}
