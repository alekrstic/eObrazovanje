package rs.ac.uns.ftn.eo.students.web.dto;


import rs.ac.uns.ftn.eo.students.model.Korisnik;

public class KorisnikDTO {
	
	private long id;
	String username;
	private String password;
	private String uloga;
	private String ime;
	private String prezime;

	
	public KorisnikDTO() {
		
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		id = korisnik.getId();
		username = korisnik.getUsername();
		password = korisnik.getPassword();
		uloga = korisnik.getUloga();
		ime = korisnik.getIme();
		prezime = korisnik.getPrezime();

	}	

	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUloga(){
		return uloga;
	}
	
	public String getIme(){
		return ime;
	}
	
	public String getPrezime(){
		return prezime;
	}


}
