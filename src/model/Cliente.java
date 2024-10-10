package model;

public class Cliente {

	    private String nome;
	    private String cognome;
	    private String sesso;
	    private String dataDiNascita;
	    private String email;
	    private String username;
	    private String passw;
	    private String ruolo;
	    private String cellulare;

	    public Cliente (String nome, String cognome, String sesso, String dataDiNascita, String email, String username, String passw, String ruolo, String cellulare) {
	        this.nome=nome;
	        this.cognome=cognome;
	        this.sesso=sesso;
	        this.dataDiNascita=dataDiNascita;
	        this.email=email;
	        this.username=username;
	        this.passw=passw;
	        this.ruolo=ruolo;
	        this.cellulare=cellulare;
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

	    public String getSesso() {
	        return sesso;
	    }

	    public void setSesso(String sesso) {
	        this.sesso = sesso;
	    }

	    public String getDataDiNascita() {
	        return dataDiNascita;
	    }

	    public void setDataDiNascita(String dataDiNascita) {
	        this.dataDiNascita = dataDiNascita;
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

	    public String getPassw() {
	        return passw;
	    }

	    public void setPassw(String passw) {
	        this.passw = passw;
	    }

	    public String getRuolo() {
	        return ruolo;
	    }

	    public void setRuolo(String ruolo) {
	        this.ruolo = ruolo;
	    }

	    public String getTelefono() {
	        return cellulare;
	    }

	    public void setTelefono(String telefono) {
	        this.cellulare = telefono;
	    }

	    @Override
	    public String toString() {
	        return "Cliente{" +
	                ", nome='" + nome + '\'' +
	                ", cognome='" + cognome + '\'' +
	                ", sesso='" + sesso + '\'' +
	                ", dataDiNascita=" + dataDiNascita +
	                ", email='" + email + '\'' +
	                ", username='" + username + '\'' +
	                ", passw='" + passw + '\'' +
	                ", ruolo='" + ruolo + '\'' +
	                ", cellulare='" + cellulare + '\'' +
	                '}';
	    }
	}

