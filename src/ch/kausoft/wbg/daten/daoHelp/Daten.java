package ch.kausoft.wbg.daten.daoHelp;

public class Daten {

  /**
   * Bezeichnung 
   */
	public String bezeichnung;

  public String getBezeichnung() {
	return bezeichnung;
}

public void setBezeichnung(String bezeichnung) {
	this.bezeichnung = bezeichnung;
}

public String getBeschreibung() {
	return beschreibung;
}

public void setBeschreibung(String beschreibung) {
	this.beschreibung = beschreibung;
}

/**
   * Beschreibung 
   */
  private  String beschreibung;

  public Daten(String bezeichnung, String beschreibung) {
    this.bezeichnung = bezeichnung;
    this.beschreibung = beschreibung;
  }

}
