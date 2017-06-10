package ch.nolix.core.endPoint4;

import ch.nolix.core.communicationInterfaces.IReceiver;

class Receiver<M, R> implements IReceiver {

	final NetEndPoint<M, R> netEndPoint;
	
	public Receiver(NetEndPoint<M, R> netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	public void receive(String message) {
		netEndPoint.receive(message);
	}
}
