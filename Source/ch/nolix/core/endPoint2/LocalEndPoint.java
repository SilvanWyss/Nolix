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
	
	//attributes
	private final String target;
	private final LocalEndPoint counterpart;

	//constructor
	/**
	 * Creates new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Calls consturctor of the base class.
		super(true);
		
		//Sets the target of this local end point.
		this.target = target.getName();
		
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
		
		//Checks if the given target is not null or empty.
		Validator.supposeThat(target).thatIsNamed("target").isNotEmpty();
		
		//Sets the target of this local end point.
		this.target = target;
		
		//Creates the counter part of this local end point.
		counterpart = new LocalEndPoint(this);
		
		server.takeIncomingEndPoint(counterpart);
	}
	
	//constructor
	/**
	 * Creates new local end point.
	 */
	private LocalEndPoint(LocalEndPoint localEndPoint) {
		
		//Calls constructor of the base class.
		super(false);
		
		this.target = localEndPoint.getTarget();
		
		this.counterpart = localEndPoint;
	}
	
	//method
	/**
	 * @return the target of this local end point.
	 */
	public String getTarget() {
		return target;
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
	
	//method
	/**
	 * Lets this local end point receive the given message.
	 * 
	 * @param message
	 * @throws InvalidArgumentException if this local end point is aborted.
	 */
	private void receive(final String message) {
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		getRefReceiver().receive(message);
	}
}
