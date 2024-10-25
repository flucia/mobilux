package model.bean;

import java.time.LocalDate;

public class Ordine {
	private int idOrdine;
	private String idUtente;
	private LocalDate dataOrdine;
	private double prezzoTotale;
	
	public Ordine() {}
	
	public Ordine(int idOrdine,String idUtente, LocalDate dataOrdine,double prezzoTotale) {
		this.idOrdine = idOrdine;
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
		this.prezzoTotale = prezzoTotale;
	}
	public Ordine( String idUtente, LocalDate dataOrdine) {
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
	}
	
	public Ordine( String idUtente, LocalDate dataOrdine,double prezzoTotale) {
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
		this.prezzoTotale = prezzoTotale;
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

	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", idUtente=" + idUtente + ", dataOrdine=" + dataOrdine +
				", prezzoTotale=" + prezzoTotale+ "]";
	}
	
}
