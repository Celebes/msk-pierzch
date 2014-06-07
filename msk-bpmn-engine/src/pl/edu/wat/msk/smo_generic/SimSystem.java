package pl.edu.wat.msk.smo_generic;

import dissimlab.monitors.MonitoredVar;

/**
 * Statystyki całego systemy
 * @since 07.06.2014
 * @author mariusz
 *
 */
public class SimSystem {
	private static SimSystem instance;
	
	private int notificationCount;
	
	public MonitoredVar mv_serviceTime;
	public MonitoredVar mv_notificationCount;
	
	private SimSystem() {
		notificationCount = 0;
		mv_serviceTime = new MonitoredVar();
		mv_notificationCount = new MonitoredVar();
	}

	public static SimSystem getInstance() {
		if(instance == null) {
			instance = new SimSystem();
		}
		
		return instance;
	}
	
	public int getNotificationCount() {
		return notificationCount;
	}
	
	public void incrementNotificationCount() {
		notificationCount++;
		mv_notificationCount.setValue(notificationCount);
	}
	
	public void decrementNotificationCount() {
		notificationCount--;
		mv_notificationCount.setValue(notificationCount);
	}
}
