package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Cliente;
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
				int quantitaDisponibile = rs.getInt("quantitaDisponibile");

				Prodotto prodotto = new Prodotto(idProdotto,nome, quantità, prezzo, descrizione, immagine,idCategoria,quantitaDisponibile);
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
				int quantitaDisponibile = rs.getInt("quantitaDisponibile");

				Prodotto prodotto = new Prodotto(idProdotto,nomeProdotto, quantità, prezzo, descrizione, immagine,idCategoria,quantitaDisponibile);
				prodotti.add(prodotto);
			}
			return prodotti;
		}
	}
	public Prodotto selectAllProdottiById(String idP) throws SQLException {
		String query = "SELECT * FROM Prodotto WHERE idProdotto = ?";

		Prodotto prodotto = null;
		try (PreparedStatement ps = connection.prepareStatement(query)){
			ps.setString(1, idP);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String idProdotto = rs.getString("idProdotto");
				String nome = rs.getString("nome");
				int quantita = rs.getInt("quantità");
				double prezzo = rs.getDouble("prezzo");
				String descrizione = rs.getString("descrizione");
				String immagine = rs.getString("immagine");
				String idCategoria = rs.getString("idCategoria");
				int quantitaDisponibile = rs.getInt("quantitaDisponibile");

				prodotto= new Prodotto(idProdotto, nome, quantita, prezzo, descrizione, immagine, idCategoria,quantitaDisponibile);

			}
		}
		return prodotto;
	}
	public void insertProdotto(Prodotto prodotto) throws SQLException {
		String query = "INSERT INTO Prodotto (idProdotto,nome,quantità,prezzo,descrizione,immagine,idCategoria,quantitaDisponibile) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, prodotto.getIdProdotto());
			ps.setString(2, prodotto.getNome());
			ps.setInt(3, prodotto.getQuantità());
			ps.setDouble(4, prodotto.getPrezzo());
			ps.setString(5, prodotto.getDescrizione());
			ps.setString(6, prodotto.getImmagine());
			ps.setString(7, prodotto.getIdCategoria());
			ps.setInt(8, prodotto.getQuantitaDisponibile());
			ps.executeUpdate();
		}
	}
	public void updateQuantitaDisponibile(String idProdotto, int nuovaQuantita)throws SQLException {
	    try {
	        String query = "UPDATE prodotto SET quantitaDisponibile = ? WHERE idProdotto = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setInt(1, nuovaQuantita);
	        ps.setString(2, idProdotto);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public int getQuantitaDisponibile(String idP) throws SQLException{
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		String query = "SELECT quantitaDisponibile FROM Prodotto WHERE idProdotto = ?";
		int quantitaDisponibile = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idP );
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				quantitaDisponibile = rs.getInt("quantitaDisponibile");
				}
			return quantitaDisponibile;
			}	
	}
	public void riduciQuantitaDisponibile(String idProdotto, int quantita) throws SQLException {
	    String query = "UPDATE Prodotto SET quantitaDisponibile = quantitaDisponibile - ? WHERE idProdotto = ?";
	    
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, quantita);
	        ps.setString(2, idProdotto);
	        ps.executeUpdate();
	    }
	}
	public void eliminaProdotto(String idProdotto) throws SQLException {
		String query = "DELETE FROM Prodotto WHERE idProdotto = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idProdotto);
			ps.executeUpdate();
		}
	}
	public void eliminaProdottoByIdCliente(String idUtente) throws SQLException {
		String query = "DELETE FROM Prodotto WHERE idUtente = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idUtente);
			ps.executeUpdate();
		}
	}
	public void modificaProdotto(Prodotto prodotto) throws SQLException {
	    String query = "UPDATE Prodotto SET nome = ?, prezzo = ?, descrizione = ? WHERE idProdotto = ?";

	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	    	
	        ps.setString(1, prodotto.getNome());
	        ps.setDouble(2, prodotto.getPrezzo());
	        ps.setString(3, prodotto.getDescrizione());
	        ps.setString(4, prodotto.getIdProdotto());
	        
	        ps.executeUpdate();
	      
	    }
	}
	public Prodotto selectById(String idProdotto) throws SQLException {
		String query = "SELECT * FROM Prodotto WHERE idProdotto = ?";

		Prodotto prodotto = new Prodotto();
		try (PreparedStatement ps = connection.prepareStatement(query)){
			ps.setString(1, idProdotto);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String nome = rs.getString("nome");
				int quantita = rs.getInt("quantità");
				double prezzo = rs.getDouble("prezzo");
				String descrizione = rs.getString("descrizione");
				String immagine = rs.getString("immagine");
				String idCategoria = rs.getString("idCategoria");
				int quantitaDisponibile = rs.getInt("quantitaDisponibile");

				 prodotto= new Prodotto(idProdotto, nome, quantita, prezzo, descrizione, immagine, idCategoria,quantitaDisponibile);

			}
		}
		System.out.println("ID prodotto richiesto: " + idProdotto);
		return prodotto;
	}
	public double getPrezzoProdotto(String idProdotto) throws SQLException {
	    String query = "SELECT prezzo FROM Prodotto WHERE idProdotto = ?";
	    double prezzo = 0.0;

	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, idProdotto);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            prezzo = rs.getDouble("prezzo");
	        }
	    }
	    return prezzo;
	}


}
