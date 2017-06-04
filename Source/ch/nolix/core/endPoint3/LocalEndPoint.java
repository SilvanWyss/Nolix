//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.sequencer.Future;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

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
	private final String target;
	private final boolean requestedConnection;
	private final LocalEndPoint counterPart;
	
	public LocalEndPoint(final IEndPointTaker endPointTaker) {
		
		requestedConnection = true;
		target = endPointTaker.getName();
		
		counterPart = new LocalEndPoint(this);
		
		endPointTaker.takeEndPoint(getRefCounterPart());
	}
	
	public LocalEndPoint(final Server server, final String target) {
		
		Validator.supposeThat(target).isNotEmpty();
		
		requestedConnection = true;
		this.target = target;
		
		counterPart = new LocalEndPoint(this);
		
		server.takeEndPoint(getRefCounterPart());
	}
	
	private LocalEndPoint(final LocalEndPoint counterPart) {
		
		//Checks if the given counter part is not null.
		Validator.supposeThat(counterPart).thatIsNamed("counterpart").isNotNull();
		
		requestedConnection = false;
		target = counterPart.getTarget();
		
		//Sets the counter part of htis local end point.
		this.counterPart = counterPart;
	}

	//method
	/**
	 * Lets this local end point send the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 * @throws InvalidStateException if this local end point is aborted.
	 */
	public String sendAndWaitToReply(final String message) {
		
		//Checks if this local end point is not aborted.
		throwExceptionIfAborted();
		
		return getRefCounterPart().receiveAndGetReply(message);
	}
	
	//method
	/**
	 * @return the counterpart of this local end point.
	 */
	private final LocalEndPoint getRefCounterPart() {
		return counterPart;
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
	
	public String sendAndGetReply(String message) {
		
		final Future future = Sequencer.runInBackground(() -> counterPart.receiveAndGetReply(message));
		
		while (future.isRunningJobs()) {
			
		}
		
		return counterPart.receiveAndGetReply(message);
	}
}
