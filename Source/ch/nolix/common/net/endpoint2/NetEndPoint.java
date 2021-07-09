//package declaration
package ch.nolix.common.net.endpoint2;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.exception.GeneralException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;
import ch.nolix.system.client.base.Server;

//class
/**
 * A {@link NetEndPoint} is a {@link EndPoint} that can send messages to an other {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @date 2017-05-22
 * @lines 350
 */
public class NetEndPoint extends EndPoint {
	
	//constant
	private int nextSentPackageIndex = 1;
	
	//attributes
	private final ch.nolix.common.net.endpoint.EndPoint internalEndPoint;
	
	//multi-attribute
	private final LinkedList<Package> receivedPackages = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect
	 * to the default target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint.NetEndPoint(port));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final int port, final String target) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint.NetEndPoint(port, target));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the default target on the {@link Server#DEFAULT_PORT} on the machine with the given ip.
	 * 
	 * @param ip
	 */
	public NetEndPoint(final String ip) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint.NetEndPoint(ip));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect
	 * to the default target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip,	final int port) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint.NetEndPoint(ip, port));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect
	 * to the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final String ip,	final int port,	final String target) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint.NetEndPoint(ip, port, target));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given internalEndPoint.
	 * 
	 * @param internalEndPoint
	 * @throws ArgumentIsNullException if the given internalEndPoint is null.
	 */
	NetEndPoint(final ch.nolix.common.net.endpoint.EndPoint internalEndPoint) {
		
		Validator.assertThat(internalEndPoint).thatIsNamed("internal EndPoint").isNotNull();
		
		this.internalEndPoint = internalEndPoint;
		createCloseDependencyTo(internalEndPoint);
		internalEndPoint.setReceiver(this::receive);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void createCloseDependencyTo(final ICloseableElement element) {
		
		//This implementation just ensures that it cannot be overwritten.
		super.createCloseDependencyTo(element);
	}
	
	//method
	/**
	 * @return the target of the current {@link NetEndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if this net end point does not have a target.
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
	 * @return true if the current {@link NetEndPoint} is a local end point.
	 */
	public boolean isLocalEndPoint() {
		return internalEndPoint.isLocalEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} is a net end point.
	 */
	public boolean isNetEndPoint() {
		return internalEndPoint.isNetEndPoint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWebEndPoint() {
		return internalEndPoint.isWebEndPoint();
	}
	
	//method
	/**
	 * Sends the given message and returns the reply.
	 * 
	 * @param message
	 * @return the reply to the given message if the current {@link NetEndPoint} stays connected, null otherwise.
	 */
	public String getReplyTo(final String message) {
		return sendAndWaitToReply(message);
	}
	
	//method
	/**
	 * @return the internal end point of the current {@link NetEndPoint}.
	 */
	ch.nolix.common.net.endpoint.EndPoint getRefInternalEndPoint() {
		return internalEndPoint;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given message.
	 * 
	 * @param message
	 */
	void receive(final String message) {
		receive(Package.createPackageFromString(message));
	}
	
	final LinkedList<Package> getRefReceivedPackages() {
		return receivedPackages;
	}
	
	//method
	/**
	 * @return the index of the next sent package. of the current {@link NetEndPoint}
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
	 * Lets the current {@link NetEndPoint} receive the given package.
	 * 
	 * @param pPackage
	 */
	void receive(final Package pPackage) {
		
		//Enumerates the message role of the given package.
		switch (pPackage.getMessageRole()) {
			case RESPONSE_EXPECTING_MESSAGE:
				
				try {
					final String reply = getRefReplier().getOutput(pPackage.getRefContent());
					if (isOpen()) {
						send(new Package(pPackage.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
					}
				} catch (final Exception exception) {
					String responseMessage = exception.getMessage();
					send(new Package(pPackage.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				getRefReceivedPackages().addAtEnd(pPackage);
		}
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
		return getRefReceivedPackages().removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @param index
	 * @return true if the current {@link NetEndPoint} has received a package with the given index.
	 */
	private final boolean receivedPackage(final int index) {
		return getRefReceivedPackages().containsAny(rp -> rp.hasIndex(index));
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
	 * Sends the given message and waits to the reply.
	 * 
	 * @param message
	 * @return the reply to the given message if the current {@link NetEndPoint} stays connected, null otherwise.
	 */
	private String sendAndWaitToReply(final String message) {
		
		//Sends message and receives reply.
		final var index = getNextSentPackageIndex();
		send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message));
		final Package response = waitToAndGetAndRemoveReceivedPackage(index);
		
		if (response == null) {
			return null;
		}
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return response.getRefContent();
			case ERROR_RESPONSE:
				throw new GeneralException(response.getRefContent());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REPLY, response, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} wait to and return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the received package with the given index.
	 * @throws RuntimeException if the current {@link NetEndPoint} reaches its timeout before it receives a package with the given index.
	 */
	private Package waitToAndGetAndRemoveReceivedPackage(final int index) {
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!receivedPackage(index)) {
			
			//Handles the case that the current NetEndPoint is closed.
			if (isClosed()) {
				return null;
			}
			
			//This statement, which is theoretically unnecessary, makes that the current loop is not optimized away.
			System.err.flush();
		}
		
		return getAndRemoveReceivedPackage(index);
	}
}
