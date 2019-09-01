//package declaration
package ch.nolix.common.endPoint;

import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;

//class
/**
 * A local end point can send messages to an other local end point
 * which is its counterpart.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 90
 */
public final class LocalEndPoint extends EndPoint {

	//attribute
	private final LocalEndPoint counterPart;
	
	//constructor
	/**
	 * Creates a new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IElementTaker<EndPoint> target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterPart = new LocalEndPoint(this);
		
		//Creates an abort dependency between this local end point and its counterpart.
		createCloseDependency(getCounterPart());
		
		//Lets the given target take the counterpart of this local end point.
		target.run(getCounterPart());
	}
	
	//constructor
	/**
	 * Creates a new local end point with the given counterpart.
	 * 
	 * @param counterPart
	 * @throws ArgumentIsNullException if the given counterpart is null.
	 */
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given counterpart is not null.
		Validator.suppose(counterPart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterPart = counterPart;
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @throws ArgumentIsNullException if the given message is null.
	 * @throws InvalidArgumentException if this local end point is aborted.
	 * @throws ArgumentDoesNotHaveAttributeException if the counterpart of this local end point does not have a receiver.
	 */
	@Override
	public void send(final String message) {
		
		//Checks if the given message is not null.
		Validator.suppose(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		supposeIsAlive();
		
		counterPart.receive(message);
	}
	
	//method
	/**
	 * Lets this local end point note an abort.
	 */
	@Override
	protected void noteClose() {}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	private LocalEndPoint getCounterPart() {
		return counterPart;
	}
}
