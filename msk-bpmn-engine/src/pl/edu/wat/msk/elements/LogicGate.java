package pl.edu.wat.msk.elements;

import java.util.Vector;

import pl.edu.wat.msk.Notification;

/** 
 *  Je?li:
 *  AND - zg?oszenie przechodzi tylko gdy dojd? z innych wej?? (razem z nimi)
 *  OR - przechdzi je?li przejdzie jak tylko dotrze
 *  XOR - przechodzi tylko jedno zg?oszenie kt?re dosz?o
 *  
 *  Przemy?le? jeszcze jak ogarn?? kilka wej?? i wyj??
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public class LogicGate extends HavePrevNext {

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