//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

//own imports
import ch.nolix.common.HTTP.HTTPRequest;
import ch.nolix.common.commonTypeHelper.InputStreamHelper;
import ch.nolix.common.constant.IPv6Catalogue;
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.processProperty.ConnectionOrigin;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.webSocket.WebSocketHandShakeRequest;
import ch.nolix.common.wrapperException.WrapperException;

//class
/**
 * A {@link BaseNetEndPoint} can send messages to an other {@link BaseNetEndPoint} that is on:
 * -the same process on the local computer
 * -another process on the local computer
 * -another process on another computer
 * 
 * A {@link BaseNetEndPoint} supports the WebSocket protocol and can communicate with a WebSocket.
 * The WebSocket protocol is complicated. Because:
 * -A WebSocket requires a HTTP handshake.
 * -A WebSocket puts its messages in frames that need to be encoded awkwardly.
 * -A WebSocket sends messages, that belong together, in separate lines.
 * The WebSocket protocol was designed this way because of security reasons.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 480
 */
public class BaseNetEndPoint extends EndPoint {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 10000;
	
	//attributes
	private final INetEndPointProcessor processor;
	private boolean hasTargetInfo = false;
	private final String mHTTPMessage;
	private final Socket socket;
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} that will connect to 
	 * the main target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public BaseNetEndPoint(final int port) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} that will connect to 
	 * the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public BaseNetEndPoint(final int port, final String target) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} that will connect to
	 * the main target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public BaseNetEndPoint(final String ip,	final int port) {
		
		//Calls constructor of the base class.
		super(ConnectionOrigin.REQUESTED_CONNECTION);
		
		//Asserts that the given port is in [0, 65535]. 
		Validator
		.assertThat(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
			processor = new NetEndPointProcessorForRegularCounterpart(this, getRefSocket().getInputStream());
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		hasTargetInfo = true;
		mHTTPMessage = null;
		
		sendRawMessage(NetEndPointProtocol.MAIN_TARGET_PREFIX);
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} that will connect to
	 * the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public BaseNetEndPoint(final String ip, final int port, final String target) {
		
		//Calls constructor of the base class.
		super(ConnectionOrigin.REQUESTED_CONNECTION);
		
		setTarget(target);
		
		//Asserts that the given port is in [0, 65535]. 
		Validator
		.assertThat(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		
		hasTargetInfo = true;
		mHTTPMessage = null;
		
		try {
			
			//Creates the socket of the current NetEndPoint.
			socket = new Socket(ip, port);
			processor = new NetEndPointProcessorForRegularCounterpart(this, getRefSocket().getInputStream());
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		sendRawMessage(NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//TEMP
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin) {
		
		super(connectionOrigin);
		
		socket = null;
		processor = null;
		mHTTPMessage = null;
	}
	
	//TEMP
	BaseNetEndPoint(final ConnectionOrigin connectionOrigin, final String target) {
		
		super(connectionOrigin, target);
		
		socket = null;
		processor = null;
		mHTTPMessage = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} with the given socket and HTTP message.
	 * 
	 * @param socket
	 * @param pHTTPMessage
	 * @throws ArgumentIsNullException if the given socket is null.
	 * @throws ArgumentIsNullException if the given HTTPMessage is null.
	 * @throws InvalidArgumentException if the given HTTPMessage is blank.
	 */
	BaseNetEndPoint(final Socket socket, final String pHTTPMessage) {
		
		//Calls constructor of the base class.
		super(ConnectionOrigin.ACCEPTED_CONNECTION);
		
		//Asserts that the given socket is not null.
		Validator.assertThat(socket).isOfType(Socket.class);
		
		//Asserts that the given HTTP message is not null or empty.
		Validator.assertThat(pHTTPMessage).thatIsNamed("HTTP message").isNotEmpty();
		
		//Sets the HTTPMessage of the current NetEndPoint.
		this.mHTTPMessage = pHTTPMessage;
		
		//Sets the socket of the current NetEndPoint.
		this.socket = socket;
				
		final var future = Sequencer.runInBackground(() -> listenToFirstMessage());
		
		future.waitUntilIsFinished(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS);
		
		processor = future.getResult();
		waitToTargetInfo();
	}
	
	//TEMP
	public EndPointType getType() {
		return EndPointType.REGULAR_SOCKET;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isNetEndPoint() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void send(final String message) {
		sendRawMessage(NetEndPointProtocol.MESSAGE_PREFIX + message);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNetEndPoint} has a target info.
	 */
	protected final boolean hasTargetInfo() {
		return hasTargetInfo;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteClose() {
		if (canWork()) {
			try {
				sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
				socket.close();
			}
			catch (final IOException pIOException) {
				Logger.logError(pIOException);
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 */
	protected final void sendRawMessage(final char rawMessage) {
		
		//Calls other method.
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} send the given rawMessage.
	 * 
	 * @param rawMessage
	 */
	protected void sendRawMessage(final String rawMessage) {
		processor.sendRawMessage(rawMessage);
	}
	
	//method
	/**
	 * Sends the target message of the current {@link BaseNetEndPoint}
	 * to the counterpart of the current {@link BaseNetEndPoint}.
	 */
	protected final void sendTargetMessage() {
		sendRawMessage(getTargetMessage());
	}
	
	//method
	/**
	 * @return the socket of the current {@link BaseNetEndPoint}.
	 */
	Socket getRefSocket() {
		return socket;
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} receive the given rawMessage asynchronously.
	 * 
	 * @param rawMessage
	 */
	void receiveRawMessageInBackground(final String rawMessage) {
		Sequencer.runInBackground(() -> receiveRawMessage(rawMessage));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNetEndPoint} can work.
	 */
	private boolean canWork() {
		return !socket.isClosed();
	}
	
	//method
	/**
	 * Confirms that the current {@link BaseNetEndPoint} has a target info.
	 * 
	 * @throws InvalidArgumentException if the current {@link BaseNetEndPoint} has already a target info.
	 */
	private void confirmReceivedTargetInfo() {
		
		if (hasTargetInfo) {
			throw new InvalidArgumentException(this, "has already a target info");
		}
		
		hasTargetInfo = true;
	}
	
	//method
	/**
	 * @return the target message of the current {@link BaseNetEndPoint}.
	 */
	private String getTargetMessage() {
		
		if (!hasTarget()) {
			return String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX);
		}
		
		return (NetEndPointProtocol.TARGET_PREFIX + getTarget());
	}
	
	//method
	private INetEndPointProcessor listenToFirstMessage() {
		try {
			
			final var inputStream = getRefSocket().getInputStream();
						
			final var firstLine = InputStreamHelper.readLineFrom(inputStream);
			
			//Enumerates the first character of the first line.
			switch (firstLine.charAt(0)) {
				case NetEndPointProtocol.TARGET_PREFIX:
				case NetEndPointProtocol.MAIN_TARGET_PREFIX:
					receiveRawMessage(firstLine);
					return new NetEndPointProcessorForRegularCounterpart(this, inputStream);
				case 'G':
					final var lines = new LinkedList<>(firstLine);
					while (true) {
						
						final var line = InputStreamHelper.readLineFrom(inputStream);
						
						if (line == null) {
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
		catch (final IOException pIOException) {
			close();
			throw new WrapperException(pIOException);
		}
	}

	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} receive the given initialRawMessages.
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
		
		//Asserts that the current NetEndPoint is open.
		assertIsOpen();
		
		getRefReceiver().receive(message);
	}
	
	//method
	private void receiveRawMessage(final String rawMessage) {
		
		//Enumerates the first character of the given rawMessage.
		switch (rawMessage.charAt(0)) {
			case NetEndPointProtocol.TARGET_PREFIX:
				setTarget(rawMessage.substring(1));
				confirmReceivedTargetInfo();
				break;
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				confirmReceivedTargetInfo();
				break;
			case NetEndPointProtocol.MESSAGE_PREFIX:
				receiveMessage(rawMessage.substring(1));
				break;
			case NetEndPointProtocol.CLOSE_PREFIX:
				Validator.assertThat(rawMessage).thatIsNamed("raw message").hasLength(1);
				close();
				break;
			default:
				throw new InvalidArgumentException("raw message", rawMessage, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseNetEndPoint} wait to the target.
	 * 
	 * @throws RuntimeException if the current {@link BaseNetEndPoint} reaches the timeout before it receives a target.
	 * @throws InvalidArgumentException if the current {@link BaseNetEndPoint} is not alive.
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
