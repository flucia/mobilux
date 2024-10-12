package model;

import java.sql.Connection;


public class QueryInsert {
	private Connection connection;
	
	 public QueryInsert(Connection connection) {

	        this.connection=connection;
	    }
	 
	   public void insertCliente(Cliente cl) {
	        InsertMySql insert = new InsertMySql(connection);
	        String query = "INSERT INTO Cliente(cf, nome, cognome, username, email, password, ruolo, indirizzo,cellulare) VALUES ('"+cl.getCodiceFiscale()+"','"+cl.getNome()+"','"+cl.getCognome()+"','"+cl.getEmail()+"','"+cl.getUsername()+"','"+cl.getPassword()+"', '"+cl.getRuolo()+"', '"+cl.getIndirizzo()+"', '"+cl.getCellulare()+"')";
	        insert.insert(query);
	    }

}
