package model.bean;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnessioneDb {
	public Connection con = null;

	public ConnessioneDb(){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Mobilux";
			con = DriverManager.getConnection(url,"root","00000000");

		}
		catch(Exception e){
			System.out.println("Connessione Fallita \n");
			System.out.println(e);
		}

	}

	public Connection getCon() {
		return con;
	}

}