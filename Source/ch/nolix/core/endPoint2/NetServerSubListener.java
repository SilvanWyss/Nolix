//package declaration
package ch.nolix.core.endPoint2;

//Java import
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//own import

import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.zetaValidator.ZetaValidator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 60
 */
final class NetServerSubListener extends Thread {

	//attribute
	private final NetServer netServer;	

	//constructor
	/**
	 * Creates new net server sub listener that belongs to the given net server.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given server is null.
	 */
	public NetServerSubListener(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		ZetaValidator.supposeThat(netServer).thatIsInstanceOf(NetServer.class).isNotNull();
		
		//Sets the net server of this net server sub listener.
		this.netServer = netServer;
	}
	
	//method
	/**
	 * Runs this net server sub listener.
	 */
	public void run() {
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(netServer.getPort());
			
			while (netServer.isNotAborted()) {
				final Socket socket = serverSocket.accept();
				Sequencer.runInBackground(() -> netServer.takeIncomingSocket(socket));
			}
		}
		catch (final IOException exception) {
			netServer.abort(exception.getMessage());
		}
		finally {
			try {
				serverSocket.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
