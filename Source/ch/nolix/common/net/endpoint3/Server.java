//package declaration
package ch.nolix.common.net.endpoint3;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
 * A {@link Server} is a {@link BaseServer}
 * that listens to {@link NetEndPoint} on a specific port.
 * 
 * @author Silvan Wyss
 * @date 2016-11-01
 * @lines 80
 */
public final class Server extends BaseServer {
	
	//constant
	public static final int DEFAULT_PORT = ch.nolix.common.net.endpoint2.Server.DEFAULT_PORT;
	
	//attribute
	private final ch.nolix.common.net.endpoint2.Server internalServer;
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public Server(final int port) {
		
		//Creates the internal net server of the current net server.
		internalServer = new ch.nolix.common.net.endpoint2.Server(port);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependencyTo(internalServer);
	}
	
	//constructor
	/**
	 * Creates a new {@link Server} that will listen to {@link NetEndPoint}s on the given port.
	 * 
	 * When a web browser connects to the {@link Server},
	 * the {@link Server} will send the given HTTP message and close the connection.
	 * 
	 * @param port
	 * @param HTTPMessage
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is blank.
	 */
	public Server(final int port, final String HTTPMessage) {
		
		//Creates the internal net server of the current net server.
		internalServer = new ch.nolix.common.net.endpoint2.Server(port, HTTPMessage);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependencyTo(internalServer);
	}
	
	//method
	/**
	 * @return the port of the current {@link Server}.
	 */
	public int getPort() {
		return internalServer.getPort();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedDefaultEndPointTaker(final IEndPointTaker defaultEndPointTaker) {
		internalServer.addDefaultEndPointTaker(new ServerEndPointTaker(defaultEndPointTaker.getName(), this));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedEndPointTaker(final IEndPointTaker endPointTaker) {
		internalServer.addEndPointTaker(new ServerEndPointTaker(endPointTaker.getName(), this));
	}
}
