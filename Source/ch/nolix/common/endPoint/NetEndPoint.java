//package declaration
package ch.nolix.common.endPoint;

//Java import
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A net end point is an end point that can send messages to an other net end point
 * -on the same process on the local machine
 * -on an other process on the local machine
 * -on an other machine
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 100
 */
public final class NetEndPoint extends EndPoint {
	
	//attribute
	private final Socket socket;
	private final PrintWriter printWriter;

	//constructor
	/**
	 * Creates new net end point that will connect to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 */
	public NetEndPoint(final String ip, final int port) {
		
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
	}
	
	//constructor
	/**
	 * Creates new net end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is null.
	 */
	NetEndPoint(final Socket socket) {
		
		//Checks if the given socket is not null.
		ZetaValidator.supposeThat(socket).thatIsInstanceOf(Socket.class).isNotNull();
		
		//Sets the socket of this net end point.
		this.socket = socket;
		
		//Sets the print writer of this net end point.
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
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
		ZetaValidator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this net end point is not aborted.
		throwExceptionIfAborted();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//package-visible method
	/**
	 * @return the socket of this net end point.s
	 */
	Socket getRefSocket() {
		return socket;
	}
}
