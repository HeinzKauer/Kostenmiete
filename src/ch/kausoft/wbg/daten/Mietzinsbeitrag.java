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
	Investition invest;

	/**
	 * Dieser Zins und Amortisationssatz ist g�ltig ab diesem Jahr
	 */
	JahrMonat gueltigAb;

	public Investition getInvest() {
		return invest;
	}

	public void setInvest(Investition invest) {
		this.invest = invest;
	}

	public JahrMonat getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(JahrMonat gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public double getJaehrlicherMietzinsbeitrag() {
		return jaehrlicherMietzinsbeitrag;
	}

	public void setJaehrlicherMietzinsbeitrag(double jaehrlicherMietzinsbeitrag) {
		this.jaehrlicherMietzinsbeitrag = jaehrlicherMietzinsbeitrag;
	}

	/**
	 * Ab dem definierten Jahr wird j�hrlich dieser Betrag verwendet um die
	 * auflauffenden Zinsen und die Amortisation zu finanzieren.
	 */
	double jaehrlicherMietzinsbeitrag;

	/**
	 * 
	 * @param abJahr
	 * @param jaehrlicherMietzinsbeitrag
	 */
	public Mietzinsbeitrag(int abJahr, double jaehrlicherMietzinsbeitrag) {
		this(abJahr, 01, jaehrlicherMietzinsbeitrag);
	}
	
	/**
	 * 
	 * @param abJahr
	 * @param abMonat
	 * @param jaehrlicherMietzinsbeitrag
	 */
	public Mietzinsbeitrag(int abJahr, int abMonat, double jaehrlicherMietzinsbeitrag) {
		this.gueltigAb = new JahrMonat(abJahr, abMonat);
		this.jaehrlicherMietzinsbeitrag = jaehrlicherMietzinsbeitrag;
	}

}
