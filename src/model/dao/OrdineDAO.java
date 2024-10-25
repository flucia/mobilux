package model.dao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.ConnessioneDb;
import model.bean.Ordine;

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

}
