package model.bean;

import java.io.Serializable;

public class DettaglioOrdine implements Serializable{
	private String idDettaglioOrdine;
	private int idOrdine;
	private String idProdotto;
	private double quantita;
	private double prezzoProdotto;
	private double prezzoTotale;
	private String nome;
	private String descrizione;
	
	public DettaglioOrdine() {}
	
	public DettaglioOrdine(String idDettaglioOrdine, int idOrdine, String idProdotto, double quantita,
			double prezzoProdotto, double prezzoTotale, String nome, String  descrizione) {
		
		this.idDettaglioOrdine = idDettaglioOrdine;
		this.idOrdine = idOrdine;
		this.idProdotto = idProdotto;
		this.quantita = quantita;
		this.prezzoProdotto = prezzoProdotto;
		this.prezzoTotale = prezzoTotale;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void detDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIdDettaglioOrdine() {
		return idDettaglioOrdine;
	}
	public void setIdDettaglioOrdine(String idDettaglioOrdine) {
		this.idDettaglioOrdine = idDettaglioOrdine;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	public double getQuantita() {
		return quantita;
	}
	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}
	public double getPrezzoProdotto() {
		return prezzoProdotto;
	}
	public void setPrezzoProdotto(double prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}
	public double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	

}
