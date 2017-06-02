package ch.nolix.core.endPoint3;

final class EndPointTaker implements ch.nolix.core.endPoint2.IEndPointTaker {

	private final IEndPointTaker endPointTaker;
	
	public EndPointTaker(IEndPointTaker endPointTaker) {
		this.endPointTaker = endPointTaker;
	}
	
	public String getName() {
		return endPointTaker.getName();
	}

	public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
		endPointTaker.takeEndPoint(
			new NetEndPoint(
				endPoint
			)
		);
	}
}
