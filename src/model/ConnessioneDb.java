package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnessioneDb {

	public ConnessioneDb() {

	}

	public Connection connessione(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Mobilux";
			con = DriverManager.getConnection(url,"root","00000000");

		}
		catch(Exception e){
			System.out.println("Connessione Fallita \n");
			System.out.println(e);
		}
		return con;

	}
}