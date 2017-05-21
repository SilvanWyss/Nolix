//package declaration
package ch.nolix.core.endPoint3;

//own import
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IReplier;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public class EndPoint extends AbortableElement {

	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 5000;

	static final String DEFAULT_TARGET = "DefaultTarget";
	
	//attribute
	private final ch.nolix.core.endPoint2.EndPoint internalEndPoint;
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	private int nextSentPackageIndex = 1;
	
	//optional attribute
	private IReplier replier;
	
	//multiple attribute
	private final List<Package> receivedPackages = new List<Package>();
	
	public EndPoint(IEndPointTaker endPointTaker) {
		
		this(
			new ch.nolix.core.endPoint2.LocalEndPoint(
				new ch.nolix.core.endPoint2.IEndPointTaker() {
				
					public String getName() {
						return endPointTaker.getName();
					}
	
					public void takeEndPoint(ch.nolix.core.endPoint2.EndPoint endPoint) {
						endPointTaker.takeEndPoint(new EndPoint(endPoint));
					}
				}
			)
		);
	}
	
	public EndPoint(final int port) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(port));
	}
	
	//constructor
	public EndPoint(final String ip, final int port) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(ip, port));
	}
	
	//constructor
	public EndPoint(final String ip, final int port, final String target) {
		
		//Creates the internal end point of this end point.
		this(new ch.nolix.core.endPoint2.NetEndPoint(ip, port, target));
	}
	
	public EndPoint(ch.nolix.core.endPoint2.EndPoint internalEndPoint) {
		
		this.internalEndPoint = internalEndPoint;
		
		internalEndPoint.setReceiver(new Receiver(this));
	}

	public EndPoint(int port, String target) {
		
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
	 * @return the timeout of this zeta end point in milliseconds.
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
	public String sendMessageAndGetReply(final String message) {
		return sendMessageAndWaitToReply(message, true);
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
		return sendMessageAndWaitToReply(message, false);
	}
	
	//method
	/**
	 * Sets the replier of this end point.
	 * 
	 * @param replier
	 * @throws NullArgumentException if the given replier is null.
	 */
	public void setReplier(final IReplier replier) {
		
		//Checks if the given replier is not null.
		Validator.supposeThat(replier).thatIsInstanceOf(IReplier.class).isNotNull();
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	//method
	/**
	 * Sets the timeout of this zeta end point in milliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws NonPositiveArgumentException if the given timeout is not positive.
	 * @throws RuntimeException if this zeta end point is stopped.
	 */
	public void setTimeoutInMilliseconds(final int timeoutInMilliseconds) {
		
		//Checks if the given timeout is positive.
		Validator.supposeThat(timeoutInMilliseconds).thatIsNamed("timeout").isPositive();
	
		//Checks if this zeta end point is not stopped.
		throwExceptionIfAborted();

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
	 * @return the receiver of this zeta end point.
	 * @throws UnexistingAttributeException if this zeta end point has no receiver.
	 */
	private final IReplier getRefReplier() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away from the compiler or the JVM.
		while (!hasReplier()) {
			
			//The following statement that is actually unnecessary makes that the loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
				throw new UnexistingAttributeException(this, IReplier.class);
			}
		}
		
		return replier;
	}
	
	private boolean hasReplier() {
		return (replier != null);
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
	private String sendMessageAndWaitToReply(
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
