//package declaration
package ch.nolix.common.zetaEndPoint;

import java.net.ServerSocket;

import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
 * A zeta end point listener listens to zeta end points on a specific port for a server.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 10
 */
final class ZetaEndPointListener extends Thread {
	
	//attribute
	private final Server server;
	
	//constructor
	/**
	 * Creates new zeta end point listener that belongs to the given server.
	 * 
	 * @param server
	 * @throws NullArgumentException if the given server is null.
	 */
	public ZetaEndPointListener(final Server server) {
		
		//Checks if the given server is not null.
		ZetaValidator.supposeThat(server).thatIsInstanceOf(Server.class).isNotNull();
		
		//Sets the server of this zeta end point listener.
		this.server = server;
	}

	//method
	/**
	 * Runs this zeta end point listener.
	 */
	public void run() {
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(server.getPort());
			
			while (server.isNotAborted()) {
				final ZetaEndPoint zetaEndPoint = new ZetaEndPoint(serverSocket.accept());
				Sequencer.runInBackground(() -> server.takeZetaEndPoint(zetaEndPoint));
			}			
		}
		catch (final Exception exception) {	
			server.abort(exception.getMessage());
		}
		finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
