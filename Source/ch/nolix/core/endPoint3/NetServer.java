//package declaration
package ch.nolix.core.endPoint3;

import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.invalidStateException.InvalidStateException;

//class
/**
* A net server is a server that listens to net end points on a specific port.
* 
* @author Silvan Wyss
* @month 2016-05
* @lines 30
*/
public final class NetServer<M, R> extends Server<M, R> {
	
	//attribute
	private final ch.nolix.core.endPoint2.NetServer<Package> internalNetServer;
	final IElementTakerElementGetter<String, M> messageTransformer;
	final IElementTakerElementGetter<String, R> replyTransformer;
	
	//constructor
	/**
	 * Creates new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port,
		final IElementTakerElementGetter<String, M> messageTransformer,
		final IElementTakerElementGetter<String, R> replyTransformer) {
		
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
		
		//Creates the internal net server of this net server.
		internalNetServer =
		new ch.nolix.core.endPoint2.NetServer<Package>(port, s -> Package.createZetaPackageFromString(s));
	}
	
	//method
	/**
	 * Aborts this net server.
	 * 
	 * @throws InvalidStateException if this net server is already aborted.
	 */
	public final void abort() {
		
		//Calls method of the base class.
		super.abort();
		
		internalNetServer.abort();
	}
	
	//method
	/**
	 * Adds the given end point taker to this net server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidStateException
	 * if this net server contains an end point taker with the same name as the given end point taker.
	 */
	public void addEndPointTaker(final IEndPointTaker<M, R> endPointTaker) {
		
		//Calls method of the base class.
		super.addEndPointTaker(endPointTaker);
		
		internalNetServer.addEndPointTaker(new EndPointTaker<M, R>(endPointTaker, messageTransformer, replyTransformer));
	}
	
	
	//method
	/**
	 * @return the port of this net server.
	 */
	public final int getPort() {
		return internalNetServer.getPort();
	}
	
	//method
	/**
	 * Removes the end point taker with the given name from this net server.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if this net server contains no end point taker with the given name.
	 */
	public void removeEndPointTaker(final String name) {
		
		//Calls method of the base class.
		super.removeEndPointTaker(name);
		
		internalNetServer.removeEndPointTaker(name);
	}
}
