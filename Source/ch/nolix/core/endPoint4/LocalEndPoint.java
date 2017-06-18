//package declaration
package ch.nolix.core.endPoint4;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 10
 */
public final class LocalEndPoint<M, R> extends EndPoint<M, R> {

	//attributes
	private final boolean requestedConnection;
	private final LocalEndPoint<M, R> counterpart;
	
	//optional attribute
	private final String target;
	
	//constructor
	/**
	 * Creates new local end point
	 * that will connect to another new local end point.
	 */
	public LocalEndPoint() {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = true;
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint<>(this);
		
		//Clears the target of this local end point.
		target = null;
	}
	
	//constructor
	/**
	 * Creates new local end point that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker<M, R> target) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = true;
		
		//Creates the counterpart of thi local end point.
		counterpart = new LocalEndPoint<M, R>(this);
		
		//Sets the target of this local end point.
		this.target = target.getName();
		
		//Lets the given target take the counterpart of this local end point.
		target.takeEndPoint(getRefCounterpart());
	}
	
	//constructor
	/**
	 * Creates new local end point
	 * that will connect to the given target on the given server.
	 * 
	 * @param server
	 * @param target
	 */
	public LocalEndPoint(final Server<M, R> server, final String target) {
		
		//Sets the requested connection flag of this local end point.		
		requestedConnection = true;
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint<M, R>(this, target);
		
		//Clears the target of this local end point.
		this.target = null;
		
		//Lets the given server take the counterpart of this local end point.
		server.takeEndPoint(getRefCounterpart(), getRefCounterpart().getTarget());
	}
	
	private LocalEndPoint(final LocalEndPoint<M, R> counterPart) {
		
		//Checks if the given counter part is not null.
		Validator
		.supposeThat(counterPart)
		.thatIsNamed("counterpart")
		.isNotNull();
		
		requestedConnection = false;
		target = counterPart.getTarget();
		
		//Sets the counter part of htis local end point.
		this.counterpart = counterPart;
	}
	
	//constructor
	/**
	 * Creates new local end point with the given counterpart and target.
	 * 
	 * @param counterpart
	 * @param target
	 * @throws NullArgumentException if the given counterpart is null.
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	private LocalEndPoint(
		final LocalEndPoint<M, R> counterpart,
		final String target
	) {
		
		//Sets the requested connection flag of this local end point.
		requestedConnection = false;
		
		//Checks if the given counter part is not null.
		Validator
		.supposeThat(counterpart)
		.thatIsNamed("counterpart")
		.isNotNull();
		
		//Sets the counter part of this local end point.
		this.counterpart  = counterpart;
		
		//Checks if the given target is not null or empty.
		Validator
		.supposeThat(target)
		.thatIsNamed("target")
		.isNotEmpty();
		
		//Sets the target of this local end point.
		this.target = target;
	}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	public final LocalEndPoint<M, R> getRefCounterpart() {
		return counterpart;
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
	 * @return true if this local end point has requested the connection.
	 */
	public boolean hasRequestedConnection() {
		return requestedConnection;
	}
	
	//method
	/**
	 * @return true if this local end point has a target.
	 */
	public boolean hasTarget() {
		return (target != null);
	}
	
	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 * @throws InvalidStateException if this local end point is aborted.
	 */
	public R sendAndGetReply(final M message) {
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		return getRefCounterpart().getReply(message);
	}
	
	//method
	/**
	 * @param message
	 * @return the reply to the given message from this local end point.
	 */
	private R getReply(final M message) {
		return getRefReplier().getReply(message);
	}
}
