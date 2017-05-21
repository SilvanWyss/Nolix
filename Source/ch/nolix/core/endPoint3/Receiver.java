package ch.nolix.core.endPoint3;

import ch.nolix.core.interfaces.IReceiver;

public class Receiver implements IReceiver {

	final EndPoint endPoint;
	
	public Receiver(EndPoint endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void receive(String message) {
		endPoint.receive(message);
		
	}
}
