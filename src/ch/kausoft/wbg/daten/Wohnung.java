package ch.kausoft.wbg.daten;

public class Wohnung extends Daten {

   short nummer;
   short hausnummer;
   double faeche;
   double bewertung;

   public Wohnung(short nummer) {
      super("", "");
      this.nummer = nummer;
      this.hausnummer = (nummer <= 7) ? (short) 23 : (short) 25;
   }

   public void list() {
      System.out.println("Wohnung " + nummer //
            + " Haus " + hausnummer //
            + bezeichnung + " // " + beschreibung //
            + " Flaeche " + faeche //
            + " Bewertet " + bewertung);
   }
}
