package ch.kausoft.wbg.daten.dao;

import java.util.HashMap;

import ch.kausoft.wbg.daten.daoHelp.Daten;

/**
 * 
 * <h1>Investitionen</h1> Eine Investition wird getätigt und dann zu einem
 * bestimmten Zeitpunkt Aktiviert. Danach muss diese Investition Amortisiert
 * oder abgetragen werden. Eine werterhaltende Investition sollte durch
 * angespartes Kapital aus einem Erneuerungsfond getilt werden können. Der
 * Wertvermehrende Anteil muss innerhalb einer Lebensdauer Amortisiert werden.
 * 
 * <h2>Tilgung aus dem Erneuerungs Fond</h2> Werterhaltende Massnahmen sollten
 * zum Teil aus dem Erneuerungfond und zum Teil aus Eigenkapital Finanziert
 * werden können.
 * 
 * <h2>Tilgung durch Amortiasation währen einer Lebensdauer</h2> Die Investition
 * Wert vermehrender Massnahmen sollten innerhalb der Lebensdauer Amortisiert
 * und getilgt werden.
 * 
 * 
 * 
 * @author Heinz
 * 
 */
public class Investition extends Daten {

	/**
	 * Ein investierter Betrag der während einer bestimmten Dauer amortisiert
	 * und getilgt werden soll
	 */
	public 	int investitionsBetrag;

	/**
	 * Ein Teil der Investition kann aus dem Erneuerungsfond getilgt werden.
	 */
	public int tilgungAusErneuerungsFond;

	/**
	 * Differenz investitionsBetrag - tilgungAusErneuerungsFond
	 */
	public 	int tilgungDurchAmortisation;

	/**
	 * Aktiviwreungsjahr
	 */
	public int aktivierungsJahr;

	/**
	 * Aktivierungs Monat / Default = Januar
	 */
	public int aktivierungsMonat = 01;

	/**
	 * Die Lebensdauer in Jahren
	 */
	public int lebensdauerInJahre;

	/**
    * 
    */
	public double tilgungProWohnung[] = new double[14];

	public Tilgung tilgung;

	/**
	 * Wird Amortisiert vom Kapital
	 */
	public  Kapital kapital;

	HashMap<Integer, Mietzinsbeitrag> mietzinsbeitrag = new HashMap<Integer, Mietzinsbeitrag>();

	public Investition(String bezeichnung) {
		super(bezeichnung, "");
	}

	public Investition(String bezeichnung, String beschreibung,
			int investitionsBetrag, int investitionAusRueckstellung,
			int aktivierungsjahr, int lebensdauerInJahre, Kapital kapital) {

		super(bezeichnung, beschreibung);

		this.investitionsBetrag = investitionsBetrag;
		this.tilgungAusErneuerungsFond = investitionAusRueckstellung;
		this.aktivierungsJahr = aktivierungsjahr;
		this.lebensdauerInJahre = lebensdauerInJahre;
		this.tilgungDurchAmortisation = investitionsBetrag
				- investitionAusRueckstellung;
		this.kapital = kapital;
	}

	public Zinssatz getKapitalZins(int jahr) {
		try {
			return kapital.getKapitalZins(jahr);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Mietzinsbeitrag getMietzinsbeitrag(int jahr) {
		for (int i = jahr; i >= 2000; i--) {
			Mietzinsbeitrag x = mietzinsbeitrag.get(i);
			if (x != null) {
				return x;
			}
		}
		return null;
	}

	public void addMietzinsbeitrag(Mietzinsbeitrag mietzinsbeitrag) {
		mietzinsbeitrag.invest = this;
		this.mietzinsbeitrag.put(mietzinsbeitrag.getGueltigAb().getJahr() , mietzinsbeitrag);
	}
}
