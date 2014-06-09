package pl.edu.wat.msk.smo_generic;

import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimManager;

/**
 * Statystyki całego systemy
 * @since 07.06.2014
 * @author mariusz
 *
 */
public class SimSystem {
	private static SimSystem instance;
	
	private int notificationCount;
	private int successCount;
	private int failureCount;
	
	public MonitoredVar mv_serviceTime;
	public MonitoredVar mv_notificationCount;
	
	private SimSystem() {
		notificationCount = 0;
		successCount = 0;
		failureCount = 0;
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
	
	public void success(ZgloszenieGeneric z) {
		successCount++;
		
		utilization(z);
	}
	
	public void failure(ZgloszenieGeneric z) {
		failureCount++;
		
		utilization(z);
	}
	
	private void utilization(ZgloszenieGeneric z) {
		z.destroy();
		
		double serviceTime = SimManager.getInstance().simTime() - z.getCzasOdniesienia();
		mv_serviceTime.setValue(serviceTime);
	}

	public int getSuccessCount() {
		return successCount;
	}

	public int getFailureCount() {
		return failureCount;
	}
	
}
