package ch.kausoft.wbg.daten;

/**
 * f�r die Tilgung einer Investition kann ein j�hrlicher Mietzinsbeitrag
 * verwendet werden. Von diesem Beitrag werden die Zinskosten abgezogen und der
 * Rest wird zur Schuldamortisation verwendet.
 * 
 * Jeder Investition hat pro jahr die Mietzinsbeitrag definiert
 * 
 * @author Heinz
 * 
 */
public class Mietzinsbeitrag {

	/**
	 * Investition f�r diese diesen Mietzinsbeitrag g�ltig ist
	 */
	public Investition invest;

	/**
	 * Dieser Zins und Amortisationssatz ist g�ltig ab diesem Jahr
	 */
	JahrMonat gueltigAb;

	/**
	 * Ab dem definierten Jahr wird j�hrlich dieser Betrag verwendet um die
	 * auflauffenden Zinsen und die Amortisation zu finanzieren.
	 */
	double jaehrlicherMietzinsbeitrag;

	public Mietzinsbeitrag(int abJahr, double jaehrlicherMietzinsbeitrag) {
		this.gueltigAb = new JahrMonat(abJahr, 01);
		this.jaehrlicherMietzinsbeitrag = jaehrlicherMietzinsbeitrag;
	}

}
