//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.netapi.baseendpointapi.TargetSlotDefinition;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * A {@link NetEndPoint} is a {@link EndPoint} that can send messages to an other {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @date 2017-05-22
 */
public final class NetEndPoint extends EndPoint {
	
	//constant
	private int nextSentPackageIndex = 1;
	
	//attributes
	private final IEndPoint internalEndPoint;
	
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
		this(new ch.nolix.core.net.endpoint.SocketEndPoint(port));
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
		this(new ch.nolix.core.net.endpoint.SocketEndPoint(port, target));
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
		this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip));
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
		this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip, port));
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
		this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip, port, target));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given internalEndPoint.
	 * 
	 * @param internalEndPoint
	 * @throws ArgumentIsNullException if the given internalEndPoint is null.
	 */
	NetEndPoint(final IEndPoint internalEndPoint) {
		
		GlobalValidator.assertThat(internalEndPoint).thatIsNamed("internal EndPoint").isNotNull();
		
		this.internalEndPoint = internalEndPoint;
		createCloseDependencyTo(internalEndPoint);
		internalEndPoint.setReceiver(this::receive);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createCloseDependencyTo(final GroupCloseable element) {
		
		//This implementation just ensures that it cannot be overwritten.
		super.createCloseDependencyTo(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectionType getConnectionType() {
		return internalEndPoint.getConnectionType();
	}
	
	//method
	/**
	 * @return the target of the current {@link NetEndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if this net end point does not have a target.
	 */
	@Override
	public String getCustomTargetSlot() {
		return internalEndPoint.getCustomTargetSlot();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PeerType getPeerType() {
		return internalEndPoint.getPeerType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecurityLevel getConnectionSecurityLevel() {
		return internalEndPoint.getConnectionSecurityLevel();
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} is a net end point.
	 */
	public boolean isNetEndPoint() {
		return internalEndPoint.isSocketEndPoint();
	}
	
	//method
	/**
	 * Sends the given message and returns the reply.
	 * 
	 * @param message
	 * @return the reply to the given message if the current {@link NetEndPoint} stays connected, null otherwise.
	 */
	@Override
	public String getReplyForRequest(final String message) {
		return sendAndWaitToReply(message);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TargetSlotDefinition getTargetSlotDefinition() {
		return internalEndPoint.getTargetSlotDefinition();
	}
	
	//method
	/**
	 * @return the internal end point of the current {@link NetEndPoint}.
	 */
	IEndPoint getOriInternalEndPoint() {
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
	
	LinkedList<Package> getOriReceivedPackages() {
		return receivedPackages;
	}
	
	//method
	/**
	 * @return the index of the next sent package. of the current {@link NetEndPoint}
	 */
	int getNextSentPackageIndex() {
		
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
					final String reply = getOriReplier().getOutput(pPackage.getOriContent());
					if (isOpen()) {
						send(new Package(pPackage.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
					}
				} catch (final Throwable error) {
					String responseMessage = error.getMessage();
					send(new Package(pPackage.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				getOriReceivedPackages().addAtEnd(pPackage);
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
	private Package getAndRemoveReceivedPackage(final int index) {
		return getOriReceivedPackages().removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @param index
	 * @return true if the current {@link NetEndPoint} has received a package with the given index.
	 */
	private boolean receivedPackage(final int index) {
		return getOriReceivedPackages().containsAny(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given package.
	 * 
	 * @param pPackage
	 */
	private void send(final Package pPackage) {
		internalEndPoint.sendMessage(pPackage.toString());
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
		final var response = waitToAndGetAndRemoveReceivedPackage(index);
		
		if (response == null) {
			return null;
		}
		
		//Enumerates the response.
		return
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE ->
				response.getOriContent();
			case ERROR_RESPONSE ->
				throw GeneralException.withErrorMessage(response.getOriContent());
			default ->
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REPLY,	response);
		};
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
