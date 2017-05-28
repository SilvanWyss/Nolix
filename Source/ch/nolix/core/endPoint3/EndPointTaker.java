package ch.nolix.core.endPoint3;

import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;

final class EndPointTaker<M, R> implements ch.nolix.core.endPoint2.IEndPointTaker<Package> {

	private final IEndPointTaker<M, R> endPointTaker;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	
	public EndPointTaker(IEndPointTaker<M, R> endPointTaker,
			IElementTakerElementGetter<String, M> messageTransformer,
	final IElementTakerElementGetter<String, R> replyTransformer) {
		
		this.endPointTaker = endPointTaker;
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
		
		System.out.println(endPointTaker.getName());
	}
	
	public String getName() {
		return endPointTaker.getName();
	}

	public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint<Package> endPoint) {
		endPointTaker.takeEndPoint(
			new NetEndPoint<M, R>(
				endPoint,
				messageTransformer,
				replyTransformer
			)
		);
	}
}
