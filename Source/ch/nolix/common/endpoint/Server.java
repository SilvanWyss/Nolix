//package declaration
package ch.nolix.common.endpoint;

//own imports
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A server manages an end point taker.
 * A server is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 60
 */
public class Server implements ICloseableElement {

	//attributes
	private final CloseController closeController = new CloseController(this);
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
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * Lets this server take the given end point.
	 * 
	 * @param endPoint
	 * @throws ClosedArgumentException if this server is closed.
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Asserts that this server is open.
		assertIsOpen();
		
		endPointTaker.run(endPoint);
	}
}
