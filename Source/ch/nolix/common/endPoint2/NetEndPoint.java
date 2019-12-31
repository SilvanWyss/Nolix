//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

//own imports
import ch.nolix.common.HTTP.HTTPRequest;
import ch.nolix.common.constants.IPv6Catalogue;
import ch.nolix.common.constants.PortCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
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
	private final String mHTTPMessage;
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
			processor = new NetEndPointProcessorForRegularCounterpart(this, new BufferedReader(new InputStreamReader(getRefSocket().getInputStream())));
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
			
		hasTargetInfo = true;
		mHTTPMessage = null;
		
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
		
		
		hasTargetInfo = true;
		mHTTPMessage = null;
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
			processor = new NetEndPointProcessorForRegularCounterpart(this, new BufferedReader(new InputStreamReader(getRefSocket().getInputStream())));
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
	 * @param pHTTPMessage
	 * @throws ArgumentIsNullException if the given socket is null.
	 * @throws ArgumentIsNullException if the given HTTPMessage is null.
	 * @throws InvalidArgumentException if the given HTTPMessage is blank.
	 */
	NetEndPoint(final Socket socket, final String pHTTPMessage) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is not null.
		Validator.suppose(socket).isOfType(Socket.class);
		
		//Checks if the given HTTP message is not null or empty.
		Validator.suppose(pHTTPMessage).thatIsNamed("HTTP message").isNotEmpty();
		
		//Sets the HTTPMessage of the current NetEndPoint.
		this.mHTTPMessage = pHTTPMessage;
		
		//Sets the socket of the current NetEndPoint.
		this.socket = socket;
				
		final var future = Sequencer.runInBackground(() -> listenToFirstMessage());
		
		future.waitUntilIsFinished(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
		
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
		catch (final IOException IOException) {
			Logger.logError(IOException);
		}
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
		try {
			
			final var inputStream = getRefSocket().getInputStream();
			
			final var bufferedReader =
			new BufferedReader(new InputStreamReader(inputStream));
			
			final var firstLine = bufferedReader.readLine();
			
			//Enumerates the first character of the first line.
			switch (firstLine.charAt(0)) {
				case NetEndPointProtocol.TARGET_PREFIX:
				case NetEndPointProtocol.MAIN_TARGET_PREFIX:
					receiveRawMessage(firstLine);
					return new NetEndPointProcessorForRegularCounterpart(this, bufferedReader);
				case 'G':
					final var lines = new LinkedList<>(firstLine);
					while (true) {
						
						final var line = bufferedReader.readLine();
						
						if (line == null) {
							bufferedReader.close();
							throw new ArgumentIsNullException(VariableNameCatalogue.LINE);
						}
						
						if (line.isEmpty()) {
							break;
						}
						
						lines.addAtEnd(line);
					}
					return receiveFirstHTTPOrWebSocketMessages(lines, inputStream);
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
	private INetEndPointProcessor receiveFirstHTTPOrWebSocketMessages(
		final IContainer<String> messages,
		final InputStream inputStream
	) {
		
		//Handles the case that the given messages are a HTTP request.
		if (HTTPRequest.canBe(messages)) {
			final var processor = new NetEndPointProcessorForHTTPCounterpart(this);
			processor.sendRawMessage(mHTTPMessage);
			return processor;
		}
		
		//Handles the case that the given messages are a WebSocket handshake request.
		if (WebSocketHandShakeRequest.canBe(messages)) {
			return
			new NetEndPointProcessorForWebSocketCounterpart(
				this,
				inputStream,
				new WebSocketHandShakeRequest(messages).getWebSocketHandShakeResponse().toString()
			);
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
		
		//For a better performance, there is made a first check.
		if (hasTargetInfo()) {
			return;
		}
		
		Sequencer
		.forMaxMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS)
		.waitUntil(() -> hasTargetInfo());
		
		if (!hasTargetInfo()) {
			throw new InvalidArgumentException(this, "reached timeout while waiting to target.");
		}
	}
}
