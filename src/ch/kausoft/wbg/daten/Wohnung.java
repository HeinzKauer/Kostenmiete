package ch.kausoft.wbg.daten;

import ch.kausoft.wbg.daten.daoHelp.Daten;

public class Wohnung extends Daten {

   short nummer;
   short hausnummer;
   double flaeche;
   double bewertung;

   public Wohnung(short nummer) {
      super("", "");
      this.nummer = nummer;
      this.hausnummer = (nummer <= 7) ? (short) 23 : (short) 25;
   }

   public void list() {
      System.out.println("Wohnung " + nummer //
            + " Haus " + hausnummer //
            + bezeichnung + " // " + getBeschreibung() 
            + " Flaeche " + flaeche //
            + " Bewertet " + bewertung);
   }
}
