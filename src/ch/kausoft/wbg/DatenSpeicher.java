package ch.kausoft.wbg;

import java.util.HashMap;
import ch.kausoft.wbg.daten.Investition;
import ch.kausoft.wbg.daten.Kapital;
import ch.kausoft.wbg.daten.Wohnung;

public class DatenSpeicher {

	/**
	 * 
	 */
	private HashMap<String, Kapital> kapital = new HashMap<String, Kapital>();
	private HashMap<String, Investition> invest = new HashMap<String, Investition>();
	public Wohnung[] wohnungen = new Wohnung[14];
	public double wohnungsanteilFlaeche[];
	public double wohnungsanteilBewertet[];

	public Kapital getKapital(String id) {
		Kapital k = kapital.get(id);
		return (k != null) ? k : kapital.put(id, new Kapital(id));
	}

	public Investition getInvestition(String id) {
		Investition iv = invest.get(id);
		return (iv != null) ? iv : invest.put(id, new Investition(id));
	}

	public Wohnung getWohnung(short wNummer) {
		if (wohnungen[wNummer - 1] == null) {
			wohnungen[wNummer - 1] = new Wohnung(wNummer);
		}
		return wohnungen[wNummer - 1];
	}

	public Investition[] getAllInvestition() {
		Investition[] inv = new Investition[invest.size()];
		int i = 0;
		for (Investition iv : invest.values())
			inv[i++] = iv;
		return inv;
	}

	public Wohnung[] getAllWohnung() {
		return wohnungen;
	}

}
