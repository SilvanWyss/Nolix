//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.controllerapi.IDataProviderController;

//class
/**
 * A local duplex controller can interact with another local duplex controller.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class LocalEndPoint extends EndPoint {
	
	//attributes
	private final boolean requestedConnection;
	private final LocalEndPoint counterpart;
	
	//optional attribute
	private final String target;
	
	//constructor
	/**
	 * Creates a new local duplex controller
	 * that will connect to another new local duplex controller.
	 */
	public LocalEndPoint() {
		
		//Sets the reqested connection flag of this local duplex controller.
		requestedConnection = true;
		
		//Creates the counterpart of this local duplex controller.
		this.counterpart = new LocalEndPoint(this);
		
		//Clears the target of this local duplex controller.
		target = null;
	}
	
	//constructor
	/**
	 * Creates a new local duplex controller that will connect to the given target.
	 * 
	 * @param target
	 */
	public LocalEndPoint(final IEndPointTaker target) {
		
		//Sets the reqested connection flag of this local duplex controller.
		requestedConnection = true;
		
		//Creates the counterpart of this local duplex controller.
		this.counterpart = new LocalEndPoint(this, target.getName());
		
		//Clears the target of this local duplex controller.
		this.target = null;
		
		//Lets the given target take the counterpart of this local duplex controller.
		target.takeEndPoint(getRefCounterpart());
	}
	
	//constructor
	/**
	 * Creates a new local duplex controller with the given counterpart.
	 * 
	 * @param counterpart
	 * @throws ArgumentIsNullException if the given counterpart is null.
	 */
	private LocalEndPoint(LocalEndPoint counterpart) {
		
		//Sets the reqested connection flag of this local duplex controller.
		requestedConnection = false;
		
		//Asserts that the given counterpart is not null.
		Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counterpart of this local duplex controller.
		this.counterpart = counterpart;
		
		createCloseDependencyTo(counterpart);
		
		//Clears the target of this local duplex controller.
		target = null;
	}
	
	//constructor
	/**
	 * Creates a new local duplex controller with the given counterpart and target.
	 * 
	 * @param counterpart
	 * @param target
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	private LocalEndPoint(
		final LocalEndPoint counterpart,
		final String target
	) {
		
		//Sets the requested connection flag of this local duplex controller.
		requestedConnection = false;
		
		//Asserts that the given counterpart is not null.
		Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counterpart of this local duplex controller.
		this.counterpart = counterpart;
		
		//Asserts that the given target is not null or empty.
		Validator.assertThat(target).thatIsNamed("target").isNotEmpty();
		
		//Sets the target of this local duplex controller.
		this.target = target;
	}

	//method
	/**
	 * @return the data the given request requests from this local duplex controller.
	 * @throws ArgumentDoesNotHaveAttributeException if this local duplex controller does not have a receiver controller.
	 */
	@Override
	public Node getData(final ChainedNode request) {
		return counterpart.getRefReceiverController().getData(request);
	}
	
	//method
	/**
	 * @return the counterpart of this local duplex controller.
	 */
	public LocalEndPoint getRefCounterpart() {
		return counterpart;
	}
	
	//method
	/**
	 * @return the target of this local duplex controller.
	 */
	@Override
	public String getTarget() {
		return target;
	}

	//method
	/**
	 * @return true if this local duplex controller has requested the conneciton.
	 */
	@Override
	public boolean hasRequestedConnection() {
		return requestedConnection;
	}
	
	//method
	/**
	 * @return true if this local duplex controller has a target.
	 */
	@Override
	public boolean hasTarget() {
		return (target != null);
	}

	//method
	/**
	 * @return true if this duplex controller is a net duplex controller.
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
	 * Lets this local duplex controller run the given command.
	 * 
	 * @param command
	 * @throws ClosedArgumentException if this local duplex controller is closed.
	 * @throws ArgumentDoesNotHaveAttributeException if this local duplex controller does not have a receiver controller.
	 */
	@Override
	public void run(final ChainedNode command) {
		
		//Asserts that this local duplex controller is not aborted.
		assertIsOpen();
		
		counterpart.getRefReceiverController().run(command);
	}
	
	//method
	/**
	 * Lets this local duplex controller run the given commands.
	 * 
	 * @param commands
	 * @throws ClosedArgumentException if this local duplex contorller is closed.
	 * @throws ArgumentDoesNotHaveAttributeException if this local duplex controller does not have a receiver controller.
	 */
	@Override
	public void run(final Iterable<ChainedNode> commands) {
		
		//Asserts that this local duplex controller is open.
		assertIsOpen();
		
		final IDataProviderController counterpartReceiverController = counterpart.getRefReceiverController();
		
		commands.forEach(counterpartReceiverController::run);
	}
}
