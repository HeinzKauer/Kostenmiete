package ch.kausoft.wbg.daten.daoHelp;

/**
 * Dieses Jahr Monat repräsentiert einen bestimmten Monat wobei 01 = Januar ist 
 * 
 * @author Heinz
 *
 */
public class JahrMonat {

	private int jahr;
	private int monat;

	public JahrMonat(int jahr, int monat) {
		this.jahr = jahr;
		this.monat = monat;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public int getMonat() {
		return monat;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

}
