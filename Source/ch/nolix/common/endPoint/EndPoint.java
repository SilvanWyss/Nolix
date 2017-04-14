//package declaration
package ch.nolix.common.endPoint;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports
import ch.nolix.common.basic.AbortableElement;
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.IReceiver;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * An end point can send messages to an other end point that is on:
 * -the same process
 * -an other process on the same computer
 * -a process on an other computer
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 */
public final class EndPoint extends AbortableElement {
	
	//attributes
	private final Socket socket;
	private final PrintWriter printWriter;
	
	//optional attributes
	private IReceiver receiver;	
	
	//constructor
	/**
	 * Creates new end point that will connect to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public EndPoint(final String ip, final int port) {
		
		//Checks if the given port is in [0, 65535]. 
		ZetaValidator
		.supposeThat(port)
		.thatIsNamed("port")
		.isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
		
		try {
			socket = new Socket(ip, port);
			printWriter = new PrintWriter(socket.getOutputStream());
		} 
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and starts the listener of this end point.
		new Listener(this);
	}
	
	//package-visible constructor
	/**
	 * Creates new end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is null.
	 */
	EndPoint(final Socket socket) {
		
		//Checks if the given socket is not null.
		ZetaValidator.supposeThat(socket).thatIsInstanceOf(Socket.class).isNotNull();
		
		this.socket = socket;
		
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and starts the listener of this end point.
		new Listener(this);
	}
	
	//method
	/**
	 * @return true if this end point has a receiver.
	 */
	public boolean hasReceiver() {
		return (receiver != null);
	}
	
	//method
	/**
	 * Lets this end point send the given message.
	 * 
	 * @param message
	 * @throws RuntimeEx
	 */
	public void send(final String message) {
		
		throwExceptionIfAborted();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//method
	/**
	 * Sets the receiver of this end point.
	 * 
	 * @param receiver
	 * @throws NullArgumentException if the given receiver is null.
	 */
	public void setReceiver(final IReceiver receiver) {
		
		//Checks if the given receiver is not null.
		ZetaValidator.supposeThat(receiver).thatIsNamed("receiver").isNotNull();
		
		this.receiver = receiver;
	}	
	
	//package-visble method
	/**
	 * @return the receiver of this end point
	 * @throws InvalidArgumentException if this end point is aborted.
	 * @throws UnexistingAttributeException if this end point has no receiver.
	 */
	final IReceiver getRefReceiver() {
		
		throwExceptionIfAborted();
		
		//Checks if this end point has a receiver.
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, "receiver");
		}
		
		return receiver;
	}
	
	//package-visible method
	/**
	 * @return the socket of this end point.
	 * @throws InvalidArgumentException if this end point is aborted.
	 */
	final Socket getRefSocket() {
		
		//Checks if this end point is not stopped.
		throwExceptionIfAborted();
		
		return socket;
	}
}
