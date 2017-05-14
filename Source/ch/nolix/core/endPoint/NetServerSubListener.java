//package declaration
package ch.nolix.core.endPoint;

//Java imports
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//own imports

import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.zetaValidator.ZetaValidator;

//package-visible class
/**
 * A net server sub listener listens for net end points for a net server.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 70
 */
final class NetServerSubListener extends Thread {

	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates new net server sub listener that belongs to the given net server.
	 * The net server sub listener starts automatically.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given net server is null.
	 */
	public NetServerSubListener(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		ZetaValidator.supposeThat(netServer).thatIsInstanceOf(NetServer.class).isNotNull();
		
		//Sets the net server of htis net server sub listener.
		this.netServer = netServer;
		
		//Starts this net server sub listener.
		start();
	}
	
	//method
	/**
	 * Runs this net server sub listener.
	 */
	public void run() {
		try (final ServerSocket serverSocket = new ServerSocket(netServer.getPort())) {
			while (netServer.isNotAborted()) {
				final Socket socket = serverSocket.accept();
				Sequencer.runInBackground(() -> takeSocket(socket));
			}
		}
		catch (final IOException exception) {
			netServer.abort(exception.getMessage());
		}
	}
	
	//method
	/**
	 * Lets this net server sub listener take the given socket.
	 * 
	 * @param socket
	 */
	private void takeSocket(final Socket socket) {
		netServer.takeEndPoint(new NetEndPoint(socket));
	}
}
