//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.net.Socket;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;

//class
/**
 * A {@link ServerListener} listens to {@link NetEndPoint}s for a {@link Server}.
 *  
 * @author Silvan Wyss
 * @date 2017-05-06
 */
final class ServerListener extends Worker {
	
	//attribute
	private final Server parentServer;
	
	//constructor
	/**
	 * Creates a new {@link ServerListener} that will belong to the given parentServer.
	 * 
	 * @param parentServer
	 * @throws ArgumentIsNullException if the given parentServer is null.
	 */
	public ServerListener(final Server parentServer) {
		
		//Asserts that the given parentServer is not null.
		GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
		
		//Sets the parentServer of the current ServerListener.
		this.parentServer = parentServer;
		
		start();
	}
	
	//method
	/**
	 * @return true if the current {@link ServerListener} is open.
	 */
	public boolean isOpen() {
		return parentServer.isOpen();
	}
	
	//method
	/**
	 * Runs the current {@link ServerListener}.
	 * Will close the {@link Server}, the current {@link ServerListener} belongs to, when an error occurs.
	 */
	@Override
	protected void run() {
		try {
			while (isOpen()) {
				final var socket = parentServer.getRefServerSocket().accept();
				takeSocket(socket);
			}
		} catch (final IOException exception) {
			parentServer.close();
		}
	}
	
	//method
	/**
	 * Lets the current {@link ServerListener} take the given socket.
	 * 
	 * @param socket
	 */
	private void takeSocket(final Socket socket) {
		new ServerSocketProcessor(parentServer, socket);
	}
}
