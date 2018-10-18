//package declaration
package ch.nolix.core.endPoint5;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.controllerAPI.IDataProviderController;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A local duplex controller can interact with another local duplex controller.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
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
		target.takeDuplexController(getRefCounterpart());
	}
	
	//constructor
	/**
	 * Creates a new local duplex controller with the given counterpart.
	 * 
	 * @param counterpart
	 * @throws NullArgumentException if the given counterpart is not an instance.
	 */
	private LocalEndPoint(LocalEndPoint counterpart) {
		
		//Sets the reqested connection flag of this local duplex controller.
		requestedConnection = false;
		
		//Checks if the given counterpart is an instance.
		Validator.suppose(counterpart).thatIsNamed("counterpart").isInstance();
		
		//Sets the counterpart of this local duplex controller.
		this.counterpart = counterpart;
		
		//Clears the target of this local duplex controller.
		target = null;
	}
	
	//constructor
	/**
	 * Creates a new local duplex controller with the given counterpart and target.
	 * 
	 * @param counterpart
	 * @param target
	 * @throws NullArgumentException if the given target is not an instance.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	private LocalEndPoint(
		final LocalEndPoint counterpart,
		final String target
	) {
		
		//Sets the requested connection flag of this local duplex controller.
		requestedConnection = false;
		
		//Checks if the given counterpart is an instance.
		Validator.suppose(counterpart).thatIsNamed("counterpart").isInstance();
		
		//Sets the counterpart of this local duplex controller.
		this.counterpart = counterpart;
		
		//Checks if the given target is not null or empty.
		Validator.suppose(target).thatIsNamed("target").isNotEmpty();
		
		//Sets the target of this local duplex controller.
		this.target = target;
	}

	//method
	/**
	 * @return the data the given request requests from this local duplex controller.
	 * @throws UnexistingAttributeException if this local duplex controller has no receiver controller.
	 */
	public DocumentNode getData(final Statement request) {
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
	public String getTarget() {
		return target;
	}

	//method
	/**
	 * @return true if this local duplex controller has requested the conneciton.
	 */
	public boolean hasRequestedConnection() {
		return requestedConnection;
	}
	
	//method
	/**
	 * @return true if this local duplex controller has a target.
	 */
	public boolean hasTarget() {
		return (target != null);
	}

	//method
	/**
	 * @return true if this duplex controller is a net duplex controller.
	 */
	public boolean isNetDuplexController() {
		return false;
	}
	
	//method
	/**
	 * Lets this local duplex controller run the given command.
	 * 
	 * @param command
	 * @throws InvalidStateException if this local duplex controller is aborted.
	 * @throws UnexistingAttributeException if this local duplex controller has no receiver controller.
	 */
	public void run(final Statement command) {
		
		//Checks if this local duplex controller is not aborted.
		supposeIsAlive();
		
		counterpart.getRefReceiverController().run(command);
	}
	
	//method
	/**
	 * Lets this local duplex controller run the given commands.
	 * 
	 * @param commands
	 * @throws InvalidStateException if this local duplex contorller is aborted.
	 * @throws UnexistingAttributeException if this local duplex controller has no receiver controller.
	 */
	protected void run(final List<String> commands) {
		
		//Checks if this local duplex controller is not aborted.
		supposeIsAlive();
		
		final IDataProviderController counterpartReceiverController
		= counterpart.getRefReceiverController();
		
		commands.forEach(c -> counterpartReceiverController.run(c));
	}
}
