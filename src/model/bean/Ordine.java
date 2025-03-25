package model.bean;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ordine {
	private int idOrdine;
	private String idUtente;
	private LocalDate dataOrdine;
	private double prezzoTotale;
	private double prezzoProdotto;
	private ArrayList<Prodotto> prodottiAcquistati;

	public Ordine() {
	}

	public Ordine(int idOrdine, String idUtente, LocalDate dataOrdine, double prezzoTotale) {
		this.idOrdine = idOrdine;
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
		this.prezzoTotale = prezzoTotale;
	}

	public Ordine(String idUtente, LocalDate dataOrdine) {
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
	}

	public Ordine(String idUtente, LocalDate dataOrdine, double prezzoProdotto) {
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
		this.prezzoProdotto = prezzoProdotto;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public ArrayList<Prodotto> getProdottiAcquistati() {
		return prodottiAcquistati;
	}

	public void setProdottiAcquistati(ArrayList<Prodotto> prodottiAcquistati) {
		this.prodottiAcquistati = prodottiAcquistati;
	}
	public double getPrezzoProdotto() {
		return prezzoProdotto;
	}

	public void setPrezzoProdotto(double prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}

	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", idUtente=" + idUtente + ", dataOrdine=" + dataOrdine
				+ ", prezzoTotale=" + prezzoTotale + ", prezzoProdotto=" + prezzoProdotto + " ]";
	}

}
