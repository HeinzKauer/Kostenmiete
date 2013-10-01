package ch.kausoft.wbg.daten;

public class Tilgung {

	/**
	* eine Investition wird getilgt 
	*/
   Investition invest;
   
   /**
    * Es wir eine Verkettet Liste aufgebaut 
    */
   Tilgung tilgungBefore;
   Tilgung tilgungNext;

   int zinsCase;
   int jahr;

   double betrag;
   double zinsBetrag;
   double amortisationsBetrag;

   public Tilgung(Investition invest, int zinsCase) {
      this.invest = invest;
      invest.tilgung = this;
      this.zinsCase = zinsCase;
      this.betrag = invest.investitionZumTilgen;
      this.jahr = invest.aktivierungsjahr;
   }

   private Tilgung(Tilgung tparent) {
      this.tilgungBefore = tparent;
      tparent.tilgungNext = this;

      this.invest = tilgungBefore.invest;
      this.zinsCase = tilgungBefore.zinsCase;
      this.betrag = tilgungBefore.betrag - tilgungBefore.amortisationsBetrag;
      this.jahr = tilgungBefore.jahr + 1;
   }

   public void rechnenMietzinsbeitragVorgegeben() {
      Zinssatz kz = invest.getKapitalZins(this.jahr);
      zinsBetrag = betrag * kz.zins[zinsCase] / 100;
      Mietzinsbeitrag mb = invest.getMietzinsbeitrag(this.jahr);
      amortisationsBetrag = mb.jaehrlicherMietzinsbeitrag - zinsBetrag;
   }

   /**
    * 
    * @param jaehrlicherMietzinsbeitrag
    * @return restschuld
    */
   public double rechnenJaehrlicheBeitraege(double jaehrlicherMietzinsbeitrag) {
      Zinssatz kz = invest.getKapitalZins(this.jahr);
      zinsBetrag = betrag * kz.zins[zinsCase] / 100;
      amortisationsBetrag = jaehrlicherMietzinsbeitrag - zinsBetrag;
      // return betrag;
      return betrag - amortisationsBetrag;
   }

   public void consolOut() {

      System.out.println(jahr
            + " "
            + (int) betrag
            + " "
            + (int) zinsBetrag
            + " "
            + (int) amortisationsBetrag
            + " "
            + (int) ((zinsBetrag + amortisationsBetrag) / 12)
            + " "
            + ((invest.lebensdauerInJahre
                  - (this.jahr - invest.aktivierungsjahr) > 0) ? this.jahr
                  - invest.aktivierungsjahr + 1
                  : "�berschreitung Amortisierungsdauer")

      );

   }

   public void consolOutDetail(double zins, Investition invest2) {
      System.out.println(invest2.bezeichnung + " Betrag " + betrag
            + " Zinskosten " + zins + " Miete / Jahr "
            + ((zinsBetrag + amortisationsBetrag)) + " Miete / Monat "
            + ((zinsBetrag + amortisationsBetrag) / 12));
   }

   public void consolOutDetail(Investition invest2, double wohnungsanteil) {
      boolean gerundet = true;
      if (gerundet) {
         System.out
               .println(invest2.bezeichnung
                     + " Betrag " //
                     + (int) (betrag * wohnungsanteil / 100)//
                     + " (" //
                     + (int) (zinsBetrag * wohnungsanteil / 100)//
                     + "," //
                     + (int) (amortisationsBetrag * wohnungsanteil / 100) //
                     + ") Miete / Jahr " //
                     + ((int) ((zinsBetrag + amortisationsBetrag) * wohnungsanteil) / 100) //
                     + " Monat " //
                     + ((int) ((zinsBetrag + amortisationsBetrag) / 12 * wohnungsanteil) / 100));
      } else {
         System.out
               .println(invest2.bezeichnung
                     + " Betrag "
                     + betrag
                     * wohnungsanteil
                     / 100 //
                     + " (" //
                     + (int) (zinsBetrag * wohnungsanteil / 100)//
                     + "," //
                     + (int) (amortisationsBetrag * wohnungsanteil / 100) //
                     + ") Miete / Jahr " //
                     + (zinsBetrag + amortisationsBetrag)
                     * wohnungsanteil
                     / 100 //
                     + " Monat " //
                     + (zinsBetrag + amortisationsBetrag) / 12 * wohnungsanteil
                     / 100);

      }
   }

   private int amortisationsjahr() {
      return (jahr - invest.aktivierungsjahr + 1);
   }

   public boolean fertig() {
      return betrag <= 0;
   }

   public Tilgung TilgungRechnen() {
      Tilgung t = this;
      do {
         t.rechnenMietzinsbeitragVorgegeben();
         t = new Tilgung(t);
      } while (!t.fertig() && t.amortisationsjahr() < 200);
      return this;
   }

   public Tilgung TilgungsPlanRechnen() {
      Tilgung t = this;

      double beitrag = 0d;
      double resrschuld;
      do {
         beitrag += 1000d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 1000d);
      beitrag -= 1000d;
      do {
         beitrag += 100d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 100d);
      beitrag -= 100d;
      do {
         beitrag += 10d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 10d);
      beitrag -= 10d;
      do {
         beitrag += 1d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 1d);
      beitrag -= 1d;
      do {
         beitrag += 0.1d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 0.1d);
      beitrag -= 0.1d;
      do {
         beitrag += 0.10d;
         resrschuld = TilgungsPlanRechnenIntern(beitrag);
         // System.out.println("--->" + beitrag + "," + resrschuld);
      } while (resrschuld > 0.01d);

      // System.out.println("--->" + beitrag + "," + resrschuld);
      return this;
   }

   private double TilgungsPlanRechnenIntern(double beitrag) {
      Tilgung t = this;
      double resrschuld;
      do {
         resrschuld = t.rechnenJaehrlicheBeitraege(beitrag);
         t = new Tilgung(t);
         if (t.betrag < t.amortisationsBetrag) {

         }
      } while (t.amortisationsjahr() < (this.invest.lebensdauerInJahre + 1));
      // } while (t.betrag > t.amortisationsBetrag);
      return resrschuld;
   }

   public void listConsoloutput() {
      System.out.println("co --------------------------");

      Tilgung l = this;
      double zins = 0d;
      do {
         // l.consolOut();
         zins += l.zinsBetrag;
         l = l.tilgungNext;
      } while (l != null);

      this.consolOutDetail(zins, this.invest);

   }

}
