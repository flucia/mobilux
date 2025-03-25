package model.bean;

import java.io.Serializable;

public class Carrello implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idUtente;
	private String idProdotto;
	private int quantita;
	private Prodotto prodotto;
	private double prezzo;

	public Carrello() {
	}

	public Carrello(String idUtente, String idProdotto, int quantita, Prodotto prodotto, double prezzo) {
		super();
		this.idUtente = idUtente;
		this.idProdotto = idProdotto;
		this.quantita = quantita;
		this.prodotto = prodotto;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Carrello [idUtente=" + idUtente + ", idProdotto=" + idProdotto + ", quantita=" + quantita
				+ ", prodotto=" + prodotto + ",prezzo=" + prezzo + "]";
	}

}
