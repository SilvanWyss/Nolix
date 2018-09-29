//package declaration
package ch.nolix.core.endPoint;

//Java import
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports
import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A net end point is an end point that can send messages to an other net end point that is:
 * -on the same process on the local machine
 * -on an other process on the local machine
 * -on an other machine
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 140
 */
public final class NetEndPoint extends EndPoint {
	
	//attributes
	private final Socket socket;
	private final PrintWriter printWriter;

	//constructor
	/**
	 * Creates a new net end point that will connect to the given port on the local machine.
	 * 
	 * @param port
	 */
	public NetEndPoint(final int port) {
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new net end point that will connect to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Checks if the given port is in [0, 65535]. 
		Validator
		.suppose(port)
		.thatIsNamed("port")
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
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
	
	//package-visible constructor
	/**
	 * Creates a new net end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is not an instance.
	 */
	NetEndPoint(final Socket socket) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given socket is an instance.
		Validator.suppose(socket).isInstanceOf(Socket.class);
		
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
	 * Lets this net end point send the given message.
	 * 
	 * @param message
	 * @throws NullArgumentException if the given message is not an instance.
	 * @throws InvalidStateException if this net end point is aborted.
	 */
	public void send(final String message) {
		
		//Checks if the given message is an instance.
		Validator.suppose(message).thatIsNamed("message").isInstance();
		
		//Checks if this net end point is not aborted.
		supposeIsAlive();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//package-visible method
	/**
	 * @return the socket of this net end point.
	 */
	Socket getRefSocket() {
		return socket;
	}
	
	//method
	/**
	 * Lets this net end point note an abort.
	 */
	protected void noteClose() {
		try {
			socket.close();
		}
		catch (final IOException exception) {}
	}
}
