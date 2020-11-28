package ch.nolix.common.endpoint4;

import ch.nolix.common.communicationapi.IReceiver;

class Receiver<M, R> implements IReceiver {

	final NetEndPoint<M, R> netEndPoint;
	
	public Receiver(NetEndPoint<M, R> netEndPoint) {
		this.netEndPoint = netEndPoint;
	}

	@Override
	public void receive(String message) {
		netEndPoint.receive(message);
	}
}
