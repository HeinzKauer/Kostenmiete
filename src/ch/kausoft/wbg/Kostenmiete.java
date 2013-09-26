package ch.kausoft.wbg;

import ch.kausoft.tool.CsvReader;
import ch.kausoft.wbg.daten.DataImporter;
import ch.kausoft.wbg.daten.Investition;
import ch.kausoft.wbg.daten.Kapital;
import ch.kausoft.wbg.daten.Mietzinsbeitrag;
import ch.kausoft.wbg.daten.Tilgung;
import ch.kausoft.wbg.daten.Wohnung;
import ch.kausoft.wbg.daten.Zinssatz;

public class Kostenmiete {

   public static DatenSpeicher datenSpeicher;

   /**
    * @param args
    */
   public static void main(String[] args) {
      new Kostenmiete().run();
   }

   private void run() {
      datenSpeicher = loadData();

      // Investition i2 = ds.getInvestition("Parketboden");
      //
      // Tilgung t = new Tilgung(i2).TilgungRechnen();
      // t.listConsoloutput();
      //
      //
      // Tilgung tp = new Tilgung(i2);
      // tp.TilgungsPlanRechnen();
      // tp.listConsoloutput();

      tilgungsplanRechnen();
      listInvest();
      listWohnungTilgung();
   }

   private void listWohnungTilgung() {
      Wohnung[] woh = datenSpeicher.getAllWohnung();
      for (int i = 0; i < woh.length; i++) {
         System.out.println();
         woh[i].list();
         Investition[] inv = datenSpeicher.getAllInvestition();
         for (int j = 0; j < inv.length; j++) {
            if (inv[j].tilgungProWohnung[i] > 0 ) {
               Tilgung t = inv[j].tilgung;  
               t.consolOutDetail(inv[j],inv[j].tilgungProWohnung[i]);
            }
         }
      }
   }

   private DatenSpeicher loadData() {
      DataImporter importer = new DataImporter();
      return importer.load();
   }

   private void tilgungsplanRechnen() {
      Investition[] inv = datenSpeicher.getAllInvestition();
      for (int i = 0; i < inv.length; i++) {
         Investition investition = inv[i];
         Tilgung t = new Tilgung(investition);
         t.TilgungsPlanRechnen();
         t.listConsoloutput();
      }
   }

   private void listInvest() {
      Investition[] inv = datenSpeicher.getAllInvestition();
      for (int i = 0; i < inv.length; i++) {
         Investition investition = inv[i];
         Tilgung t = investition.tilgung;
         t.listConsoloutput();
      }
   }

}