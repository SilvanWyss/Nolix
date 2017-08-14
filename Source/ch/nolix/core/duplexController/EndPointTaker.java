//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
final class EndPointTaker implements IEndPointTaker {

	//attribute
	private final IDuplexControllerTaker duplexControllerTaker;
	
	//constructor
	/**
	 * Creates new end point taker with the given duplex contorller taker.
	 * 
	 * @param duplexControllerTaker
	 * @throws NullArgumentException if the given duplex controller taker is null.
	 */
	public EndPointTaker(final IDuplexControllerTaker duplexControllerTaker) {
		
		//Checks if the given net duplex controller taker is not null.
		Validator
		.supposeThat(duplexControllerTaker)
		.thatIsInstanceOf(IDuplexControllerTaker.class)
		.isNotNull();
		
		//Sets the duplex controller taker of this end point taker.
		this.duplexControllerTaker = duplexControllerTaker;
	}
	
	//method
	/**
	 * @return the name of this end point taker.
	 */
	public String getName() {
		return duplexControllerTaker.getName();
	}
	
	//method
	/**
	 * Lets this end point taker take the given end point.
	 * 
	 * @param endPoint
	 * @throws NullArgumentException if the given end point is null.
	 * @throws InvalidArgumentException if the given end point is no NetEndPoint.
	 */
	public void takeEndPoint(final EndPoint endPoint) {
		
		//Checks if the given end point is a NetEndPoint.
		Validator
		.supposeThat(endPoint)
		.thatIsInstanceOf(EndPoint.class)
		.isOfType(NetEndPoint.class);
		
		duplexControllerTaker.takeDuplexController(
			new NetDuplexController((NetEndPoint)endPoint)
		);
	}
}
