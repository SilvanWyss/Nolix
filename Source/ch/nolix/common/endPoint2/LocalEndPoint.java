//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.validator.Validator;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 180
 */
public final class LocalEndPoint extends EndPoint {
	
	//attribute
	private final LocalEndPoint counterpart;
	
	//constructor
	/**
	 * Creates a new local end point that will connect to an other new local end point.
	 */
	public LocalEndPoint() {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
	}
	
	//constructor
	/**
	 * Creates a new local end point that will connect to the given target.
	 * 
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 */
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
		
		//Sets the target of the counterpart of htis local end point.
		getRefCounterPart().setTarget(target.getName());
		
		//Lets the given target take the counterpart of this local end point.
		target.takeEndPoint(getRefCounterPart());
	}
	
	//constructor
	/**
	 * Creates a new local end point that will connect to the given target on the given server.
	 * 
	 * @param server
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public LocalEndPoint(final Server server, final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
		
		//Sets the target of the counterpart of this local end point.
		getRefCounterPart().setTarget(target);
		
		//Lets the given server take the counterpart of this local end point.
		server.takeEndPoint(getRefCounterPart());
	}
	
	//constructor
	/**
	 * Creates a new local end point with the given counterpart.
	 * 
	 * @param counterpart
	 * @throws ArgumentIsNullException if the given counterpart is null.
	 */
	private LocalEndPoint(final LocalEndPoint counterpart) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Checks if the given counterpart is not null.
		Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();
				
		//Sets the counterpart of this local end point.
		this.counterpart = counterpart;
	}
	

	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	public LocalEndPoint getRefCounterPart() {
		return counterpart;
	}
	
	//method
	/**
	 * @return true if this local end point is a net end point.
	 */
	@Override
	public boolean isNetEndPoint() {
		return false;
	}

	//method
	/**
	 * Lets this local send the given message.
	 * 
	 * @throws ArgumentIsNullException if the given message is null.
	 * @throws InvalidArgumentException if this local end point is aborted.
	 */
	@Override
	public void send(final String message) {
		
		//Checks if the given message is not null.
		Validator.assertThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		supposeIsAlive();
		
		counterpart.receiveRawMessage(message);
	}

	//method
	/**
	 * Lets this local end point note an abort.
	 */
	@Override
	protected void noteClose() {}
	
	//method
	/**
	 * Lets the current {@link EndPoint} receive the given message.
	 * 
	 * @param message
	 */
	private void receiveRawMessage(final String message) {
		getRefReceiver().receive(message);
	}
}
