package ch.kausoft.wbg.daten;

/**
 * für die Tilgung einer Investition kann ein jährlicher Mietzinsbeitrag
 * verwendet werden. Von diesem Beitrag werden die Zinskosten abgezogen und der
 * Rest wird zur Schuldamortisation verwendet.
 * 
 * Jeder Infestition hat pro jahr die Mietzinsbeitrag definiert
 * 
 * @author Heinz
 * 
 */
public class Mietzinsbeitrag {

   /**
    * Investition für diese diesen Mietzinsbeitrag gültig ist
    */
   public Investition invest;

   /**
    * Dieser Zins und Amortisationssatz ist gültig ab diesem Jahr
    */
   int abJahr;

   /**
    * Ab dem definierten Jahr wird jährlich dieser Betrag verwendet um die
    * auflauffenden Zinsen und die Amortisation zu finanzieren.
    */
   double jaehrlicherMietzinsbeitrag;

   public Mietzinsbeitrag(int abJahr, double jaehrlicherMietzinsbeitrag) {
      this.abJahr = abJahr;
      this.jaehrlicherMietzinsbeitrag = jaehrlicherMietzinsbeitrag;
   }

}
