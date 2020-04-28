//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.net.Socket;

//own imports
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link NetServerListener} listens to {@link NetEndPoints} for a {@link NetServer}.
 *  
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 100
 */
final class NetServerListener {

	//attributes
	private final NetServer parentNetServer;
	private boolean started = false;
	
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
	 * @return true if the current {@link NetServer} is started.
	 */
	public boolean isStarted() {
		return started;
	}
	
	//method
	/**
	 * Starts the current {@link NetServerListener}.
	 * 
	 * @throws InvalidArgumentException if the current {@link NetServerListener} is already started.
	 */
	public void start() {
		
		if (isStarted()) {
			throw new InvalidArgumentException(this, "is already started");
		}
		
		Sequencer.runInBackground(this::run);
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
