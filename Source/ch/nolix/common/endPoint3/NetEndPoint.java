//package declaration
package ch.nolix.common.endPoint3;

import ch.nolix.common.containers.LinkedList;

//class
/**
 * a net end point can send messages to an other net end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public class NetEndPoint extends EndPoint {
	
	//default value
	private int nextSentPackageIndex = 1;
	
	//attributes
	private final ch.nolix.common.endPoint2.EndPoint internalEndPoint;
	
	//multi-attribute
	private final LinkedList<Package> receivedPackages = new LinkedList<>();
	
	public NetEndPoint(
		final int port
	) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.common.endPoint2.NetEndPoint(port)
		);
	}
	
	//constructor
	public NetEndPoint(
		final String ip,
		final int port
	) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.common.endPoint2.NetEndPoint(
				ip, port
			)
		);
	}
	
	//constructor
	public NetEndPoint(
		final String ip,
		final int port,
		final String target) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.common.endPoint2.NetEndPoint(
				ip, port, target
			)
		);
	}
	
	NetEndPoint(
		final ch.nolix.common.endPoint2.EndPoint internalEndPoint
	) {
		
		this.internalEndPoint = internalEndPoint;
		
		internalEndPoint.setReceiver(new Receiver(this));
		
		createCloseDependency(internalEndPoint);
	}

	public NetEndPoint(int port, String target) {
		
		//Creates the internal end point of this end point.
		this(
			new ch.nolix.common.endPoint2.NetEndPoint(port, target)
		);
	}

	//method
	/**
	 * @return the target of this end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this net end point does not have a target.
	 */
	@Override
	public String getTarget() {
		return internalEndPoint.getTarget();
	}
	
	//method
	/**
	 * @return true if this end point has requested the connection.
	 */
	@Override
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
	 * This method throws an exception if there is not received a reply within the timeout.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 * @throws RuntimeException if this zeta end point is stopped.
	 * @throws RuntimeException if an error occurs by trying to send the message.
	 */
	@Override
	public String sendAndGetReply(final String message) {
		return sendAndWaitToReply(message);
	}
	
	ch.nolix.common.endPoint2.EndPoint getRefInternalEndPoint() {
		return internalEndPoint;
	}
	
	//method
	/**
	 * Lets this zeta end point receive the given message.
	 * 
	 * @param message
	 */
	void receive(final String message) {
		receive(Package.createPackageFromString(message));
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
		final int index
	) {
		
		while (!receivedPackage(index)) {
			supposeIsAlive();
		}

		return getAndRemoveReceivedPackage(index);
	}
	
	final LinkedList<Package> getRefReceivedPackages() {
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
					final String reply = getRefReplier().getReply(package_.getRefContent());
					send(new Package(package_.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
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
	private String sendAndWaitToReply(final String message) {
		
		//Sends message and receives reply.
		final var index = getNextSentPackageIndex();
		send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message));
		final Package response = waitToAndGetAndRemoveReceivedPackage(index);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return response.getRefContent();
			case ERROR_RESPONSE:
				throw new RuntimeException(response.getRefContent());
			default:
				throw new RuntimeException("An error occured.");
		}
	}

	@Override
	public boolean hasTarget() {
		return internalEndPoint.hasTarget();
	}
}
