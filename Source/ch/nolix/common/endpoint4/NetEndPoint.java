//package declaration
package ch.nolix.common.endpoint4;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link NedEndPoint} can send messages to another {@link NedEndPoint}.
 * 
 * @author Silvan Wyss
 * @date 2017-06-10
 * @lines 350
 */
public class NetEndPoint<M, R> extends EndPoint<M, R> {
		
	//attributes
	private final ch.nolix.common.endpoint2.EndPoint internalEndPoint;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	private int nextSentPackageIndex = 1;
	
	//multi-attribute
	private final LinkedList<Package> receivedPackages = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the default target on the given port on the local machine.
	 * The {@link NetEndPoint} will have the  given messageTransformer and replyTransformer.
	 * 
	 * @param port
	 * @param messageTransformer
	 * @param replyTransformer
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given messageTransformer is null.
	 * @throws ArgumentIsNullException if the given replyTransformer is null.
	 */
	public NetEndPoint(
		final int port,
		IElementTakerElementGetter<String, M> messageTransformer,
		IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endpoint2.NetEndPoint(port), messageTransformer, replyTransformer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the given target on the given port on the local machine.
	 * The {@link NetEndPoint} will have the  given messageTransformer and replyTransformer.
	 * 
	 * @param port
	 * @param target
	 * @param messageTransformer
	 * @param replyTransformer
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsOutOfRangeException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 * @throws ArgumentIsNullException if the given messageTransformer is null.
	 * @throws ArgumentIsNullException if the given replyTransformer is null.
	 */
	public NetEndPoint(int port, String target,
		IElementTakerElementGetter<String, M> messageTransformer,
		IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endpoint2.NetEndPoint(port, target), messageTransformer, replyTransformer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the default target on the given port on the machine with the given ip.
	 * The {@link NetEndPoint} will have the  given messageTransformer and replyTransformer.
	 * 
	 * @param ip
	 * @param port
	 * @param messageTransformer
	 * @param replyTransformer
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given messageTransformer is null.
	 * @throws ArgumentIsNullException if the given replyTransformer is null.
	 */
	public NetEndPoint(
		final String ip,
		final int port,
		IElementTakerElementGetter<String, M> messageTransformer,
		IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endpoint2.NetEndPoint(ip, port), messageTransformer, replyTransformer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the given target on the given port on the machine with the given ip.
	 * The {@link NetEndPoint} will have the  given messageTransformer and replyTransformer.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @param messageTransformer
	 * @param replyTransformer
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsOutOfRangeException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 * @throws ArgumentIsNullException if the given messageTransformer is null.
	 * @throws ArgumentIsNullException if the given replyTransformer is null.
	 */
	public NetEndPoint(
		final String ip,
		final int port,
		final String target,
		IElementTakerElementGetter<String, M> messageTransformer,
		IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endpoint2.NetEndPoint(ip, port, target), messageTransformer, replyTransformer);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given internalEndPoint.
	 * The {@link NetEndPoint} will have the  given messageTransformer and replyTransformer.
	 * 
	 * @param internalEndPoint
	 * @param messageTransformer
	 * @param replyTransformer
	 * @throws ArgumentIsNullException if the given internalEndPoint is null.
	 * @throws ArgumentIsNullException if the given messageTransformer is null.
	 * @throws ArgumentIsNullException if the given replyTransformer is null.
	 */
	NetEndPoint(
		final ch.nolix.common.endpoint2.EndPoint internalEndPoint,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		Validator.assertThat(internalEndPoint).thatIsNamed("internal end point").isNotNull();
		Validator.assertThat(messageTransformer).thatIsNamed("messageTransformer").isNotNull();
		Validator.assertThat(replyTransformer).thatIsNamed("reply transformer").isNotNull();
		
		this.internalEndPoint = internalEndPoint;
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
		
		internalEndPoint.setReceiver(this::receive);
		createCloseDependencyTo(internalEndPoint);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTarget() {
		return internalEndPoint.getTarget();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRequestedConnection() {
		return internalEndPoint.hasRequestedConnection();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasTarget() {
		return internalEndPoint.hasTarget();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean isLocalEndPoint() {
		return internalEndPoint.isLocalEndPoint();
	}
	
	//method
	/**
	 * @return true if this end point is a net end point.
	 */
	public boolean isNetEndPoint() {
		return internalEndPoint.isNetEndPoint();
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given message and returns the reply.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 */
	@Override
	public R sendAndGetReply(final M message) {
		return sendAndWaitToReply(message);
	}
	
	//method
	/**
	 * @return the internal end point of the current {@link NetEndPoint}.
	 */
	ch.nolix.common.endpoint2.EndPoint getRefInternalEndPoint() {
		return internalEndPoint;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given message.
	 * 
	 * @param message
	 */
	void receive(final String message) {
		receive(Package.createZetaPackageFromString(message));
	}
	
	//method
	/**
	 * @return the index of the next sent package. of the current {@link NetEndPoint}
	 */
	private int getNextSentPackageIndex() {
		
		//Resets the index of the text sent package if it has reached the maximum value.
		if (nextSentPackageIndex == Integer.MAX_VALUE) {
			nextSentPackageIndex = 0;
		}
		
		//Returns and increments the next sent package index.
		return nextSentPackageIndex++;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the reply with the given index
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} has not received a package with the given index.
	 */
	private final Package getAndRemoveReceivedPackage(final int index) {
		return receivedPackages.removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @return the received packages of the current {@link NetEndPoint}.
	 */
	private IContainer<Package> getRefReceivedPackages() {
		return receivedPackages;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given pPackage.
	 * 
	 * @param pPackage
	 */
	private void receive(final Package pPackage) {
		
		//Enumerates the message role of the given package.
		switch (pPackage.getMessageRole()) {
			case RESPONSE_EXPECTING_MESSAGE:
				
				try {
					final R reply = getRefReplier().getReply(messageTransformer.getOutput(pPackage.getRefContent()));
					send(new Package(pPackage.getIndex(), MessageRole.SUCCESS_RESPONSE, reply.toString()));
				}
				catch (final Exception exception) {
					String responseMessage = exception.getMessage();
					send(new Package(pPackage.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				receivedPackages.addAtEnd(pPackage);
		}
	}

	//method
	/**
	 * @param index
	 * @return true if the current {@link NetEndPoint} has received a package with the given index.
	 */
	private final boolean receivedPackage(final int index) {
		return getRefReceivedPackages().contains(rp -> rp.hasIndex(index));
	}

	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given package.
	 * 
	 * @param pPackage
	 */
	private void send(final Package pPackage) {
		internalEndPoint.send(pPackage.toString());
	}
	
	//method
	/**
	 * Les the current {@link NetEndPoint} send the given message and wait to the reply.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	private R sendAndWaitToReply(final M message) {
		
		//Sends message and receives the reply.
		final int index = getNextSentPackageIndex();
		send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message.toString()));
		final Package response = waitToAndGetAndRemoveReceivedPackage(index);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return replyTransformer.getOutput(response.getRefContent());
			case ERROR_RESPONSE:
				throw new RuntimeException(response.getRefContent());
			default:
				throw new RuntimeException("An error occured.");
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} wait to and return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the received package with the given index.
	 * @throws Exception
	 * if the current {@link NetEndPoint} reaches its timeout before it receives a package with the given index.
	 */
	private Package waitToAndGetAndRemoveReceivedPackage(final int index) {
		
		while (!receivedPackage(index));
	
		return getAndRemoveReceivedPackage(index);
	}
}
