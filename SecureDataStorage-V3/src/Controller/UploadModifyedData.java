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

@WebServlet("/UploadModifyedData")
public class UploadModifyedData extends HttpServlet{
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadModifyedData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String fileId =  request.getParameter("fileid");
    	String filecontent = request.getParameter("modifyFile");
    	
		FileDao fd=new FileDao();
		fd.updateField(Integer.parseInt(fileId), "filecontent", filecontent);
		
		List <FileDetails> allFiles = fd.getAllfiles();
		RequestDispatcher view = request.getRequestDispatcher("myfiles.jsp");
		request.setAttribute("files", allFiles);
        view.forward(request, response);


    }
    	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		doGet(request, response);
    	}

}
