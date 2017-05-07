//package declaration
package ch.nolix.common.endPoint;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A local end point can send messages to an other local end point
 * which is its counterpart.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 70
 */
public final class LocalEndPoint extends EndPoint {

	//attribute
	private final LocalEndPoint counterPart;
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given end point taker.
	 * 
	 * @param endPointTaker
	 */
	public LocalEndPoint(final IEndPointTaker endPointTaker) {
		
		//Creates the counterpart of this local end point.
		counterPart = new LocalEndPoint(this);
		
		endPointTaker.takeEndPoint(getCounterPart());
	}
	
	//constructor
	/**
	 * Creates new local end point with the given counterpart.
	 * 
	 * @param counterPart
	 * @throws NullArgumentException if the given counterpart is null.
	 */
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Checks if the given counterpart is not null.
		ZetaValidator.supposeThat(counterPart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterPart = counterPart;
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidStateException if this local end point is aborted.
	 */
	public void send(final String message) {
		
		//Checks if the given message is not null.
		ZetaValidator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		counterPart.receive(message);
	}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	private LocalEndPoint getCounterPart() {
		return counterPart;
	}
}
