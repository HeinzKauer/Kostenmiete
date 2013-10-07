package ch.kausoft.wbg.daten.dao;

import java.util.HashMap;

import ch.kausoft.wbg.daten.daoHelp.Daten;

public class Kapital extends Daten {

   HashMap<Integer, Zinssatz> zins    = new HashMap<Integer, Zinssatz>();

   public Kapital(String bezeichnung) {
      this(bezeichnung,"" );
   }

   public Kapital(String bezeichnung, String beschreibung) {
      super(bezeichnung, beschreibung);
      addKapitalZins(new Zinssatz(2000, .0d, 3.0d, 3.0d, 6.0d));
   }

   public Zinssatz getKapitalZins(int jahr) {
      for (int i = jahr; i >= 2000; i--) {
         Zinssatz x = zins.get(i);
         if (x != null) {
            return x;
         }
      }
      return null;
   }


   public void addKapitalZins(Zinssatz kapitalZins) {
      zins.put(kapitalZins.abJahr, kapitalZins);
   }



}
