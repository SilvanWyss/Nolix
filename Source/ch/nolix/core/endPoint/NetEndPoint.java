//package declaration
package ch.nolix.core.endPoint;

//Java import
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import ch.nolix.core.constants.IPv6Manager;
//own imports
import ch.nolix.core.constants.PortManager;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A net end point is an end point that can send messages to an other net end point
 * -on the same process on the local machine
 * -on an other process on the local machine
 * -on an other machine
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 130
 */
public final class NetEndPoint extends EndPoint {
	
	//attribute
	private final Socket socket;
	private final PrintWriter printWriter;

	public NetEndPoint(final int port) {
		this(IPv6Manager.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates new net end point that will connect to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.supposeThat(port)
		.thatIsNamed("port")
		.isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
		try {
			
			//Creates the socket of this net end point.
			socket = new Socket(ip, port);
			
			//Creates the printwriter of this net end point.
			printWriter = new PrintWriter(socket.getOutputStream());
		} 
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates net end point sub listener for this net end point.
		new NetEndPointSubListener(this);
	}
	
	//constructor
	/**
	 * Creates new net end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is null.
	 */
	NetEndPoint(final Socket socket) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is not null.
		Validator.supposeThat(socket).thatIsInstanceOf(Socket.class).isNotNull();
		
		//Sets the socket of this net end point.
		this.socket = socket;
		
		//Sets the print writer of this net end point.
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates net end point sub listener for this net end point.
		new NetEndPointSubListener(this);
	}
	
	//method
	/**
	 * Lets this net end point note an abort.
	 */
	protected void noteAbort() {
		try {
			socket.close();
		}
		catch (final IOException exception) {}
	}
	
	//method
	/**
	 * Lets this net end point send the given message.
	 * 
	 * @param message
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidStateException if this net end point is aborted.
	 */
	public void send(final String message) {
		
		//Checks if the given message is not null.
		Validator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this net end point is not aborted.
		throwExceptionIfAborted();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//package-visible method
	/**
	 * @return the socket of this net end point.
	 */
	protected Socket getRefSocket() {
		return socket;
	}
}
