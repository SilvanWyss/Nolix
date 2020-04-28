//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.net.Socket;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link NetServerListener} listens to {@link NetEndPoints} for a {@link NetServer}.
 *  
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 80
 */
final class NetServerListener {

	//attribute
	private final NetServer parentNetServer;
	
	//constructor
	/**
	 * Creates a new {@link NetServerListener} that will belong to the given parentNetServer.
	 * The {@link NetServerListener} will start automatically.
	 * 
	 * @param parentNetServer
	 * @throws ArgumentIsNullException if the given parentNetServer is null.
	 */
	public NetServerListener(final NetServer parentNetServer) {
		
		//Asserts that the given netServer is not null.
		Validator.assertThat(parentNetServer).thatIsNamed("parent NetServer").isNotNull();
		
		//Sets the parentNetServer of the current NetServerListener.
		this.parentNetServer = parentNetServer;
		
		Sequencer.runInBackground(this::run);
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
	private void run() {
		try {
			while (isOpen()) {
				final Socket socket = parentNetServer.getRefServerSocket().accept();
				Sequencer.runInBackground(() -> takeSocket(socket));
			}
		}
		catch (final IOException exception) {
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
		if (!socket.isClosed()) {
			parentNetServer.takeEndPoint(new NetEndPoint(socket, parentNetServer.getHTTPMessage()));
		}
	}
}
