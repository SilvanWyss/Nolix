//package declaration
package ch.nolix.core.endPoint;

import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.optionalClosableElement.OptionalClosableElement;
import ch.nolix.core.validator.Validator;

//class
/**
 * A server manages an end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 */
public class Server extends OptionalClosableElement {

	//attribute
	private final IElementTaker<EndPoint> endPointTaker;
	
	/**
	 * Creates a new server with the given end point taker.
	 * 
	 * @param endPointTaker
	 * @throws NullArgumentException if the given end point taker is null.
	 */
	public Server(final IElementTaker<EndPoint> endPointTaker) {
		
		//Checks if the given end point taker is not null.
		Validator.suppose(endPointTaker).thatIsNamed("andPointTaker").isNotNull();
		
		//Sets the end point taker of this server.
		this.endPointTaker = endPointTaker;
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 * @throws InvalidArgumentException if this server is aborted.
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Checks if this server is not aborted.
		supposeIsAlive();
		
		endPointTaker.run(endPoint);
	}

	//method
	/**
	 * Lets this server note an abort.
	 */
	@Override
	protected void noteClose() {}
}
