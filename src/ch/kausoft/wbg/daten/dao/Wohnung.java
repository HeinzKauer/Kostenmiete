package ch.kausoft.wbg.daten.dao;

import ch.kausoft.wbg.daten.daoHelp.Daten;

public class Wohnung extends Daten {

   public short nummer;
   public    short hausnummer;
   public    double flaeche;
   public    double bewertung;

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
