//package declaration
package ch.nolix.common.endPoint;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ch.nolix.common.constant.IPv6Catalogue;
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Asserts that the given port is in [0, 65535]. 
		Validator
		.assertThat(port)
		.thatIsNamed("port")
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		setPreCloseAction(this::runPreClose);
		
		try {
			
			//Creates the socket of this net end point.
			socket = new Socket(ip, port);
			
			//Creates the printwriter of this net end point.
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new WrapperException(exception);
		}
		
		//Creates net end point sub listener for this net end point.
		new NetEndPointSubListener(this);
	}
	
	//constructor
	/**
	 * Creates a new net end point with the given socket.
	 * 
	 * @param socket
	 * @throws ArgumentIsNullException if the given socket is null.
	 */
	NetEndPoint(final Socket socket) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Asserts that the given socket is not null.
		Validator.assertThat(socket).isOfType(Socket.class);
		
		setPreCloseAction(this::runPreClose);
		
		//Sets the socket of this net end point.
		this.socket = socket;
		
		//Sets the print writer of this net end point.
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new WrapperException(exception);
		}
		
		//Creates net end point sub listener for this net end point.
		new NetEndPointSubListener(this);
	}
	
	//method
	/**
	 * Lets this net end point send the given message.
	 * 
	 * @param message
	 * @throws ArgumentIsNullException if the given message is null.
	 * @throws ClosedArgumentException if this net end point is closed.
	 */
	@Override
	public void send(final String message) {
		
		//Asserts that the given message is not null.
		Validator.assertThat(message).thatIsNamed("message").isNotNull();
		
		//Asserts that this net end point is not aborted.
		assertIsOpen();
		
		printWriter.println(message);
		printWriter.flush();
	}
	
	//method
	/**
	 * @return the socket of this net end point.
	 */
	Socket getRefSocket() {
		return socket;
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} run a pre-close.
	 */
	private void runPreClose() {
		try {
			socket.close();
		}
		catch (final IOException pIOException) {
			Logger.logError(pIOException);
		}
	}
}
