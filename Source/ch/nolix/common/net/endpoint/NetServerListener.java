//package declaration
package ch.nolix.common.net.endpoint;

//Java imports
import java.io.IOException;
import java.net.Socket;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.worker.Worker;

//class
/**
 * A {@link NetServerListener} listens to {@link NetEndPoint}s for a {@link NetServer}.
 *  
 * @author Silvan Wyss
 * @date 2017-05-06
 * @lines 70
 */
final class NetServerListener extends Worker {
	
	//attribute
	private final NetServer parentNetServer;
	
	//constructor
	/**
	 * Creates a new {@link NetServerListener} that will belong to the given parentNetServer.
	 * 
	 * @param parentNetServer
	 * @throws ArgumentIsNullException if the given parentNetServer is null.
	 */
	public NetServerListener(final NetServer parentNetServer) {
		
		//Asserts that the given netServer is not null.
		Validator.assertThat(parentNetServer).thatIsNamed("parent NetServer").isNotNull();
		
		//Sets the parentNetServer of the current NetServerListener.
		this.parentNetServer = parentNetServer;
	}
	
	//method
	/**
	 * @return true if the current {@link NetServerListener} is open.
	 */
	public boolean isOpen() {
		return parentNetServer.isOpen();
	}
	
	//method
	/**
	 * Runs the current {@link NetServerListener}.
	 * Will close the {@link NetServer}, the current {@link NetServerListener} belongs to, when an error occurs.
	 */
	@Override
	protected void run() {
		try {
			while (isOpen()) {
				final Socket socket = parentNetServer.getRefServerSocket().accept();
				takeSocket(socket);
			}
		} catch (final IOException exception) {
			parentNetServer.close();
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetServerListener} take the given socket.
	 * 
	 * @param socket
	 */
	private void takeSocket(final Socket socket) {
		new NetServerSocketProcessor(parentNetServer, socket).start();
	}
}
