package ch.kausoft.wbg.daten;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.kausoft.wbg.daten.dao.Kapital;

/**
 * Laden der Daten.
 * 
 * @author Heinz
 * 
 */
public class DataImporter {

	DatenSpeicher ds = new DatenSpeicher();

	public DatenSpeicher load() {

		String name = "C:\\eclipse2013\\git\\github\\Kostenmiete\\src\\ch\\kausoft\\wbg\\daten\\InputDaten.csv";
		File f = new File(name);
		FileInputStream fis;
		InputStreamReader isr = null;
		BufferedReader br;

		String zeile;
		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			zeile = br.readLine();
			while (zeile != null) {
				System.out.println(zeile);
				parsData(zeile);
				zeile = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		return ds;
	}

	private void parsData(String zeile) {
		String[] d = zeile.split(",");
		if (d != null && d.length <= 3)
			return; // Zeile überlesen
		if (d[0].equalsIgnoreCase("Kapital")) {
			parsKapital(d);
		} else if (d[0].equalsIgnoreCase("Investition")) {
			parsInvestition(d);
		} else if (d[0].equalsIgnoreCase("Wohnung")) {
			parsWohnung(d);
		}
	}

	private void parsWohnung(String[] d) {
		short hausNr = Short.parseShort(d[1]);
		short wNummer = Short.parseShort(d[2]);
		Wohnung wohnung = ds.getWohnung(wNummer);
		wohnung.setBezeichnung( d[3]);
		wohnung.setBeschreibung(d[4]);
		wohnung.hausnummer = hausNr;
		wohnung.flaeche = Double.parseDouble(d[5]);
		wohnung.bewertung = Double.parseDouble(d[6]);
	}

	private void parsInvestition(String[] d) {
		Investition iv = ds.getInvestition(d[1]); // Bezeichnung
		if (d[2].equalsIgnoreCase("Beschreibung")) {
			iv.setBeschreibung( d[3]);
		} else if (d[2].equalsIgnoreCase("Kapital")) {
			iv.kapital = ds.getKapital(d[3]);
		} else if (d[2].equalsIgnoreCase("Investition")) {
			if (d.length >= 7) {
				try {
					iv.aktivierungsJahr = Integer.parseInt(d[3]);
					iv.investitionsBetrag = Integer.parseInt(d[4]);
					iv.tilgungAusErneuerungsFond = Integer.parseInt(d[5]);
					iv.lebensdauerInJahre = Integer.parseInt(d[6]);
					iv.tilgungDurchAmortisation = iv.investitionsBetrag
							- iv.tilgungAusErneuerungsFond;
				} catch (Exception e) {
					System.out
							.println("130802-0040 Daten für Investition fehlerhaft");
				}
			} else {
				System.out
						.println("130802-0033 Daten für Investition fehlerhaft");
			}
		} else if (d[2].equalsIgnoreCase("Mietzinsbeitrag")) {
			if (d.length >= 5) {
				try {
					iv.addMietzinsbeitrag(new Mietzinsbeitrag(//
							Integer.parseInt(d[3]), //
							Double.parseDouble(d[4])));
				} catch (Exception e) {
					System.out
							.println("130802-0044 Daten für Mietzinsbeitrag fehlerhaft");
				}
			} else {
				System.out
						.println("130802-0042 Daten für Mietzinsbeitrag fehlerhaft");
			}
		} else if (d[2].equalsIgnoreCase("Tilgung")) {
			if (d[3].equalsIgnoreCase("Wohnung")) {
				short wNummer = Short.parseShort(d[4]);
				iv.tilgungProWohnung[wNummer - 1] = Double.parseDouble(d[5]);
			} else if (d[3].equalsIgnoreCase("Linear")) {
				for (int i = 0; i < iv.tilgungProWohnung.length; i++) {
					iv.tilgungProWohnung[i] = 100.00d / iv.tilgungProWohnung.length;
				}
			} else if (d[3].equalsIgnoreCase("Wohnflaeche")) {
				if (ds.wohnungsanteilFlaeche == null) {
					ds.wohnungsanteilFlaeche = new double[14];
					double summe = 0;
					for (int i = 0; i < ds.wohnungen.length; i++) {
						summe += ds.wohnungen[i].flaeche;
					}
					for (int i = 0; i < ds.wohnungen.length; i++) {
						ds.wohnungsanteilFlaeche[i] = (ds.wohnungen[i].flaeche * 100)
								/ summe;
					}
				}
				iv.tilgungProWohnung = ds.wohnungsanteilFlaeche;

			} else if (d[3].equalsIgnoreCase("Bewertungsindex")) {
				if (ds.wohnungsanteilBewertet == null) {
					ds.wohnungsanteilBewertet = new double[14];
					double summe = 0;
					for (int i = 0; i < ds.wohnungen.length; i++) {
						summe += ds.wohnungen[i].bewertung;
					}
					for (int i = 0; i < ds.wohnungen.length; i++) {
						ds.wohnungsanteilBewertet[i] = (ds.wohnungen[i].bewertung * 100)
								/ summe;
					}
				}
				iv.tilgungProWohnung = ds.wohnungsanteilBewertet;
			}
		}
	}

	private void parsKapital(String[] d) {
		Kapital k = ds.getKapital(d[1]);
		if (d[2].equalsIgnoreCase("Beschreibung")) {
			k.setBeschreibung( d[3]);
		} else if (d[2].equalsIgnoreCase("Zinssatz")) {
			if (d.length >= 8) {
				k.addKapitalZins( //
				Zinssatz.getInstance(d[3], d[4], d[5], d[6], d[7]));
			} else {
				System.out.println("130801-2359 Daten für Zinssatz fehlerhaft");
			}
		}
	}
}
