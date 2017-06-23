package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.FileRequestModel;
import Model.User;
import Util.DbUtil;

public class Validate {

	private static Connection connection;

	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public Validate() {
		connection = DbUtil.getConnection();
	}

	public User checkUser(String username, String password, String secretKey) {
		User usr = new User();

		try {
			ps = connection.prepareStatement("select * from user where username=? and password=? and secretkey=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, secretKey);
			
			rs = ps.executeQuery();
			if (rs.next() && rs != null) {
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
				usr.setSecretkey(rs.getString("secretkey"));
			}
			return usr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public FileRequestModel checkKey(String secretekey)
	{
		FileRequestModel freq = new FileRequestModel();
		
		try
		{
			ps = connection.prepareStatement("select * from filerequest where secretekey=?");
			ps.setString(1, secretekey);
			
			rs = ps.executeQuery();
			
			if(rs.next() && rs!=null)
			{
				freq.setSceretekey(rs.getString("secretekey"));
			}
			return freq;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
