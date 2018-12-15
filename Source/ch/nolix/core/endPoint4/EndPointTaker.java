package ch.nolix.core.endPoint4;

//own imports
import ch.nolix.core.functionAPI.IElementTakerElementGetter;

final class EndPointTaker<M, R> implements ch.nolix.core.endPoint2.IEndPointTaker {

	private final IEndPointTaker<M, R> endPointTaker;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	
	public EndPointTaker(IEndPointTaker<M, R> endPointTaker,
			IElementTakerElementGetter<String, M> messageTransformer,
			IElementTakerElementGetter<String, R> replyTransformer) {
		this.endPointTaker = endPointTaker;
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
	}
	
	@Override
	public String getName() {
		return endPointTaker.getName();
	}

	@Override
	public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
		endPointTaker.takeEndPoint(
			new NetEndPoint<M, R>(
				endPoint,			
				messageTransformer,
				replyTransformer
			)
		);
	}
}
