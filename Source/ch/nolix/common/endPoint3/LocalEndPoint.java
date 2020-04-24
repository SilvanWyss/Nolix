//package declaration
package ch.nolix.common.endPoint3;

import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class LocalEndPoint extends EndPoint {

	//attributes
	private final boolean requestedConnection;
	private final LocalEndPoint counterpart;
	
	//optional attribute
	private final String target;
	
	//constructor
	/**
	 * Creates a new local end point that will connect to an other new local end point.
	 */
	public LocalEndPoint() {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = false;
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
		
		//Clears the target of this local end point.
		target = null;
	}
	
	//constructor
	/**
	 * Creates a new local end point that will connect to the given target
	 * 
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 */
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = true;
	
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this, target.getName());
		
		//Clears the target of this local end point.
		this.target = null;
		
		target.takeEndPoint(getRefCounterpart());
	}
	
	//constructor
	/**
	 * Creates a new local end point
	 * that will connect to the given target on the given server.
	 * 
	 * @param server
	 * @param target
	 */
	public LocalEndPoint(final Server server, final String target) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = true;
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this, target);
		
		this.target = target;
		
		//Lets the given server take the counterpart of this lcoal end point.
		server.takeEndPoint(getRefCounterpart());
	}
	
	//constructor
	/**
	 * Creates a new local end point with the given counterpart.
	 * 
	 * @param counterPart
	 * @throws ArgumentIsNullException if the given counterpart is null.
	 */
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = false;
		
		//Asserts that the given counter part is not null.
		Validator.assertThat(counterPart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterpart = counterPart;
		
		//Clears the target of this local end point.
		target = null;
	}
	
	//constructor
	/**
	 * Creates a new local end point with the given counterpart and target.
	 * 
	 * @param counterpart
	 * @param target
	 * @throws ArgumentIsNullException if the given counterpart is null.
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	private LocalEndPoint(final LocalEndPoint counterpart, final String target) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = false;
		
		//Asserts that the given counter part is not null.
		Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterpart = counterpart;
		
		//Asserts that the given target is not null or empty.
		Validator.assertThat(target).thatIsNamed("target").isNotEmpty();
		
		//Sets the target of this local end point.
		this.target = target;
	}

	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 * @throws ClosedArgumentException if this local end point is closed.
	 */
	public String sendAndWaitToReply(final String message) {
		
		//Asserts that this local end point is open.
		supposeIsOpen();
		
		return getRefCounterpart().receiveAndGetReply(message);
	}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	public final LocalEndPoint getRefCounterpart() {
		return counterpart;
	}

	//method
	/**
	 * Lets this local end point receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 */
	private String receiveAndGetReply(final String message) {
		return getRefReplier().getReply(message);
	}

	//method
	/**
	 * @return the target of this local end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this local end point does not have a target.
	 */
	@Override
	public String getTarget() {
		
		//Asserts that this local end point has a target.
		if (!hasTarget()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "target");
		}
		
		return target;
	}

	//method
	/**
	 * @return true if this local end point has requested the connection.
	 */
	@Override
	public boolean hasRequestedConnection() {
		return requestedConnection;
	}
	
	//method
	/**
	 * @return true if this local end point has a target.
	 */
	@Override
	public boolean hasTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @return the reply to the given message from this local end point.
	 */
	@Override
	public String sendAndGetReply(final String message) {
		return getRefCounterpart().receiveAndGetReply(message);
	}
}
