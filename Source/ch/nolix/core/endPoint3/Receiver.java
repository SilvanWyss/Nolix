package ch.nolix.core.endPoint3;

import ch.nolix.core.communicationInterfaces.IGenericReceiver;

class Receiver<M, R> implements IGenericReceiver<Package> {

	final NetEndPoint<M, R> netEndPoint;
	
	public Receiver(NetEndPoint<M, R> netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	public void receive(Package message) {
		netEndPoint.receive(message);
		
	}
}
