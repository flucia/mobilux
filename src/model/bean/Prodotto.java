package model.bean;

import java.io.Serializable;

public class Prodotto implements Serializable {
	private String idProdotto;
	private String nome;
	private int quantità;
	private double prezzo;
	private String descrizione;
	private String immagine;
	private String idCategoria;
	private int quantitaDisponibile; 
	
	
	public Prodotto() {}
	
	 
    public Prodotto(String idProdotto, String nome, int quantità) {
    	this.idProdotto = idProdotto;
		this.nome = nome;
		this.quantità = quantità;
    }
	
	public Prodotto(String idProdotto, String nome, double prezzo,String descrizione,String immagine,String idCategoria,int quantitaDisponibile) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.idCategoria = idCategoria;
		this.quantitaDisponibile = quantitaDisponibile;
	}
	
	public Prodotto(String idProdotto,String nome,double prezzo, String descrizione) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		
	}

	public Prodotto(String idProdotto, String nome, int quantità, double prezzo,String descrizione,String immagine,String idCategoria,int quantitaDisponibile) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.quantità = quantità;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.immagine = immagine;
		this.idCategoria = idCategoria;
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	@Override
	public String toString() {
		return "Prodotto [idProdotto=" + idProdotto +
				", nome=" + nome + 
				", quantità=" + quantità + 
				", prezzo=" + prezzo + 
				", descrizione=" + descrizione +
				", immagine=" + immagine +
				", idCategoria=" + idCategoria +
				", quantitaDisponibile=" + quantitaDisponibile +
				"]";
	}
}
