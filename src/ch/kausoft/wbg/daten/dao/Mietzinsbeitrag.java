package ch.kausoft.wbg.daten.dao;

import ch.kausoft.wbg.daten.daoHelp.JahrMonat;

/**
 * für die Tilgung einer Investition kann ein jährlicher Mietzinsbeitrag
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
	 * Investition für diese diesen Mietzinsbeitrag gültig ist
	 */
	public Investition invest;

	/**
	 * Dieser Zins und Amortisationssatz ist gültig ab diesem Jahr
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
	 * Ab dem definierten Jahr wird jährlich dieser Betrag verwendet um die
	 * auflauffenden Zinsen und die Amortisation zu finanzieren.
	 */
	public double jaehrlicherMietzinsbeitrag;

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
