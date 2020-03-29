package ch.nolix.common.endPoint4;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;

final class EndPointTaker<M, R> implements ch.nolix.common.endPoint2.IEndPointTaker {
	
	private final IEndPointTaker<M, R> parentEndPointTaker;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	
	public EndPointTaker(IEndPointTaker<M, R> endPointTaker,
			IElementTakerElementGetter<String, M> messageTransformer,
			IElementTakerElementGetter<String, R> replyTransformer) {
		this.parentEndPointTaker = endPointTaker;
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
	}
	
	@Override
	public String getName() {
		return parentEndPointTaker.getName();
	}

	@Override
	public void takeEndPoint(ch.nolix.common.endPoint2.EndPoint endPoint) {
		parentEndPointTaker.takeEndPoint(
			new NetEndPoint<M, R>(
				endPoint,			
				messageTransformer,
				replyTransformer
			)
		);
	}
}
