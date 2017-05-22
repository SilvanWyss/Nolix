//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.validator2.Validator;

//class
/**
 * a net end point can send messages to an other net end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class NetEndPoint extends EndPoint {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 5000;
	
	static final String DEFAULT_TARGET = "DefaultTarget";
	
	//attribute
	private final ch.nolix.core.endPoint2.EndPoint internalEndPoint;
	private int nextSentPackageIndex = 1;
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	
	//multiple attribute
	private final List<Package> receivedPackages = new List<Package>();
	
	public NetEndPoint(IEndPointTaker endPointTaker) {
		
		this(
			new ch.nolix.core.endPoint2.LocalEndPoint(
				new ch.nolix.core.endPoint2.IEndPointTaker() {
				
					public String getName() {
						return endPointTaker.getName();
					}
	
					public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
						endPointTaker.takeEndPoint(new NetEndPoint(endPoint));
					}
				}
			)
		);
	}
	
	public NetEndPoint(final int port) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(port));
	}
	
	//constructor
	public NetEndPoint(final String ip, final int port) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(ip, port));
	}
	
	//constructor
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(ip, port, target));
	}
	
	public NetEndPoint(ch.nolix.core.endPoint2.EndPoint internalEndPoint) {
		
		this.internalEndPoint = internalEndPoint;
		
		internalEndPoint.setReceiver(new Receiver(this));
	}

	public NetEndPoint(int port, String target) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(port, target));
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
	public String sendAndGetReply(final String message) {
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
	public String sendMessageAndWaitToReply(final String message) {
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
	
	ch.nolix.core.endPoint2.EndPoint getRefInternalEndPoint() {
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
	 * Lets this zeta end point return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the reply with the given index
	 * @throws InvalidArgumentException if this zeta end point has not received a package with the given index.
	 */
	private final Package getAndRemoveReceivedPackage(final int index) {
		return receivedPackages.removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @return the index of the next sent package. of this zeta end point
	 */
	private final int getNextSentPackageIndex() {
		
		//Resets the index of the text sent package if it has reached the maximum value.
		if (nextSentPackageIndex == Integer.MAX_VALUE) {
			nextSentPackageIndex = 0;
		}
		
		//Returns and increments the next sent package index.
		return nextSentPackageIndex++;
	}
	
	//method
	/**
	 * Lets this zeta end point receive the given package.
	 * 
	 * @param package_
	 */
	private void receive(final Package package_) {
		
		//Enumerates the message role of the given package.
		switch (package_.getMessageRole()) {
			case RESPONSE_EXPECTING_MESSAGE:
				
				try {
					final String reply = getRefReplier().receiveMessageAndGetReply(package_.getMessage());
					send(new Package(package_.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
				}
				catch (final Exception exception) {
					String responseMessage = exception.getMessage();
					send(new Package(package_.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				receivedPackages.addAtEnd(package_);
		}
	}
	
	//method
	/**
	 * @param index
	 * @return true if this zeta end point has received a package with the given index.
	 */
	private final boolean receivedPackage(final int index) {
		return receivedPackages.contains(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * Lets this end point send the given package.
	 * 
	 * @param package_
	 */
	private void send(final Package package_) {
		internalEndPoint.send(package_.toString());
	}
	
	//method
	/**
	 * Sends the given message and waits to the reply.
	 * 
	 * @param message
	 * @param timeoutCheck
	 * @return the reply to the given message.
	 */
	private String sendAndWaitToReply(
		final String message,
		final boolean timeoutCheck
	) {
		//Sends message nd receives reply.
		final int index = getNextSentPackageIndex();
		send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message));		
		final Package response = waitToAndGetAndRemoveReceivedPackage(index, timeoutCheck);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return response.getMessage();
			case ERROR_RESPONSE:
				throw new RuntimeException(response.getMessage());
			default:
				throw new RuntimeException("An error occured.");
		}
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
	private Package waitToAndGetAndRemoveReceivedPackage(
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
}
