//package declaration
package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.processproperty.ConnectionOrigin;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
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
		super(ConnectionOrigin.REQUESTED_CONNECTION);
		
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
		super(ConnectionOrigin.REQUESTED_CONNECTION);
		
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
	 * @param baseServer
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public LocalEndPoint(final BaseServer baseServer, final String target) {
		
		//Calls constructor of the base class.
		super(ConnectionOrigin.REQUESTED_CONNECTION);
		
		//Creates the counterpart of this local end point.
		counterpart = new LocalEndPoint(this);
		
		//Sets the target of the counterpart of this local end point.
		getRefCounterPart().setTarget(target);
		
		//Lets the given server take the counterpart of this local end point.
		baseServer.takeEndPoint(getRefCounterPart());
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
		super(ConnectionOrigin.ACCEPTED_CONNECTION);
		
		//Asserts that the given counterpart is not null.
		Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();
		
		//Creates a close dependency from the current LocalEndPoint to the given counterpart.
		createCloseDependencyTo(counterpart);
		
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
	 * {@inheritDoc}
	 */
	public EndPointType getType() {
		return EndPointType.LOCAL;
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWebEndPoint() {
		return false;
	}
	
	//method
	/**
	 * Lets this local send the given message.
	 * 
	 * @param message
	 * @throws ArgumentIsNullException if the given message is null.
	 * @throws ClosedArgumentException if this local end point is closed.
	 */
	public void send(final String message) {
		
		//Asserts that the given message is not null.
		Validator.assertThat(message).thatIsNamed("message").isNotNull();
		
		//Asserts that this local end point is open.
		assertIsOpen();
		
		counterpart.receiveRawMessage(message);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteClose() {}
	
	//method
	/**
	 * Lets the current {@link EndPoint} receive the given message.
	 * 
	 * @param message
	 */
	private void receiveRawMessage(final String message) {
		getRefReceiver().run(message);
	}
}
