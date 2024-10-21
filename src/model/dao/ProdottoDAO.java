package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.ConnessioneDb;
import model.bean.Prodotto;

public class ProdottoDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public ProdottoDAO() {
		this.connectionDb = new ConnessioneDb();
	    connection = connectionDb.getCon();
	}
	public ArrayList<Prodotto> selectByIdCategoria(String idCategoria) throws SQLException {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		String query = "SELECT * FROM Prodotto WHERE idCategoria = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
				ps.setString(1, idCategoria);
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String idProdotto = rs.getString("idProdotto");
				String nome = rs.getString("nome");
				int quantità = rs.getInt("quantità");
				double prezzo = rs.getDouble("prezzo");
				String descrizione = rs.getString("descrizione");
				String immagine = rs.getString("immagine");
				idCategoria = rs.getString("idCategoria");

				Prodotto prodotto = new Prodotto(idProdotto,nome, quantità, prezzo, descrizione, immagine,idCategoria);
				prodotti.add(prodotto);
			}
			return prodotti;
		}
	}
	public ArrayList<Prodotto> selectByName(String nome) throws SQLException {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		String query = "SELECT * FROM Prodotto WHERE nome LIKE ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
				ps.setString(1,"%" + nome + "%");
				ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String idProdotto = rs.getString("idProdotto");
				String nomeProdotto = rs.getString("nome");
				int quantità = rs.getInt("quantità");
				double prezzo = rs.getDouble("prezzo");
				String descrizione = rs.getString("descrizione");
				String immagine = rs.getString("immagine");
				String idCategoria = rs.getString("idCategoria");

				Prodotto prodotto = new Prodotto(idProdotto,nomeProdotto, quantità, prezzo, descrizione, immagine,idCategoria);
				prodotti.add(prodotto);
			}
			return prodotti;
		}
	}
	
	
}
