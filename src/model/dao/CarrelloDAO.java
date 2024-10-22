package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Carrello;
import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.bean.Prodotto;


public class CarrelloDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public CarrelloDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}

	public void insertCarrello(Carrello carrello) throws SQLException {
		String query = "INSERT INTO Carrello (idUtente, idProdotto, quantita) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, carrello.getIdUtente());
			ps.setString(2, carrello.getIdProdotto());
			ps.setInt(3,carrello.getQuantita());
			ps.executeUpdate();
		}	
	}

	public ArrayList<Carrello> selectAllCarrello(String idUtente) throws SQLException {
		ArrayList<Carrello> carrelli = new ArrayList<>();
		String query = "SELECT c.idUtente, c.quantita, p.idProdotto, p.nome, p.immagine, p.prezzo " +
				"FROM Carrello c " +
				"JOIN Prodotto p ON c.idProdotto = p.idProdotto " +
				"WHERE c.idUtente = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idUtente);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String idProdotto = rs.getString("idProdotto");
				String nome = rs.getString("nome");
				String immagine = rs.getString("immagine");
				double prezzo = rs.getDouble("prezzo");
				int quantita = rs.getInt("quantita");


				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setNome(nome);
				prodotto.setImmagine(immagine);
				prodotto.setPrezzo(prezzo);


				Carrello carrello = new Carrello();
				carrello.setProdotto(prodotto);
				carrello.setQuantita(quantita);
				carrello.setIdUtente(idUtente);
				carrelli.add(carrello);
			}
		}
		return carrelli;
	}

	public void removeItem(String idUtente,String idProdotto) throws SQLException{
		String query = "DELETE FROM Carrello WHERE idUtente= ? AND idProdotto= ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idUtente);
			ps.setString(2, idProdotto);
			ps.executeUpdate();
		}

	}

	public void updateQuantitaCarrello(Carrello carrello) throws SQLException {
		String query = "UPDATE Carrello SET quantita = ? WHERE idUtente=? AND idProdotto= ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1,carrello.getQuantita());
			ps.setString(2, carrello.getIdUtente());
			ps.setString(3, carrello.getIdProdotto());

			ps.executeUpdate();
		}
	}
	public boolean productExists(String idProdotto, String idUtente) throws SQLException {
		String query = "SELECT COUNT(*) FROM Carrello WHERE idProdotto = ? AND idUtente= ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idProdotto);
			ps.setString(2, idUtente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
		return false;
	}

	public Carrello selectById(String idUtente,String idProdotto) throws SQLException{
		String query = "SELECT * FROM Carrello WHERE idUtente=? AND idProdotto= ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idUtente);
			ps.setString(2, idProdotto);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Carrello carrello = new Carrello();
				carrello.setIdProdotto(rs.getString("idProdotto"));
				carrello.setQuantita(rs.getInt("quantita"));
				carrello.setIdUtente(rs.getString("idUtente"));
				return carrello;

			}

		}
		return null;
	}
}





