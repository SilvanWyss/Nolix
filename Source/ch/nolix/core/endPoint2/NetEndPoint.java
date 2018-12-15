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
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A net end point can send messages to an other net end point that is on:
 * -the same process on the local computer
 * -an other process on the local computer
 * -an other process on an other computer
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 190
 */
public class NetEndPoint extends EndPoint {
	
	//attributes
	private boolean receivedTargetInfo = false;
	private final String HTTPMessage;
	private final Socket socket;
	private final PrintWriter printWriter;
		
	//constructor
	/**
	 * Creates a new net end point that will connect to 
	 * the default target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		
		//Calls other constructor.
		this(
			IPv6Catalogue.LOOP_BACK_ADDRESS,
			port
		);
	}
	
	public NetEndPoint(
		int port,
		String target
	) {
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}
	
	//constructor
	/**
	 * Creates a new net end point that connects to
	 * the default target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(
		final String ip,
		final int port) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.suppose(port)
		.thatIsNamed("port")
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		HTTPMessage = null;
		
		try {
			
			//Creates the socket of this net end point.
			socket = new Socket(ip, port);
			
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and starts the listener of this net end point.
		new NetEndPointSubListener(this);
		
		send_internal("N");
	}
	
	//constructor
	/**
	 * Creates a new net end point that will connect to
	 * the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
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
		.thatIsNamed("port")
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		HTTPMessage = null;
		
		try {
			
			//Creates the socket of this net end point.
			socket = new Socket(ip, port);
			
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and starts the listener of this net end point.
		new NetEndPointSubListener(this);
		
		send_internal("T" + target);
	}
	
	//package-visible constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given socket and HTTP message.
	 * 
	 * @param socket
	 * @param HTTPMessage
	 * @throws NullArgumentException if the given socket is null.
	 */
	NetEndPoint(final Socket socket, final String HTTPMessage) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is not null.
		Validator.suppose(socket).isInstanceOf(Socket.class);
		
		//Checks if the given HTTP message is not null and not empty.
		Validator.suppose(HTTPMessage).thatIsNamed("HTTP message").isNotEmpty();
		
		this.HTTPMessage = HTTPMessage;
		
		//Sets the socket of the current net end point.
		this.socket = socket;
		
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and starts the listener of the current net end point.
		new NetEndPointSubListener(this);
		
		waitToTarget();
	}
	
	//method
	/**
	 * @return true if this net end point is a net end point.
	 */
	@Override
	public boolean isNetEndPoint() {
		return true;
	}

	//method
	/**
	 * @return the socket of this end point.
	 */
	protected Socket getRefSocket() {
		return socket;
	}
	
	//package-visible method
	/**
	 * Lets this net end point receive the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	@Override
	protected void receive(final String message) {
		
		//Checks if this net end point is not stopped.
		supposeIsAlive();
		
		//Enumerates the first character of the given message.
		switch (message.charAt(0)) {
			case 'M':
			
				//Calls method of the base class.
				super.receive(message.substring(1));
				
				break;
			case 'N':
				receivedTargetInfo = true;
				break;
			case 'T':
				receivedTargetInfo = true;
				setTarget(message.substring(1));
				break;
			case 'H':
				send_internal(HTTPMessage);
				receivedTargetInfo = true;
				close();
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.MESSAGE, message);
		}
	}
	
	@Override
	public void send(final String message) {
		send_internal("M" + message);
	}
	
	private void send_internal(final String message) {
		
		//Checks if the given message is not null.
		Validator.suppose(message).thatIsNamed("message").isInstance();
		
		//Checks if this net end point is not stopped.
		supposeIsAlive();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//method
	/**
	 * Lets this net end point note an abort.
	 */
	@Override
	protected void noteClose() {
		try {
			socket.close();
		} catch (final IOException exception) {}
	}
	
	private boolean receivedTargetInfo() {
		return receivedTargetInfo;
	}

	//method
	/**
	 * Lets this net end point wait to the target.
	 * 
	 * @throws RuntimeException if this net end point reaches the timeout before it receives the target.
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	private void waitToTarget() {
		
		//Checks if this net end point is not stopped.
		supposeIsAlive();
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!receivedTargetInfo()) {
			
			//This statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > 5000) {
				throw new RuntimeException("Zeta end point reached timeout while waiting to target application.");
			}
		}
	}
}
