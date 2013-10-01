package ch.kausoft.wbg.daten;

public class Zinssatz {

   /**
    * Dieser Zins und Amortisationssatz ist gültig ab diesem Jahr
    */
   int abJahr;

   /**
    * Wir haben vier verschiedene Zinssätze
    */
   double zins[] = new double[4];


   /**
    * 
    * @param abJahr
    * @param zinsOptimistisch
    * @param zinsPessimistisch
    * @param zinsIst
    * @param zinsRendite
    */
   public Zinssatz(int abJahr, double zinsOptimistisch,
         double zinsPessimistisch, double zinsIst, double zinsRendite) {

      this.abJahr = abJahr;
      this.zins[0] = zinsOptimistisch;
      this.zins[1] = zinsPessimistisch;
      this.zins[2] = zinsIst;
      this.zins[3] = zinsRendite;

   }

   public static Zinssatz getInstance(String abJahr, String zinsOptimistisch,
         String zinsPessimistisch, String zinsIst, String zinsRendite) {
      try {
      return new Zinssatz(Integer.parseInt(abJahr), //
            Double.parseDouble(zinsOptimistisch), //
            Double.parseDouble(zinsPessimistisch), //
            Double.parseDouble(zinsIst), //
            Double.parseDouble(zinsRendite));
      } catch (Exception e) {
         System.out.println("130802-0015 Daten für Zinssatz fehlerhaft");
         return new Zinssatz(1000,0d,0d,0d,0d);
      }
      
   }
}
