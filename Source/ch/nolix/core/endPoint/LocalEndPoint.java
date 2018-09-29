//package declaration
package ch.nolix.core.endPoint;

import ch.nolix.core.validator2.Validator;

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
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterPart = new LocalEndPoint(this);
		
		//Creates an abort dependency between this local end point and its counterpart.
		createCloseDependency(getCounterPart());
		
		//Lets the given target take the counterpart of this local end point.
		target.takeEndPoint(getCounterPart());
	}
	
	//constructor
	/**
	 * Creates a new local end point with the given counterpart.
	 * 
	 * @param counterPart
	 * @throws NullArgumentException if the given counterpart is not an instance.
	 */
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given counterpart is an instance.
		Validator.suppose(counterPart).thatIsNamed("counterpart").isInstance();
		
		//Sets the counter part of this local end point.
		this.counterPart = counterPart;
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @throws NullArgumentException if the given message is not an instance.
	 * @throws InvalidStateException if this local end point is aborted.
	 * @throws UnexistingAttributeException if the counterpart of this local end point has no receiver.
	 */
	public void send(final String message) {
		
		//Checks if the given message is an instance.
		Validator.suppose(message).thatIsNamed("message").isInstance();
		
		//Checks if this local end point is not aborted.
		supposeIsAlive();
		
		counterPart.receive(message);
	}
	
	//method
	/**
	 * Lets this local end point note an abort.
	 */
	protected void noteClose() {}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	private LocalEndPoint getCounterPart() {
		return counterPart;
	}
}
