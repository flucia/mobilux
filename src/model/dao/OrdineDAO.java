package model.dao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.bean.Ordine;
import model.bean.OrdineCliente;

public class OrdineDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public OrdineDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}
	public boolean insertOrdine(Ordine ordine) throws SQLException {
		String query = "INSERT INTO Ordine (idUtente, dataOrdine,prezzoTotale) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, ordine.getIdUtente());
			ps.setDate(2,java.sql.Date.valueOf(ordine.getDataOrdine()));
			ps.setDouble(3, ordine.getPrezzoTotale()); 
			ps.executeUpdate();
		}	
		return true;
	}

	public ArrayList<Ordine> selectById(String idUtente) throws SQLException{
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		String query = "SELECT * FROM Ordine WHERE idUtente=?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1,idUtente );

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idOrdine = rs.getInt("idOrdine"); 
				LocalDate dataOrdine = rs.getDate("dataOrdine").toLocalDate();
				double prezzoTotale = rs.getDouble("prezzoTotale");
				Ordine ordine = new Ordine(idOrdine, idUtente, dataOrdine,prezzoTotale);
				ordini.add(ordine);
			}
		}
		return ordini;
	}
	public ArrayList<Ordine> selectAll() throws SQLException{
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		String query = "SELECT * FROM Ordine";
		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String idUtente = rs.getString("idUtente");
				int idOrdine = rs.getInt("idOrdine"); 
				LocalDate dataOrdine = rs.getDate("dataOrdine").toLocalDate();
				double prezzoTotale = rs.getDouble("prezzoTotale");
				Ordine ordine = new Ordine(idOrdine, idUtente, dataOrdine,prezzoTotale);
				ordini.add(ordine);
			}
		}
		return ordini;
	}
	public ArrayList<OrdineCliente> cercaOrdini(String query) {
		ArrayList<OrdineCliente> ordini = new ArrayList<>();
	   
	    String sql = "SELECT o.idOrdine, o.dataOrdine, o.prezzoTotale, c.nome, c.cognome "
	               + "FROM Ordine o "
	               + "JOIN Cliente c ON o.idUtente = c.cf "
	               + "WHERE c.nome LIKE ? OR o.dataOrdine LIKE ? OR c.cognome LIKE ?";
	    
	    try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setString(1, "%" + query.trim() + "%");
	        ps.setString(2, "%" + query.trim() + "%");
	        ps.setString(3, "%" + query.trim() + "%");
	        
	        ResultSet rs = ps.executeQuery();
	       
	        while (rs.next()) {
	            OrdineCliente ordineCliente = new OrdineCliente();
	            Cliente cliente = new Cliente();
	            ordineCliente.setIdOrdine(rs.getInt("idOrdine"));
	            ordineCliente.setDataOrdine(rs.getDate("dataOrdine").toLocalDate());
	            ordineCliente.setPrezzoTotale(rs.getDouble("prezzoTotale"));
	            cliente.setNome(rs.getString("nome"));
	            cliente.setCognome(rs.getString("cognome"));
	            ordineCliente.setCliente(cliente);	            
	            ordini.add(ordineCliente);
	                    }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ordini;
	}
}
