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
 * A net server sub listener listens for net end points for a net server.
 * 
 * A net server must be able to be aborted from outside.
 * Because a net server sub listener cannot check a status while waiting to a socket.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 60
 */
final class NetServerListener extends Thread {

	//attributes
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates a new net server sub listener that belongs to the given net server.
	 * The net server sub listener will start automatically.
	 * 
	 * @param netServer
	 * @throws ArgumentIsNullException if the given net server is null.
	 * @throws RuntimeException if an error occurs.
	 */
	public NetServerListener(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		Validator.suppose(netServer).isOfType(NetServer.class);
		
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
	@Override
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
		if (!socket.isClosed()) {
			netServer.takeEndPoint(new NetEndPoint(socket, netServer.getHTTPMessage()));
		}
	}
}
