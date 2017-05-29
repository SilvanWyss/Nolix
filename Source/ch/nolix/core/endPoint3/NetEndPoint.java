//package declaration
package ch.nolix.core.endPoint3;

import ch.nolix.core.container.List;
//own imports
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.validator2.Validator;

//class
/**
 * a net end point can send messages to an other net end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public class NetEndPoint<M, R> extends EndPoint<M, R> {
	
	
	
	static final String DEFAULT_TARGET = "DefaultTarget";
	
	//default value
		public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 5000;
		private int nextSentPackageIndex = 1;
	
	//attributes
	private final ch.nolix.core.endPoint2.EndPoint<Package> internalEndPoint;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	
	//multiple attribute
	private final List<Package> receivedPackages = new List<Package>();
	
	public NetEndPoint(
		final int port,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.core.endPoint2.NetEndPoint<Package>(
				port, s -> Package.createZetaPackageFromString(s)
			),
			messageTransformer,
			replyTransformer
		);
	}
	
	//constructor
	public NetEndPoint(
		final String ip,
		final int port,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.core.endPoint2.NetEndPoint<Package>(
				ip, port, s -> Package.createZetaPackageFromString(s)
			),
			messageTransformer,
			replyTransformer
		);
	}
	
	//constructor
	public NetEndPoint(
		final String ip,
		final int port,
		final String target,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.core.endPoint2.NetEndPoint<Package>(
				ip, port, target, s -> Package.createZetaPackageFromString(s)
			),
			messageTransformer,
			replyTransformer
		);
	}
	
	NetEndPoint(
		final ch.nolix.core.endPoint2.EndPoint<Package> internalEndPoint,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer
	) {
		
		this.internalEndPoint = internalEndPoint;
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
		
		internalEndPoint.setReceiver(new Receiver<M, R>(this));
	}

	public NetEndPoint(int port, String target,
			final IElementTakerElementGetter<String, M> messageTransformer,
			final IElementTakerElementGetter<String, R> replyTransformer) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.core.endPoint2.NetEndPoint<Package>(
				port, target, s -> Package.createZetaPackageFromString(s)
			),
			messageTransformer,
			replyTransformer
		);
	}

	//method
	/**
	 * @return the target of this end point.
	 */
	public String getTarget() {
		return internalEndPoint.getTarget();
	}
	
	//method
	/**
	 * @return the timeout of this end point in milliseconds.
	 */
	public int getTimeoutInMilliseconds() {
		return timeoutInMilliseconds;
	}
	
	//method
	/**
	 * @return true if this end point has requested the connection.
	 */
	public boolean hasRequestedConnection() {
		return internalEndPoint.hasRequestedConnection();
	}
	
	//method
	/**
	 * @return true if this end point is a local end point.
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
	 * Sends the given message and returns the reply.
	 * This method throws an exception if no reply is received within the timeout.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 * @throws RuntimeException if this zeta end point is stopped.
	 * @throws RuntimeException if an error occurs by trying to send the message.
	 */
	public R sendAndGetReply(final M message) {
		return sendAndWaitToReply(message, true);
	}
	
	//method
	/**
	 * Sends the given message and returns the reply.
	 * This method waits to the reply without checking a timeout.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 * @throws RuntimeException if this zeta end point is stopped.
	 * @throws RuntimeException if an error occurs by trying to send the message.
	 */	
	public R sendMessageAndWaitToReply(final M message) {
		return sendAndWaitToReply(message, false);
	}
	
	//method
	/**
	 * Sets the timeout of this end point in milliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws NonPositiveArgumentException if the given timeout is not positive.
	 * @throws RuntimeException if this end point is aborted.
	 */
	public void setTimeoutInMilliseconds(final int timeoutInMilliseconds) {
		
		//Checks if the given timeout is positive.
		Validator.supposeThat(timeoutInMilliseconds).thatIsNamed("timeout").isPositive();
	
		//Checks if this end point is not aborted.
		throwExceptionIfAborted();

		//Sets the timeout of this end point in milliseconds.
		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}
	
	ch.nolix.core.endPoint2.EndPoint<Package> getRefInternalEndPoint() {
		return internalEndPoint;
	}
	
	//package-visible method
	/**
	 * Lets this zeta end point receive the given message.
	 * 
	 * @param message
	 */
	void receive(final String message) {
		receive(Package.createZetaPackageFromString(message));
	}
	
	//method
	/**
	 * Lets this zeta end point wait to and return and remove the received package with the given index.
	 * 
	 * @param index
	 * @param timeoutCheck
	 * @return the received package with the given index.
	 * @throws RuntimeException if this zeta end point reaches its timeout before it receives a package with the given index.
	 */
	Package waitToAndGetAndRemoveReceivedPackage(
		final int index,
		final boolean timeoutCheck
	) {
		
		if (!timeoutCheck) {
			while (!receivedPackage(index));
		}
		else {
			
			final long startTimeInMilliseconds = System.currentTimeMillis();
			
			while (!receivedPackage(index)) {
				if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
					throw new RuntimeException("Zeta end point reached timeout while waiting to the package with the index " + index + ".");
				}
			}
		}
		
		return getAndRemoveReceivedPackage(index);
	}
	
	final List<Package> getRefReceivedPackages() {
		return receivedPackages;
	}
	
	//method
	/**
	 * Lets this zeta end point return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the reply with the given index
	 * @throws InvalidArgumentException if this zeta end point has not received a package with the given index.
	 */
	private final Package getAndRemoveReceivedPackage(final int index) {
		return getRefReceivedPackages().removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @return the index of the next sent package. of this zeta end point
	 */
	final int getNextSentPackageIndex() {
		
		//Resets the index of the text sent package if it has reached the maximum value.
		if (nextSentPackageIndex == Integer.MAX_VALUE) {
			nextSentPackageIndex = 0;
		}
		
		//Returns and increments the next sent package index.
		return nextSentPackageIndex++;
	}
	
	//method
	/**
	 * @param index
	 * @return true if this zeta end point has received a package with the given index.
	 */
	private final boolean receivedPackage(final int index) {
		return getRefReceivedPackages().contains(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * Lets this zeta end point receive the given package.
	 * 
	 * @param package_
	 */
	void receive(final Package package_) {
		
		//Enumerates the message role of the given package.
		switch (package_.getMessageRole()) {
			case RESPONSE_EXPECTING_MESSAGE:
				
				try {
					final R reply = getRefReplier().getReply(messageTransformer.getOutput(package_.getRefContext()));
					send(new Package(package_.getIndex(), MessageRole.SUCCESS_RESPONSE, reply.toString()));
				}
				catch (final Exception exception) {
					String responseMessage = exception.getMessage();
					send(new Package(package_.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				getRefReceivedPackages().addAtEnd(package_);
		}
	}
	

	
	//method
	/**
	 * Lets this end point send the given package.
	 * 
	 * @param package_
	 */
	private void send(final Package package_) {
		internalEndPoint.send(package_);
	}
	
	//method
	/**
	 * Sends the given message and waits to the reply.
	 * 
	 * @param message
	 * @param timeoutCheck
	 * @return the reply to the given message.
	 */
	private R sendAndWaitToReply(
		final M message,
		final boolean timeoutCheck
	) {
		//Sends message nd receives reply.
		final int index = getNextSentPackageIndex();
		send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message.toString()));		
		final Package response = waitToAndGetAndRemoveReceivedPackage(index, timeoutCheck);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return replyTransformer.getOutput(response.getRefContext());
			case ERROR_RESPONSE:
				throw new RuntimeException(response.getRefContext());
			default:
				throw new RuntimeException("An error occured.");
		}
	}
}
