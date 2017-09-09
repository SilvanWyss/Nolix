//package declaration
package ch.nolix.core.endPoint2;

//Java imports
import java.io.IOException;
import java.net.Socket;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A net server sub listener listens for net end points for a net server.
 * 
 * A net server must be able to be aborted from outside.
 * Because a net server sub listener cannot check a status while waiting to a socket.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 60
 */
final class NetServerSubListener extends Thread {

	//attributes
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates new net server sub listener that belongs to the given net server.
	 * The net server sub listener will start automatically.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given net server is null.
	 * @throws RuntimeException if an error occurs.
	 */
	public NetServerSubListener(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		Validator.supposeThat(netServer).thatIsInstanceOf(NetServer.class).isNotNull();
		
		//Sets the net server of htis net server sub listener.
		this.netServer = netServer;
		
		//Starts this net server sub listener.
		start();
	}

	//method
	/**
	 * Runs this net server sub listener.
	 * Aborts this net server sub listener if an error occurs.
	 */
	public void run() {
		try {
			
			//This loop will be finished when the accept method of the server socket throws an exception.
			//The server socket throws an exception if the net server is aborted or if an error occurs. 
			while (true) {
				final Socket socket = netServer.getRefServerSocket().accept();
				Sequencer.runInBackground(() -> takeSocket(socket));
			}
		}
		catch (final IOException exception) {
			netServer.close();
		}
	}
	
	//method
	/**
	 * Lets this net server sub listener take the given socket.
	 * 
	 * @param socket
	 */
	private void takeSocket(final Socket socket) {
		final NetEndPoint netEndPoint = new NetEndPoint(socket);
		netServer.takeEndPoint(netEndPoint);
	}
}
