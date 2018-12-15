//package declaration
package ch.nolix.core.endPoint;

//own imports
import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A server manages an end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 */
public class Server
extends ClosableElement
implements IEndPointTaker {

	//attribute
	private final IEndPointTaker endPointTaker;
	
	/**
	 * Creates a new server with the given end point taker.
	 * 
	 * @param endPointTaker
	 * @throws NullArgumentException if the given end point taker is null.
	 */
	public Server(final IEndPointTaker endPointTaker) {
		
		//Checks if the given end point taker is not null.
		Validator.suppose(endPointTaker).isInstanceOf(IEndPointTaker.class);
		
		//Sets the end point taker of this server.
		this.endPointTaker = endPointTaker;
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 * @throws InvalidStateException if this server is aborted.
	 */
	@Override
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Checks if this server is not aborted.
		supposeIsAlive();
		
		endPointTaker.takeEndPoint(endPoint);
	}

	//method
	/**
	 * Lets this server note an abort.
	 */
	@Override
	protected void noteClose() {}
}
