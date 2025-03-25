package model.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Carrello;
import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.bean.Ordine;
import model.bean.OrdineCliente;
import model.bean.Prodotto;

public class OrdineDAO {
	private ConnessioneDb connectionDb;
	private Connection connection;

	public OrdineDAO() {
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();
	}

	public int insertOrdine(Ordine ordine) throws SQLException {
		String query = "INSERT INTO Ordine (idUtente, dataOrdine, prezzoTotale) VALUES (?, ?, ?)";
		int idOrdine = -1;
		System.out.println(ordine);
		try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, ordine.getIdUtente());
			ps.setDate(2, java.sql.Date.valueOf(ordine.getDataOrdine()));
			ps.setDouble(3, ordine.getPrezzoTotale());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idOrdine = rs.getInt(1);
			}
		}
		return idOrdine;
	}

	public ArrayList<Ordine> selectById(String idUtente) throws SQLException {
	    ArrayList<Ordine> ordini = new ArrayList<Ordine>();
	    String query = "SELECT * FROM Ordine WHERE idUtente=?";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, idUtente);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int idOrdine = rs.getInt("idOrdine");
	            LocalDate dataOrdine = rs.getDate("dataOrdine").toLocalDate();
	            double prezzoTotale = rs.getDouble("prezzoTotale");

	  
	            Ordine ordine = new Ordine(idOrdine, idUtente, dataOrdine, prezzoTotale);

	         
	            ArrayList<Prodotto> prodottiAcquistati = new ArrayList<>();
	            String queryProdotti = "SELECT p.idProdotto, p.nome, do.quantita " 
	                                    + "FROM dettaglioOrdine do "
	                                    + "JOIN Prodotto p ON do.idProdotto = p.idProdotto "
	                                    + "WHERE do.idOrdine = ?";

	            try (PreparedStatement psProdotti = connection.prepareStatement(queryProdotti)) {
	                psProdotti.setInt(1, idOrdine);
	                ResultSet rsProdotti = psProdotti.executeQuery();

	                while (rsProdotti.next()) {
	                    String idProdotto = rsProdotti.getString("idProdotto");
	                    String nomeProdotto = rsProdotti.getString("nome");
	                    int quantita = rsProdotti.getInt("quantita");

	                    Prodotto prodotto = new Prodotto(idProdotto, nomeProdotto, quantita);
	                    prodottiAcquistati.add(prodotto);
	                }

	             
	                ordine.setProdottiAcquistati(prodottiAcquistati);
	            }

	         
	            ordini.add(ordine);
	        }
	    }
	    return ordini;
	}


	public ArrayList<Ordine> selectAll() throws SQLException {
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		String query = "SELECT * FROM Ordine";
		try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String idUtente = rs.getString("idUtente");
				int idOrdine = rs.getInt("idOrdine");
				LocalDate dataOrdine = rs.getDate("dataOrdine").toLocalDate();
				double prezzoTotale = rs.getDouble("prezzoTotale");
				Ordine ordine = new Ordine(idOrdine, idUtente, dataOrdine, prezzoTotale);
				ordini.add(ordine);
			}
		}
		return ordini;
	}

	public ArrayList<OrdineCliente> cercaOrdini(String query) {
		ArrayList<OrdineCliente> ordini = new ArrayList<>();

		String sql = "SELECT o.idOrdine, o.dataOrdine, o.prezzoTotale, c.nome, c.cognome " + "FROM Ordine o "
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

	public void insertDettaglioOrdine(int idOrdine, String idProdotto, int quantitaAcquistata, double prezzoProdotto,
			double prezzoTotale) throws SQLException {
		String query = "INSERT INTO DettaglioOrdine (idOrdine, idProdotto, quantita, prezzoProdotto,prezzoTotale,nome,descrizione) VALUES (?, ?, ?, ?, ?,?,?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			String nome = getNomeProdottoById(idProdotto);
			String descrizione = getDescrizioneProdottoById(idProdotto);

			ps.setInt(1, idOrdine);
			ps.setString(2, idProdotto);
			ps.setInt(3, quantitaAcquistata);
			ps.setDouble(4, prezzoProdotto);
			ps.setDouble(5, prezzoTotale);
			ps.setString(6, nome);
			ps.setString(7, descrizione);
			ps.executeUpdate();

		}
	}

	private String getDescrizioneProdottoById(String idProdotto) {

		String query = "SELECT descrizione FROM Prodotto WHERE idProdotto = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idProdotto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("descrizione");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Prodotto non disponibile";
	}

	private String getNomeProdottoById(String idProdotto) {

		String query = "SELECT nome FROM Prodotto WHERE idProdotto = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, idProdotto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("nome");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Prodotto non disponibile";
	}

	public boolean prodottoEsistente(int idOrdine, String idProdotto) throws SQLException {
		String query = "SELECT COUNT(*) FROM DettaglioOrdine WHERE idOrdine = ? AND idProdotto = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, idOrdine);
			ps.setString(2, idProdotto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
		return false;
	}

	public void aggiornaQuantitaProdotto(int idOrdine, String idProdotto, int quantita) throws SQLException {
		String query = "UPDATE DettaglioOrdine SET quantita = quantita + ? WHERE idOrdine = ? AND idProdotto = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, quantita);
			ps.setInt(2, idOrdine);
			ps.setString(3, idProdotto);
			ps.executeUpdate();
		}
	}

}
