package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.bean.DettaglioOrdine;

public class DettaglioOrdineDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public DettaglioOrdineDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}
	public ArrayList<DettaglioOrdine> selectAllDettagli() throws SQLException {
		ArrayList<DettaglioOrdine> dettagliOrdini = new ArrayList<>();
		String query = "SELECT * FROM DettaglioOrdine";

		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String idDettaglioOrdine = rs.getString("idDettaglioOrdine");
				int idOrdine = rs.getInt("idOrdine");
				String idProdotto = rs.getString("idProdotto");
				double quantita = rs.getDouble("quantita");
				double prezzoProdotto = rs.getDouble("prezzoProdotto");
				double prezzoTotale = rs.getDouble("prezzoTotale");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				

				DettaglioOrdine dettaglioOrdine = new DettaglioOrdine(idDettaglioOrdine,idOrdine,idProdotto,quantita,prezzoProdotto,prezzoTotale,nome,descrizione);
				dettagliOrdini.add(dettaglioOrdine);
			}
		}
		return dettagliOrdini;
	}
	
	public ArrayList<DettaglioOrdine> selectDettagliOrdineById(int idOrdine) throws SQLException {
	    ArrayList<DettaglioOrdine> dettagliOrdine = new ArrayList<>();
	    String query = "SELECT * FROM DettaglioOrdine WHERE idOrdine = ?";

	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, idOrdine);  
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String idDettaglioOrdine = rs.getString("idDettaglioOrdine");
	            String idProdotto = rs.getString("idProdotto");
	            double quantita = rs.getDouble("quantita");
	            double prezzoProdotto = rs.getDouble("prezzoProdotto");
	            double prezzoTotale = rs.getDouble("prezzoTotale");
	            String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");

	            DettaglioOrdine dettaglioOrdine = new DettaglioOrdine(idDettaglioOrdine, idOrdine, idProdotto, quantita, prezzoProdotto, prezzoTotale,nome,descrizione);
	            dettagliOrdine.add(dettaglioOrdine);
	        }
	    }
	    return dettagliOrdine;
	}


}
