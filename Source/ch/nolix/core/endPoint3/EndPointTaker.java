package ch.nolix.core.endPoint3;

final class EndPointTaker implements ch.nolix.core.endPoint2.IEndPointTaker {

	private final NetServer netServer;
	
	public EndPointTaker(final NetServer netServer) {
		this.netServer = netServer;
	}
	
	public String getName() {
		return null;
	}

	public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
		netServer.takeEndPoint(
			new NetEndPoint(
				endPoint
			)
		);
	}
}
