//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ch.nolix.common.constants.IPv6Catalogue;
import ch.nolix.common.constants.PortCatalogue;
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.webSocket.WebSocketHandShakeRequest;

//class
/**
 * A {@link NetEndPoint} can send messages to an other {@link NetEndPoint} that is on:
 * -the same process on the local computer
 * -another process on the local computer
 * -another process on another computer
 * 
 * A {@link NetEndPoint} supports the WebSocket protocol and can communicate with a WebSocket.
 * The WebSocket protocol is an absolute crap! Because:
 * -A WebSocket requires a HTTP handshake.
 * -A WebSocket puts its messages in frames that need to be encoded awkwardly.
 * -A WebSocket sends messages, that belong together, in separate lines.
 * -A WebSocket sends empty lines without a meaning.
 * The WebSocket was designed this way because of security reasons.
 * But criminals can use a WebSocket server as well, so WebSockets do not have a better security than regular Sockets.
 * The persons, who designed or approved WebSockets, should stay in shame.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 450
 */
public final class NetEndPoint extends EndPoint {
	
	//attributes
	private NetEndPointCounterpartType counterpartType;
	private boolean hasTargetInfo = false;;
	private final String HTTPMessage;
	private boolean receivedRawMessage = false;
	private final Socket socket;
	private final PrintWriter printWriter;
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to 
	 * the main target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to 
	 * the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final int port, final String target) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the main target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip,	final int port) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.suppose(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		counterpartType = NetEndPointCounterpartType.REGULAR_NET_END_POINT;
		hasTargetInfo = true;
		HTTPMessage = null;
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
			
			//Creates the printWriter of the current NetEndPoint.
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates and starts the listener of the current NetEndPoint.
		new NetEndPointSubListener(this);
		
		sendRawMessage(NetEndPointProtocol.MAIN_TARGET_PREFIX);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		setTarget(target);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.suppose(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		counterpartType = NetEndPointCounterpartType.REGULAR_NET_END_POINT;
		hasTargetInfo = true;
		HTTPMessage = null;
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
			
			//Creates the printWriter of the current NetEndPoint.
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates and starts the listener of this {@link NetEndPoint}.
		new NetEndPointSubListener(this);
		
		sendRawMessage(NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//package-visible constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given socket and HTTP message.
	 * 
	 * @param socket
	 * @param HTTPMessage
	 * @throws NullArgumentException if the given socket is null.
	 * @throws NullArgumentException if the given HTTPMessage is null.
	 * @throws InvalidArgumentException if the given HTTPMessage is blank.
	 */
	NetEndPoint(final Socket socket, final String HTTPMessage) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is not null.
		Validator.suppose(socket).isOfType(Socket.class);
		
		//Checks if the given HTTP message is not null or empty.
		Validator.suppose(HTTPMessage).thatIsNamed("HTTP message").isNotEmpty();
		
		//Sets the HTTPMessage of the current NetEndPoint.
		this.HTTPMessage = HTTPMessage;
		
		//Sets the socket of the current NetEndPoint.
		this.socket = socket;
		
		try {
			
			//Creates the printWriter of the current NetEndPoint.
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates and starts the listener of the current NetEndPoint.
		new NetEndPointSubListener(this);
		
		waitToTarget();
	}
	
	//method
	/**
	 * 
	 * @return the type of the counterpart the current {@link NetEndPoint}.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} does not know the type of its counterpart.
	 */
	public NetEndPointCounterpartType getCounterpartType() {
		
		//Checks if the current NetEndPoint knows the type of its counterpart.
		if (counterpartType == null) {
			throw new InvalidArgumentException(this, "does not know the type of its counterpart");
		}
		
		return counterpartType;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNetEndPoint() {
		return true;
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} knows the type of its counterpart.
	 */
	public boolean knowsCounterpartType() {
		return (counterpartType != null);
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} received a raw message.
	 */
	public boolean receivedAnyRawMessage() {
		return receivedRawMessage;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(final String message) {
		sendRawMessage("M" + message);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteClose() {
		try {
			socket.close();
		}
		catch (final IOException IOException) {}
	}
	
	//package-visible method
	/**
	 * @return the socket of the current {@link NetEndPoint}.
	 */
	Socket getRefSocket() {
		return socket;
	}

	//package-visible method
	/**
	 * Lets the current {@link NetEndPoint} receive the given rawMessages.
	 * 
	 * @param rawMessages
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive
	 */
	void receiveRawMessages(final IContainer<String> rawMessages) {
		
		//TEMP
		//System.out.println(hashCode() + " received: " + rawMessages);
		
		receivedRawMessage = true;
		
		if (!knowsCounterpartType()) {
			receiveInitialRawMessages(rawMessages);
		}
		else {
			receiveCommonRawMessages(rawMessages);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} has a target info.
	 */
	private boolean hasTargetInfo() {
		return hasTargetInfo;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given commonRawMessages.
	 * 
	 * @param commonRawMessages
	 */
	private void receiveCommonRawMessages(final IContainer<String> commonRawMessages) {
		if (commonRawMessages.containsOne()) {
			receiveRawMessage(commonRawMessages.getRefOne());
		}
		else {
			
			//Checks if the current NetEndPoint is alive.
			supposeIsAlive();
			
			//TODO
		}
	}

	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given initialRawMessages.
	 * 
	 * @param initialRawMessages
	 */
	private void receiveInitialRawMessages(final IContainer<String> initialRawMessages) {
		
		if (initialRawMessages.containsOne()) {
			receiveRawMessage(initialRawMessages.getRefOne());
		}
		else if (WebSocketHandShakeRequest.canBe(initialRawMessages)) {
			
			//Checks if the current NetEndPoint is alive.
			supposeIsAlive();
			
			counterpartType = NetEndPointCounterpartType.WEBSOCKET_NET_END_POINT;
			
			final var webSocketHandshakeRequest = new WebSocketHandShakeRequest(initialRawMessages);
			sendRawMessage(webSocketHandshakeRequest.getWebSocketHandShakeResponse().toString());
		}
		else {
			
			//Checks if the current NetEndPoint is alive.
			supposeIsAlive();
			
			counterpartType = NetEndPointCounterpartType.BROWSER_INITIAL_NET_END_POINT;
			sendRawMessage(HTTPMessage);
			close();
		}
	}
	
	//method
	private void receiveRawMessage(final String rawMessage) {
		
		//Checks if the current NetEndPoint is alive.
		supposeIsAlive();
		
		//Enumerates the first character of the given rawMessage.
		switch (rawMessage.charAt(0)) {
			case NetEndPointProtocol.TARGET_PREFIX:
				counterpartType = NetEndPointCounterpartType.REGULAR_NET_END_POINT;
				setTarget(rawMessage.substring(1));
				hasTargetInfo = true;
				break;
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				counterpartType = NetEndPointCounterpartType.REGULAR_NET_END_POINT;
				hasTargetInfo = true;
				break;
			case NetEndPointProtocol.MESSAGE_PREFIX:
				
				if (!knowsCounterpartType()) {
					throw new InvalidArgumentException(this, "does not know the type of its counterpart");
				}
				
				getRefReceiver().receive(rawMessage.substring(1));
				
				break;
			default:
				throw new InvalidArgumentException("first raw message", rawMessage, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void sendRawMessage(final char rawMessage) {
		
		//Calls other method.
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 * @throws NullArgumentException if the given rawMessage is null.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void sendRawMessage(final String rawMessage) {
		
		//TEMP
		//System.out.println(hashCode() + " send: " + rawMessage);
		
		//Checks if the given message is not null.
		Validator.suppose(rawMessage).thatIsNamed(VariableNameCatalogue.MESSAGE).isNotNull();
		
		//Checks if the current NetEndPoint is alive.
		supposeIsAlive();
		
		printWriter.println(rawMessage);
		printWriter.println(StringCatalogue.EMPTY_STRING);
		printWriter.flush();
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} wait to the target.
	 * 
	 * @throws RuntimeException if the current {@link NetEndPoint} reaches the timeout before it receives a target.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void waitToTarget() {
		
		if (hasTargetInfo) {
			return;
		}
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!hasTargetInfo()) {
			
			//This statement, that is actually unnecessary, makes that the current loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > 5000) {
				throw new InvalidArgumentException("reached timeout while waiting to target.");
			}
		}
	}
}
