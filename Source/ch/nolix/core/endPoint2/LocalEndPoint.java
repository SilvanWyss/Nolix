//package declaration
package ch.nolix.core.endPoint2;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 120
 */
public final class LocalEndPoint extends EndPoint {
	
	//attribute
	private final LocalEndPoint counterpart;
	
	//constructor
	/**
	 * Creates new local end point that will connect to an other new local end point.
	 */
	public LocalEndPoint() {
		
		//Calls constructor of the base class.
		super(true);
		
		//Sets the target of this local end point.
		setTarget(DEFAULT_TARGET);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
	}
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker target	) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Sets the target of this local end point.
		setTarget(target.getName());
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
		
		target.takeEndPoint(counterpart);
	}
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given target on the given server.
	 * 
	 * @param server
	 * @param target
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public LocalEndPoint(final Server server, final String target) {
		
		//Calls constructor of the base class.
		super(true);
		
		//Sets the target of this local end point.
		setTarget(target);
		
		//Creates the counter part of this local end point.
		counterpart = new LocalEndPoint(this);
		
		server.takeEndPoint(counterpart);
	}
	
	//constructor
	/**
	 * Creates new local end point the given local end point will connect to.
	 * 
	 * @param localEndPoint
	 */
	private LocalEndPoint(final LocalEndPoint localEndPoint) {
		
		//Calls constructor of the base class.
		super(false);
		
		//Sets the target of this local end point.
		setTarget(localEndPoint.getTarget());
		
		//Sets the counterpart of this local end point.
		counterpart = localEndPoint;
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
	public boolean isNetEndPoint() {
		return false;
	}

	//method
	/**
	 * Lets this local send the given message.
	 * 
	 * @throws NullArgumentException if the given message is null.
	 * @throws InvalidArgumentException if this local end point is aborted.
	 */
	public void send(final String message) {
		
		//Checks if the given message is not null.
		Validator.supposeThat(message).thatIsNamed("message").isNotNull();
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		counterpart.receive(message);	
	}
}
