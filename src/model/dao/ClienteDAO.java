package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Cliente;
import model.bean.ConnessioneDb;

public class ClienteDAO {

	private ConnessioneDb connectionDb;
	private Connection connection;

	public ClienteDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}

	public Cliente login(String username, String password) throws SQLException {
		Cliente cliente = null;
		String query = "SELECT * FROM Cliente WHERE username = ? AND password = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String cf = rs.getString(1);
				String nome = rs.getString(2);
				String cognome = rs.getString(3);
				String email = rs.getString(5);
				String user = rs.getString(4);
				String pass = rs.getString(6);
				String ruolo = rs.getString(7);
				String indirizzo = rs.getString(8);
				String cellulare = rs.getString(9);

				// Creare il cliente con i dati ottenuti dal database
				cliente = new Cliente(cf, nome, cognome, email, user, pass, ruolo, indirizzo, cellulare);
			}
		}
		return cliente;
	}

	public boolean usernameExists(String username) throws SQLException {
		String query = "SELECT COUNT(*) FROM Cliente WHERE username = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
		return false;
	}
	public void insertCliente(Cliente cliente) throws SQLException {
		String query = "INSERT INTO Cliente (cf, nome, cognome, username, email, password, ruolo, indirizzo, cellulare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, cliente.getCodiceFiscale());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getCognome());
			ps.setString(4, cliente.getUsername());
			ps.setString(5, cliente.getEmail());
			ps.setString(6, cliente.getPassword());
			ps.setString(7, cliente.getRuolo());
			ps.setString(8, cliente.getIndirizzo());
			ps.setString(9, cliente.getCellulare());
			ps.executeUpdate();
		}
	}
	public ArrayList<Cliente> selectAllCliente() throws SQLException {
		ArrayList<Cliente> clienti = new ArrayList<>();
		String query = "SELECT * FROM Cliente";

		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String cf = rs.getString("cf");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String ruolo = rs.getString("ruolo");
				String indirizzo = rs.getString("indirizzo");
				String cellulare = rs.getString("cellulare");

				Cliente cliente = new Cliente(cf, nome, cognome, username, email, password, ruolo, indirizzo, cellulare);
				clienti.add(cliente);
			}
		}
		return clienti;
	}
	public void insertClienteTemporaneo(String sessionId) throws SQLException {
	    String query = "INSERT INTO cliente (cf, is_temporary) VALUES (?, TRUE)";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, sessionId);
	        ps.executeUpdate();
	    }
	}


}