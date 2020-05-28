package tabellendarstellung;


//Programmierung eines angepassten Objektes fuer die Tabelle
public class Zeile {
	
	private String artikelnr, artikelname, hersteller, beschr, materialangaben, geschlecht, produktart,
	aermel, bein, kragen, herstellung, taschenart, grammatur, material, land, bildname;
	
	public Zeile() {
	}
	
	public Zeile(String[] artikel) {	//ueberladener Konstruktor, damit nicht 16 Zeilen zum setzen
										//bei der Initialisierung verwendet werden muessen
		this.artikelnr = artikel[0];
		this.artikelname = artikel[1];
		this.hersteller = artikel[2];
		this.beschr = artikel[3];
		this.materialangaben = artikel[4];
		this.geschlecht = artikel[5];
		this.produktart = artikel[6];
		this.aermel = artikel[7];
		this.bein = artikel[8];
		this.kragen = artikel[9];
		this.herstellung = artikel[10];
		this.taschenart = artikel[11];
		this.grammatur = artikel[12];
		this.material = artikel[13];
		this.land = artikel[14];
		this.bildname = artikel[15];
	}
	
	public String getArtikelnr() {					//Setter fuer den spaeteren Zugriff
		return artikelnr;
	}

	public void setArtikelnr(String artikelnr) {
		this.artikelnr = artikelnr;
	}

	public String getArtikelname() {
		return artikelname;
	}

	public void setArtikelname(String artikelname) {
		this.artikelname = artikelname;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getBeschr() {
		return beschr;
	}

	public void setBeschr(String beschr) {
		this.beschr = beschr;
	}

	public String getMaterialangaben() {
		return materialangaben;
	}

	public void setMaterialangaben(String materialangaben) {
		this.materialangaben = materialangaben;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getProduktart() {
		return produktart;
	}

	public void setProduktart(String produktart) {
		this.produktart = produktart;
	}

	public String getAermel() {
		return aermel;
	}

	public void setAermel(String aermel) {
		this.aermel = aermel;
	}

	public String getBein() {
		return bein;
	}

	public void setBein(String bein) {
		this.bein = bein;
	}

	public String getKragen() {
		return kragen;
	}

	public void setKragen(String kragen) {
		this.kragen = kragen;
	}

	public String getHerstellung() {
		return herstellung;
	}

	public void setHerstellung(String herstellung) {
		this.herstellung = herstellung;
	}

	public String getTaschenart() {
		return taschenart;
	}

	public void setTaschenart(String taschenart) {
		this.taschenart = taschenart;
	}

	public String getGrammatur() {
		return grammatur;
	}

	public void setGrammatur(String grammatur) {
		this.grammatur = grammatur;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getBildname() {
		return bildname;
	}

	public void setBildname(String bildname) {
		this.bildname = bildname;
	}
	
	
	
	
	
}
