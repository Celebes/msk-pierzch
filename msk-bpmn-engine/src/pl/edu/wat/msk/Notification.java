package pl.edu.wat.msk;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** 
 *  Zg?oszenie generowane przez ?r?d?o i obs?ugiwane przez kolejne komponenty modelu
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public class Notification {

  private double id;

  /** 
   *  Ostatni przydzielony identyfikator
   */
  private static int LAST_ID = 0;

  public final double startTime;

  private Integer refereceTime;

  public int getId() {
	  return 0;
  }

  public Notification(double startTime) {
	  throw new NotImplementedException();
  }

  /** 
   *  Zwraca ostatni przydzielony identyfikator do zg?oszenia
   */
  public static int getLastId() {
	  throw new NotImplementedException();
  }

  /** 
   *  Inkrementacja LAST_ID i zwr?cenie tej warto?ci
   */
  public static int getNextId() {
	  return 0;
  }

  public double getReferenceTime() {
	  return 0.0;
  }

  public void setReferenceTime(double referenceTime) {
	  
  }

}