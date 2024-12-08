package model.bean;

import java.time.LocalDate;

public class OrdineCliente {
	    private int idOrdine;
	    private LocalDate dataOrdine;
	    private double prezzoTotale;
	    private Cliente cliente;

	    // Getters e Setters
	    public int getIdOrdine() { return idOrdine; }
	    public void setIdOrdine(int idOrdine) { this.idOrdine = idOrdine; }

	    public LocalDate getDataOrdine() { return dataOrdine; }
	    public void setDataOrdine(LocalDate dataOrdine) { this.dataOrdine = dataOrdine; }

	    public double getPrezzoTotale() { return prezzoTotale; }
	    public void setPrezzoTotale(double prezzoTotale) { this.prezzoTotale = prezzoTotale; }

	    public Cliente getCliente() { return cliente ; }
	    public void setCliente(Cliente cliente) { this.cliente = cliente; }

	}

