package ch.nolix.core.endPoint4;

final class EndPointTaker<M, R> implements ch.nolix.core.endPoint2.IEndPointTaker {

	private final IEndPointTaker<M, R> endPointTaker;
	
	public EndPointTaker(IEndPointTaker<M, R> endPointTaker) {
		this.endPointTaker = endPointTaker;
	}
	
	public String getName() {
		return endPointTaker.getName();
	}

	public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
		endPointTaker.takeEndPoint(
			new NetEndPoint<M, R>(
				endPoint
			)
		);
	}
}
