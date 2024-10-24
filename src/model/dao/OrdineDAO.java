package model.dao;

import java.sql.Connection;

import model.bean.ConnessioneDb;

public class OrdineDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public OrdineDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}
	
	

}
