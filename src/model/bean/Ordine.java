package model.bean;

import java.util.Date;

public class Ordine {
	private String idOrdine;
	private String idUtente;
	private Date dataOrdine;
	
	public Ordine() {}
	
	public Ordine(String idOrdine, String idUtente, Date dataOrdine) {
		super();
		this.idOrdine = idOrdine;
		this.idUtente = idUtente;
		this.dataOrdine = dataOrdine;
	}
	public String getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(String idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public Date getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", idUtente=" + idUtente + ", dataOrdine=" + dataOrdine + "]";
	}
	
}
