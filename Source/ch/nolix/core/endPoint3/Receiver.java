package ch.nolix.core.endPoint3;

import ch.nolix.core.communicationInterfaces.IReceiver;

class Receiver implements IReceiver {

	final NetEndPoint netEndPoint;
	
	public Receiver(NetEndPoint netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	public void receive(String message) {
		netEndPoint.receive(message);
		
	}
}
