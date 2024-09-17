package com.tushar.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.tushar.chatapp.utils.ConfigReader.getValue;

//Throw early and catch later

public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException{
		//Step-1 Load a Driver
		Class.forName(getValue("DRIVER"));
		//Step-2 Making a Connection
		final String Connection_String= getValue("CONNECTION_URL");
		final String User_ID = getValue("USER_ID");
		final String Password= getValue("Password");
		Connection con= DriverManager.getConnection(Connection_String, User_ID, Password);
		if(con != null) {
			System.out.println("CONNECTION CREATED....");
		//	con.close();
		}
		return con;
	}
}
