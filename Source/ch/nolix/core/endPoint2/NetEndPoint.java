//package declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports
import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link NetEndPoint} can send messages to an other {@link NetEndPoint} that is on:
 * -the same process on the local computer
 * -another process on the local computer
 * -another process on another computer
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 370
 */
public final class NetEndPoint extends EndPoint {
	
	//attributes
	private boolean receivedTargetInfo = false;
	private boolean isBrowserEndPoint = false;
	private final String HTTPMessage;
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
		
		send_internal(NetEndPointProtocol.MAIN_TARGET_PREFIX);
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
	public NetEndPoint(
		final String ip,
		final int port,
		final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		setTarget(target);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.suppose(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
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
		
		send_internal(NetEndPointProtocol.TARGET_PREFIX + target);
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
		
		this.HTTPMessage = HTTPMessage;
		
		//Sets the socket of the current NetEndPoint.
		this.socket = socket;
		
		try {
			
			//Creates the printWriter of the current NetEndPoint.
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		//Creates and starts the listener of the current NetEndPoint.
		new NetEndPointSubListener(this);
		
		waitToTarget();
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
		send_internal("M" + message);
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
	 * Lets the current {@link NetEndPoint} receive the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidArgumentException if the given message is blank.
	 */
	@Override
	protected void receive(final String message) {
		
		//Checks if the given message is not null or blank.
		Validator
		.suppose(message)
		.thatIsNamed(VariableNameCatalogue.MESSAGE)
		.isNotBlank();
		
		//Enumerates the first character of the given message.
		switch (message.charAt(0)) {
			case NetEndPointProtocol.MESSAGE_PREFIX:
				
				//Checks if the current NetEndPoint is alive.
				supposeIsAlive();
			
				//Calls method of the base class.
				super.receive(message.substring(1));
				
				break;
			case NetEndPointProtocol.MAIN_TARGET_PREFIX:
				
				//Checks if the current NetEndPoint is alive.
				supposeIsAlive();
				
				receivedTargetInfo = true;
				
				break;
			case NetEndPointProtocol.TARGET_PREFIX:
				
				//Checks if the current NetEndPoint is alive.
				supposeIsAlive();
				
				receivedTargetInfo = true;
				setTarget(message.substring(1));
				
				break;
			case 'H':
				isBrowserEndPoint = true;
				send_internal(HTTPMessage);
				receivedTargetInfo = true;
				close();
				break;
			default:
				
				if (!isBrowserEndPoint) {
					Sequencer.waitForASecond();
				}
				
				if (!isBrowserEndPoint) {
					throw new InvalidArgumentException(VariableNameCatalogue.MESSAGE, message);
				}
		}
	}
	
	//package-visible method
	/**
	 * @return the socket of the current {@link NetEndPoint}.
	 */
	Socket getRefSocket() {
		return socket;
	}
	
	//method
	/**
	 * @return true if the current {@link NetEndPoint} has received a target info.
	 */
	private boolean receivedTargetInfo() {
		return receivedTargetInfo;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void send_internal(final char message) {
		
		//Calls other method.
		send_internal(String.valueOf(message));
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} send the given message.
	 * 
	 * @param message
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidArgumentException if the current {@link NetEndPoint} is not alive.
	 */
	private void send_internal(final String message) {
		
		//Checks if the given message is not null.
		Validator.suppose(message).thatIsNamed(VariableNameCatalogue.MESSAGE).isNotNull();
		
		//Checks if the current NetEndPoint is alive.
		supposeIsAlive();
		
		printWriter.println(message);
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
		
		//Checks if the current NetEndPoint is alive.
		supposeIsAlive();
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!receivedTargetInfo()) {
			
			//This statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
			
			if (isBrowserEndPoint) {
				return;
			}
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > 5000) {
				throw new InvalidArgumentException("reached timeout while waiting to target.");
			}
		}
	}
}
