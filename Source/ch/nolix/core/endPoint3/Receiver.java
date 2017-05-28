package ch.nolix.core.endPoint3;

import ch.nolix.core.communicationInterfaces.IReceiver;

public class Receiver implements IReceiver {

	final NetEndPoint netEndPoint;
	
	public Receiver(NetEndPoint netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	@Override
	public void receive(String message) {
		netEndPoint.receive(message);
		
	}
}
