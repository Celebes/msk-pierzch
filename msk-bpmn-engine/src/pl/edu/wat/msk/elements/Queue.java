package pl.edu.wat.msk.elements;

import java.util.ArrayList;
import java.util.Vector;

import dissimlab.monitors.MonitoredVar;
import pl.edu.wat.msk.Notification;

/** 
 *  Kolejka zg?osze?
 * 
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public class Queue extends HavePrevNext implements IHaveNext {

	  private int size;
	
	  private java.util.Vector<Notification> notifications;
	
	  /** 
	   *  Zminenna monitora czasu oczekiwania w kolejce
	   */
	  public MonitoredVar waitTimeMV;
	
	  /** 
	   *  Zmienna monmitora d?ugo?ci kolejki
	   */
	  public MonitoredVar queueLengthMV;
	
	  public Queue(int size) {
		  this.size = size;
		  notifications = new Vector<Notification>(size);
		  waitTimeMV = new MonitoredVar();
		  queueLengthMV = new MonitoredVar();
	  }

	  /** 
	   *  Dodanie zg?oszenia do kolejki
	   */
	  public void add(Notification notification) {
		  notifications.add(notification);
		  queueLengthMV.setValue(notifications.size());
		  
	  }
	
	  /** 
	   *  Usuni?cie zg?oszenia z kolejki
	   */
	  public Notification delete() {
		  Notification notification = notifications.remove(0);
		  queueLengthMV.setValue(notifications.size());
		  return notification;
	  }
	
	  /** 
	   *  Usuni?cie konkretnego zg?oszenia
	   */
	  public void delete(Notification notification) {
		  notifications.remove(notification);
		  queueLengthMV.setValue(notifications.size());
	  }
	
	  /** 
	   *  Liczba zg?osze? w kolejce
	   */
	  public int getCount() {
		  return notifications.size();
	  }
	
	  public int getSize() {
		  return size;
	  }
	
	@Override
	public Vector validate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void processing(Notification notification) {
		// TODO Auto-generated method stub
		
	}

}