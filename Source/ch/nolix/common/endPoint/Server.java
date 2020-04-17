//package declaration
package ch.nolix.common.endPoint;

//own imports
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;

//class
/**
 * A server manages an end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 */
public class Server extends ClosableElement {

	//attribute
	private final IElementTaker<EndPoint> endPointTaker;
	
	/**
	 * Creates a new server with the given end point taker.
	 * 
	 * @param endPointTaker
	 * @throws ArgumentIsNullException if the given end point taker is null.
	 */
	public Server(final IElementTaker<EndPoint> endPointTaker) {
		
		//Asserts that the given end point taker is not null.
		Validator.assertThat(endPointTaker).thatIsNamed("andPointTaker").isNotNull();
		
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
		
		//Asserts that this server is not aborted.
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
