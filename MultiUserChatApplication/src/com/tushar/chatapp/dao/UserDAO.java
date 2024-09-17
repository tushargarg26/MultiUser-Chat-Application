package com.tushar.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tushar.chatapp.dto.UserDTO;
import com.tushar.chatapp.utils.Encryption;

// User CRUD(create, read, update, delete)

public class UserDAO {
	//public int add(String userid, String Password,..... ){
	//
	//}
	
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL= "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs= pstmt.executeQuery();
			
			return rs.next();
			//if(rs.next()) {
			//	return true;
			//}
			//else {
			//	return false;
			//}
			
		}
		finally{
			if(rs!=null)
				rs.close();
			
			if(pstmt!=null)
				pstmt.close();
			
			if(con!=null)
				con.close();
			
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		System.out.println("In UserDAO Recorded UserID--> "+userDTO.getUserid()+", Password--> "+ new String(userDTO.getPassword()));
		Connection connection= null;
		Statement stmt = null; //query
		try {
		connection = CommonDAO.createConnection();  //Step-1 Connection created
		//Step-2 We do a query
		stmt= connection.createStatement();
		//stmt.executeUpdate("insert into users (userid, password) values('Tushar', 'tushar123')"); //Insert, Delete, Update
		int record = stmt.executeUpdate("insert into users (userid, password) values('"+userDTO.getUserid()+"' , '"+ Encryption.passwordEncrypt( new String (userDTO.getPassword()))+"')");
		return record;
		}
		finally {    //ALWAYS EXECUTE except in the condition of system.exit();
			if(stmt!=null)
				stmt.close();
			
			if(connection!=null)
				connection.close();
		}
		
	}
}  