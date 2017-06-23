package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import Model.User;

@WebServlet("/GenerateKey")
public class GenerateKey extends HttpServlet{
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//PrintWriter out = response.getWriter();
		//User user = new User();
		
		HttpSession session = request.getSession();

		String username = request.getParameter("un");
		String password = request.getParameter("pass");
		
		
		UserDao userDao = new UserDao();
		User user = userDao.typeofUser(username, password);
		if(user==null){
			PrintWriter out = response.getWriter();
			out.print(
					"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>SDS</title><script type='text/javascript'>"
							+ "function display(msg){alert(msg);}</script></head>"
							+ "<body><script type='text/javascript'>display('Please check your password or username');window.location='http://localhost:8080/SecureDataStorage-V3/userlogin.jsp';</script></body></html>");
			return;
		}
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.genKeyPair();

		byte[] secretKey = keyPair.getPublic().getEncoded();
		
		String key = secretKey.toString();

		System.out.println("key: " + secretKey);
		
		
	
		user.setSecretkey(key);
		
		System.out.println(secretKey);
		userDao.updateField(user.getUserid(), "secretkey", user.getSecretkey());
		
		PrintWriter out = response.getWriter();

		out.print(
				"<!DOCTYPE html>" +
				"<html>" +
				 "<head>" +
					"<meta charset='ISO-8859-1'>" +
						"<title>SDS</title>" +
						"<script type='text/javascript'>" +
							"function display(msg){alert(msg);}" +
						"</script>" +
				 "</head>" +
				 "<body>" +
				 	"<script type='text/javascript'>" +
				 		"display('OTP Key sent..Pls Check the Your Mailid once and Use secrete key to lgoin to your account... ');" +
					"</script>" +
				 "</body>" +
				"</html>"
			);

 

		request.setAttribute("userName", username);
		request.setAttribute("password", password);
		RequestDispatcher rs = request.getRequestDispatcher("userLoginOTP.jsp");
        rs.forward(request, response);
        
        String usrName = user.getUsername();
		System.out.println("consumer name:" + usrName);

		String usrEmail = user.getEmail();
		System.out.println("consumer email:" + usrEmail);
		
		// ------------------Email Setup--------------------------

				// SMTP server information
				String host = "smtp.gmail.com";
				String port = "587";
				String mailFrom = "tejumouli@gmail.com";
				String pass = "Rvsbn@11097";

				// outgoing message information
				String mailTo = usrEmail;
				String subject = "Hello " + usrName;
				String message = "Your One Time Password:"+ secretKey;

				String resultMessage = "";
				try {
					EmailUtility.sendEmail(host, port, mailFrom, pass, mailTo, subject, message);
					resultMessage = "The e-mail was sent successfully";
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "There were an error: " + ex.getMessage();
				} finally {

					System.out.println("Secrete Key sent to your mail id..");
				}

				// -----------------eof email setup------------------------------

		/*		PrintWriter out = response.getWriter();

				out.print(
						"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>SDS</title><script type='text/javascript'>"
								+ "function display(msg){alert(msg);}</script></head>"
								+ "<body><script type='text/javascript'>display('OTP Key sent..Pls Check the Your Mailid once and Use secrete key to lgoin to your account... ');</script></body></html>");

         */         
	}

}
