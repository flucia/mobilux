package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Carrello;
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
}



