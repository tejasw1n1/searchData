package Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import DAO.FileDao;
import Model.FileDetails;
import Model.User;
import Util.HierarchialClustering;



/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "UploadDocs";
	private static final String ENCRYPT_DIRECTORY = "EncryptDocs";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// constructs the directory path to store upload file
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		// constructs the directory path to store encrypt file
		String encryptPath = getServletContext().getRealPath("") + File.separator + ENCRYPT_DIRECTORY;
	
	
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		String upload_fileName = null;
		String extension = null;
		String filePath = null;
		String encfilepath = null;
		String keywords = null;
		InputStream filecontent = null;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {

			// parses the request's content to extract file data
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();
		
			FileDetails ff = new FileDetails();

			
			// iterates over form's fields
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				String fieldName = item.getFieldName();
				if(fieldName.equals("file")){
					filecontent = item.getInputStream();
					upload_fileName = new File(item.getName()).getName();
					System.out.println("uploaded file name:" + upload_fileName);
					extension = FilenameUtils.getExtension(upload_fileName);
					System.out.println("uploaded file :" + extension);
					filePath = uploadPath + File.separator + upload_fileName;
					File storeFile = new File(filePath);
					item.write(storeFile);
					
					//------------------Encryption algorithm IMplementation---
					encfilepath = encryptPath+ File.separator +upload_fileName+ ".bfa";
					String key = "Mary has one cat";
			        File encryptedFile = new File(encfilepath);
					
			        try {
			        	long startTime = System.nanoTime();
			            CryptoUtils.encrypt(key, storeFile, encryptedFile);
			            long endTime = System.nanoTime();
			            System.out.println("Took "+(endTime - startTime) + " ns"); 
			        } catch (CryptoException ex) {
			            System.out.println(ex.getMessage());
			            ex.printStackTrace();
			        }
					
			        String encryptedformate = upload_fileName+ ".bfa";
			        int userid = user.getUserid();
					int typeofuserid = user.getTypeofusersid();
			
					ff.setFilecontent(filecontent);
					ff.setEncryptedformate(encryptedformate);
					ff.setFilename(upload_fileName);
					ff.setUploadedby(userid);
					ff.setTypeofuserid(typeofuserid);

					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					String currentDate = df.format(Calendar.getInstance().getTime());
					ff.setUploadeddate(currentDate);
					
					long time = Calendar.getInstance().getTimeInMillis();
					ff.setDateInMillis(time);

					
				}
				if(fieldName.equals("keywords")){
					keywords = item.getString();
					ff.setKeywords(keywords);
				}
			}

			HierarchialClustering hierarchialClustering = new HierarchialClustering();
			hierarchialClustering.clusterInputFile(ff);
			FileDao fd = new FileDao();
			fd.insertFiles(ff);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PrintWriter out = response.getWriter();
		out.print(
				"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>Insert title here</title><script type='text/javascript'>"
						+ "function display(msg){alert(msg);}</script></head>"
						+ "<body><script type='text/javascript'>display('File Uploaded Successfully... ');window.location='http://localhost:8080/SecureDataStorage-V3/senderwelcome.jsp';</script></body></html>");	
		

	}

}
