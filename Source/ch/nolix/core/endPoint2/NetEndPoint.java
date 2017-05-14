//package declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports


import ch.nolix.core.constants.IPv6Manager;
import ch.nolix.core.constants.PortManager;
import ch.nolix.core.validator2.ZetaValidator;

//class
/**
 * A net end point can send messages to an other net end point that is on:
 * -the same process on the local computer
 * -an other process on the local computer
 * -an other process on an other computer
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 */
public final class NetEndPoint extends EndPoint {
	
	//constants
	private static final String DEFAULT_TARGET = "Target";
	private static final int TIMEOUT_IN_MILLISECONDS = 10000;
	
	//attributes
	private String target;
	private final Socket socket;
	private final PrintWriter printWriter;
		
	//constructor
	/**
	 * Creates new net end point that will connect to 
	 * the default target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		
		//Calls other constructor.
		this(IPv6Manager.LOOP_BACK_ADDRESS, port, DEFAULT_TARGET);
	}
	
	//constructor
	/**
	 * Creates new net end point that connects to
	 * the default target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls other constructor.
		this(ip, port, DEFAULT_TARGET);
	}
	
	//constructor
	/**
	 * Creates new net end point that will connect to
	 * the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		setTarget(target);
		
		//Checks if the given port is in [0, 65535]. 
		ZetaValidator
		.supposeThat(port)
		.thatIsNamed("port")
		.isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
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
		
		send(target);
	}
	
	//package-visible constructor
	/**
	 * Creates new end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is null.
	 */
	NetEndPoint(final Socket socket) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is not null.
		ZetaValidator.supposeThat(socket).thatIsInstanceOf(Socket.class).isNotNull();
		
		//Sets the socket of this net end point.
		this.socket = socket;
		
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and starts the listener of this net end point.
		new NetEndPointSubListener(this);
		
		waitToTarget();
	}
	
	//method
	/**
	 * @return the target of this net end point.
	 */
	public String getTarget() {
		return target;
	}
	
	//method
	/**
	 * Lets this end point send the given message.
	 * 
	 * @param message
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	public void send(final String message) {
		
		//Checks if the given message is not null.
		ZetaValidator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		printWriter.println(message);
		printWriter.flush();
	}

	//package-visible method
	/**
	 * @return the socket of this end point.
	 */
	Socket getRefSocket() {
		return socket;
	}
	
	//package-visible method
	/**
	 * Lets this net end point receive the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	void receive(final String message) {
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		if (!receivedTarget()) {
			setTarget(message);
		}
		else {
			getRefReceiver().receive(message);
		}
	}
	
	//method
	/**
	 * @return true if this net end point received a target.
	 */
	private boolean receivedTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * Sets the target of this net end point.
	 * 
	 * @param target
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 * @throws InvalidArgumentException if this net end point is aborted.
	 */
	private void setTarget(final String target) {
		
		//Checks if the given target is not empty.
		ZetaValidator.supposeThat(target).thatIsNamed("target").isNotEmpty();
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		this.target = target;
	}

	//method
	/**
	 * Lets this net end point wait to the target.
	 * 
	 *  @throws RuntimeException if this net end point reaches the timeout before it receives the target.
	 *  @throws InvalidArgumentException if this net end point is aborted.
	 */
	private void waitToTarget() {
		
		//Checks if this net end point is not stopped.
		throwExceptionIfAborted();
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!receivedTarget()) {
			
			//This statement that is actually unnecessary makes that the loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > TIMEOUT_IN_MILLISECONDS) {
				throw new RuntimeException("Zeta end point reached timeout while waiting to target application.");
			}
		}
	}
}
