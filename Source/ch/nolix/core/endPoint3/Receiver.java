package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.communicationAPI.IReceiver;

class Receiver implements IReceiver {

	final NetEndPoint netEndPoint;
	
	public Receiver(NetEndPoint netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	@Override
	public void receive(String message) {
		netEndPoint.receive(message);
		
	}
}
