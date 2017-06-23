package DAO;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import Controller.CryptoException;
import Controller.CryptoUtils;
import Model.FileDetails;
import Model.FileRequestModel;

import Util.DbUtil;

public class FileDao {
	
private Connection connection;
	public FileDao() {
		connection = DbUtil.getConnection();
	}
	public int insertFiles(FileDetails file) throws SQLException
	{
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into file ( uploadedby, filename, filecontent, uploadeddate, encryptedformate,typeofuserid, keywords, dateInMillis, cluster)values( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		preparedStatement.setInt(1,file.getUploadedby() );
		preparedStatement.setString(2, file.getFilename());
		preparedStatement.setBlob(3, file.getFilecontent());
		preparedStatement.setString(4, file.getUploadeddate());
		preparedStatement.setString(5, file.getEncryptedformate());
		preparedStatement.setInt(6, file.getTypeofuserid());
		preparedStatement.setString(7, file.getKeywords());
		preparedStatement.setLong(8, file.getDateInMillis());
		preparedStatement.setString(9, file.getCluster());
		preparedStatement.executeUpdate();
		
		preparedStatement=null;
		 preparedStatement = connection
					.prepareStatement("select max(fileid) as id from file");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs!=null) {
				
				return rs.getInt("id");
				
			}
			
			else{
				return 0;
			}
		
	}

	public List <FileDetails>	getAllfiles(){
		List<FileDetails> files=new ArrayList<FileDetails>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from file as a join user as b on b.userid=a.uploadedby join userrolemapping c on c.userid=b.userid join typeofuser d on d.typeofuserid=c.typeofuserid");
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next() && rs!=null) {
				FileDetails f=new FileDetails();
				f.setEncryptedformate(rs.getString("encryptedformate"));
				f.setFileid(rs.getInt("fileid"));
				f.setFilename(rs.getString("filename"));
				f.setOwnerName(rs.getString("username"));
				f.setUploadeddate(rs.getString("uploadeddate"));
				f.setUploadedby(rs.getInt("uploadedby"));
				f.setKeywords(rs.getString("keywords"));
				f.setRank(rs.getInt("rank"));
				f.setDateInMillis(rs.getLong("dateInMillis"));
				f.setCluster(rs.getString("cluster"));
				files.add(f);
				
			}
			
			return files;
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return files;
	}

	public FileDetails getFileContent(int fileId)
	{
		
		FileDetails f=new FileDetails();
		//select * from file where fileid=?
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from file where fileid=?");
			preparedStatement.setInt(1,fileId);
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs!=null) {
				f.setFileid(rs.getInt("fileid"));
				f.setRank(rs.getInt("rank"));
				f.setFilename(rs.getString("filename"));
				f.setEncryptedformate(rs.getString("encryptedformate"));
				f.setDateInMillis(rs.getLong("dateInMillis"));
				f.setCluster(rs.getString("cluster"));
			}	
			
			return f;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public void  updateField(int primaryKey	, String key, String value){
		try {
			String statement = "update file set "+key+" = ? where fileid=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setString(1, value);
			preparedStatement.setInt(2, primaryKey);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void deleteFile(String id){
		try{
			String statement = "delete from file where fileid="+id;
			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String getContentOfFile(String id){
		String filecontent="";
		try{
			String statement = "select filecontent from file where fileid="+id;
			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next() && rs!=null) {
				filecontent = rs.getString("filecontent");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return filecontent;
	}
	
}