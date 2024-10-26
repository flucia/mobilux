package model.bean;

public class Cliente {

	private String cf;
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	private String ruolo;
	private String indirizzo;
	private String cellulare;

	public Cliente() {}
	public Cliente (String cf,String nome, String cognome, String username,String email, String password, String ruolo, String indirizzo, String cellulare) {
		this.cf = cf;
		this.nome=nome;
		this.cognome=cognome;
		this.username=username;
		this.email=email;
		this.password=password;
		this.ruolo=ruolo;
		this.cellulare=cellulare;
		this.indirizzo=indirizzo;
	}

	public String getCodiceFiscale() {
		return cf;
	}

	public void setCodiceFiscale(String cf) {
		this.cf = cf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassw(String passw) {
		this.password = passw;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				", nome='" + cf + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", passw='" + password + '\'' +
				", ruolo='" + ruolo + '\'' +
				", cellulare='" + cellulare + '\'' +
				", indirizzo='" + indirizzo + '\'' +
				'}';
	}
}

