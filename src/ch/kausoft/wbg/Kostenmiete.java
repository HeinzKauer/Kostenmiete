package ch.kausoft.wbg;

import ch.kausoft.wbg.daten.DataImporter;
import ch.kausoft.wbg.daten.DatenSpeicher;
import ch.kausoft.wbg.daten.dao.Investition;
import ch.kausoft.wbg.daten.dao.Tilgung;
import ch.kausoft.wbg.daten.dao.Wohnung;
import ch.kausoft.wbg.daten.dao.ZinsCase;

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
      tilgungsplanRechnen();
      listInvest();
      listWohnungTilgung();
   }

   private void listWohnungTilgung() {
      Wohnung[] woh = datenSpeicher.getAllWohnung();
      System.out.println("1--------------------------");
      for (int i = 0; i < woh.length; i++) {
         System.out.println("1.1--------------------------");
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
      System.out.println("tilgungsplanRechnen() --------------------------");
      Investition[] inv = datenSpeicher.getAllInvestition();
      for (int i = 0; i < inv.length; i++) {
         Investition investition = inv[i];
         Tilgung t = new Tilgung(investition,ZinsCase.zinsIst);
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