//package declaration
package ch.nolix.common.endPoint4;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;

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
	private final ch.nolix.common.endPoint2.NetServer internalNetServer;
	private final IElementTakerElementGetter<String, M> messageTransformer;
	private final IElementTakerElementGetter<String, R> replyTransformer;
	
	//constructor
	/**
	 * Creates a new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port, IElementTakerElementGetter<String, M> messageTransformer,
			IElementTakerElementGetter<String, R> replyTransformer) {
		
		//Creates the internal net server of this net server.
		internalNetServer =	new ch.nolix.common.endPoint2.NetServer(port);
		createCloseDependency(internalNetServer);
		
		this.messageTransformer = messageTransformer;
		this.replyTransformer = replyTransformer;
	}
	
	//method
	/**
	 * Adds the given end point taker to this net server.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException
	 * if this net server contains an end point taker with the same name as the given end point taker.
	 */
	@Override
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
	 * @throws InvalidArgumentException if this net server does not contain an end point taker with the given name.
	 */
	@Override
	public void removeEndPointTaker(final String name) {
		
		//Calls method of the base class.
		super.removeEndPointTaker(name);
		
		internalNetServer.removeEndPointTaker(name);
	}
}
