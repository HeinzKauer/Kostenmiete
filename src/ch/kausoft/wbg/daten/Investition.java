package ch.kausoft.wbg.daten;

import java.util.HashMap;

/**
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
	int investitionsBetrag;

	/**
	 * Ein Teil der Investition kann aus Rückstellungen getilgt werden.
	 */
	int investitionAusRueckstellung;

	/**
	 * Differenz investitionsBetrag - investitionAusRueckstellung
	 */
	int investitionZumTilgen;

	/**
	 * Aktiviwreungsjahr
	 */
	int aktivierungsjahr;

	/**
   * 
   */
	int lebensdauerInJahre;

	/**
    * 
    */
	public double tilgungProWohnung[] = new double[14];

	public Tilgung tilgung;

	/**
	 * Wird Amortisiert vom Kapital
	 */
	Kapital kapital;

	HashMap<Integer, Mietzinsbeitrag> mietzinsbeitrag = new HashMap<Integer, Mietzinsbeitrag>();

	public Investition(String bezeichnung) {
		super(bezeichnung, "");
	}

	public Investition(String bezeichnung, String beschreibung,
			int investitionsBetrag, int investitionAusRueckstellung,
			int aktivierungsjahr, int lebensdauerInJahre, Kapital kapital) {

		super(bezeichnung, beschreibung);

		this.investitionsBetrag = investitionsBetrag;
		this.investitionAusRueckstellung = investitionAusRueckstellung;
		this.aktivierungsjahr = aktivierungsjahr;
		this.lebensdauerInJahre = lebensdauerInJahre;
		this.investitionZumTilgen = investitionsBetrag
				- investitionAusRueckstellung;
		this.kapital = kapital;
	}

	public Zinssatz getKapitalZins(int jahr) {
		return kapital.getKapitalZins(jahr);
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
		this.mietzinsbeitrag.put(mietzinsbeitrag.abJahr, mietzinsbeitrag);
	}
}
