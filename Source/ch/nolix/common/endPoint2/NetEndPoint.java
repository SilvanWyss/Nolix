//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//own imports
import ch.nolix.common.HTTP.HTTPRequest;
import ch.nolix.common.constants.IPv6Catalogue;
import ch.nolix.common.constants.PortCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
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
 * The WebSocket protocol is complicated. Because:
 * -A WebSocket requires a HTTP handshake.
 * -A WebSocket puts its messages in frames that need to be encoded awkwardly.
 * -A WebSocket sends messages, that belong together, in separate lines.
 * The WebSocket was designed this way because of security reasons.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 410
 */
public final class NetEndPoint extends EndPoint {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 10000;
	
	//attributes
	private final INetEndPointProcessor processor;
	private boolean hasTargetInfo = false;
	private final String HTTPMessage;
	private final Socket socket;
	
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
	 * @throws ArgumentIsNullException if the given target is null.
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
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		processor = new NetEndPointProcessorForRegularCounterpart(this);		
		hasTargetInfo = true;
		HTTPMessage = null;
		
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
	 * @throws ArgumentIsNullException if the given target is null.
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
		
		processor = new NetEndPointProcessorForRegularCounterpart(this);
		hasTargetInfo = true;
		HTTPMessage = null;
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		sendRawMessage(NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//package-visible constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given socket and HTTP message.
	 * 
	 * @param socket
	 * @param HTTPMessage
	 * @throws ArgumentIsNullException if the given socket is null.
	 * @throws ArgumentIsNullException if the given HTTPMessage is null.
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
		
		
		final var future = Sequencer.runInBackground(() -> listenToFirstMessage());
		
		//TODO: Use timeout.
		future.waintUntilIsFinishedSuccessfully();
		
		processor = future.getResult();
		waitToTargetInfo();
	}
	
	//method
	/**
	 * 
	 * @return the type of the counterpart the current {@link NetEndPoint}.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} does not know the type of its counterpart.
	 */
	public NetEndPointCounterpartType getCounterpartType() {
		return processor.getCounterpartType();
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
	 * Lets the current {@link NetEndPoint} receive the given rawMessage asynchronously.
	 * 
	 * @param rawMessage
	 */
	void receiveRawMessageInBackground(final String rawMessage) {
		Sequencer.runInBackground(() -> receiveRawMessage(rawMessage));
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} has a target info.
	 */
	private boolean hasTargetInfo() {
		return hasTargetInfo;
	}
	
	//method
	private INetEndPointProcessor listenToFirstMessage() {
		try (
			final BufferedReader bufferedReader =
			new BufferedReader(new InputStreamReader(getRefSocket().getInputStream()))
		) {
			
			final var firstLine = bufferedReader.readLine();
			
			//Enumerates the first character of the first line.
			switch (firstLine.charAt(0)) {
				case NetEndPointProtocol.TARGET_PREFIX:
				case NetEndPointProtocol.MAIN_TARGET_PREFIX:
					receiveRawMessage(firstLine);
					return new NetEndPointProcessorForRegularCounterpart(this);
				case 'G':
					final var lines = new List<>(firstLine);
					while (true) {
						
						final var line = bufferedReader.readLine();
						
						if (line == null) {
							throw new ArgumentIsNullException(VariableNameCatalogue.LINE);
						}
						
						if (line.isEmpty()) {
							break;
						}
						
						lines.addAtEnd(line);
					}
					return receiveFirstHTTPOrWebSocketMessages(lines);
			default:
				throw new InvalidArgumentException("first line", firstLine, "is not valid");
			}
		}
		catch (final IOException IOException) {
			close();
			throw new RuntimeException(IOException);
		}
	}

	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given initialRawMessages.
	 * 
	 * @param messages
	 */
	private INetEndPointProcessor receiveFirstHTTPOrWebSocketMessages(final IContainer<String> messages) {
		
		//Handles the case that the given messages are a HTTP request.
		if (HTTPRequest.canBe(messages)) {
			final var processor = new NetEndPointProcessorForHTTPCounterpart(this);
			processor.sendRawMessage(HTTPMessage);
			return processor;
		}
		
		//Handles the case that the given messages are a WebSocket handshake request.
		if (WebSocketHandShakeRequest.canBe(messages)) {
			final var processor = new NetEndPointProcessorForWebSocketCounterpart(this);
			final var webSocketHandshakeRequest = new WebSocketHandShakeRequest(messages);
			processor.sendRawMessage(webSocketHandshakeRequest.getWebSocketHandShakeResponse().toString());
			return processor;
		}
		
		throw new InvalidArgumentException("first HTTP or WebSocket message", messages, "is not valid");
	}
	
	//method
	private void receiveMessage(final String message) {
		getRefReceiver().receive(message);
	}
	
	//method
	private void receiveRawMessage(final String rawMessage) {
		
		//Checks if the current NetEndPoint is alive.
		supposeIsAlive();
		
		//Enumerates the first character of the given rawMessage.
		switch (rawMessage.charAt(0)) {
			case NetEndPointProtocol.TARGET_PREFIX:
				setTarget(rawMessage.substring(1));
				hasTargetInfo = true;
				break;
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				hasTargetInfo = true;
				break;
			case NetEndPointProtocol.MESSAGE_PREFIX:
				receiveMessage(rawMessage.substring(1));				
				break;
			default:
				throw new InvalidArgumentException("raw message", rawMessage, "is not valid");
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
		processor.sendRawMessage(rawMessage);
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 * @throws ArgumentIsNullException if the given rawMessage is null.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void sendRawMessage(final String rawMessage) {
		processor.sendRawMessage(rawMessage);
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} wait to the target.
	 * 
	 * @throws RuntimeException if the current {@link NetEndPoint} reaches the timeout before it receives a target.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void waitToTargetInfo() {

		//For a better performance, there is made a first simple check.
		if (hasTargetInfo) {
			return;
		}
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		final long startTimeInMilliseconds = System.currentTimeMillis();
		while (!hasTargetInfo()) {
			
			//This statement, that is actually unnecessary, makes that the current loop is not optimized away.
			System.out.flush();
			
			//TODO: Use getTimeoutInMilliseconds method.
			if (System.currentTimeMillis() - startTimeInMilliseconds > DEFAULT_TIMEOUT_IN_MILLISECONDS) {
				throw new InvalidArgumentException("reached timeout while waiting to target.");
			}
		}
	}
}
