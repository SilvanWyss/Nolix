//package declaration
package ch.nolix.core.endPoint;

//own import
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
	 * Creates new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterPart = new LocalEndPoint(this);
		
		//Creates an abort dependency between this local end point and its counterpart.
		createAbortDependency(getCounterPart());
		
		//Lets the given target take the counterpart of this local end point.
		target.takeEndPoint(getCounterPart());
	}
	
	//constructor
	/**
	 * Creates new local end point with the given counterpart.
	 * 
	 * @param counterPart
	 * @throws NullArgumentException if the given counterpart is null.
	 */
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given counterpart is not null.
		Validator.supposeThat(counterPart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterPart = counterPart;
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidStateException if this local end point is aborted.
	 * @throws UnexistingAttributeException if the counterpart of this local end point has no receiver.
	 */
	public void send(final String message) {
		
		//Checks if the given message is not null.
		Validator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		supposeBeingAlive();
		
		counterPart.receive(message);
	}
	
	//method
	/**
	 * Lets this local end point note an abort.
	 */
	protected void noteClosing() {}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	private LocalEndPoint getCounterPart() {
		return counterPart;
	}
}
