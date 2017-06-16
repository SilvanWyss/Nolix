//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.ILevel2Controller;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public final class LocalDuplexController extends DuplexController {
	
	//attribute
	private final LocalDuplexController counterpart;
	private boolean aborted = false;
	
	//optional attribute
	private String abortReason;
	
	//constructor
	/**
	 * Creates new local duplex controller
	 * that will be connected to an other new local duplex controller.
	 */
	public LocalDuplexController() {
		
		//Creates the counterpart of this local duplex controller.
		this.counterpart = new LocalDuplexController(this);
	}
	
	//constructor
	/**
	 * Creates new local duplex controller with the given counterpart.
	 * 
	 * @param counterpart
	 * @throws NullArgumentException if the given counterpart is null.
	 */
	private LocalDuplexController(LocalDuplexController counterpart) {
		
		//Checks if the given counterpart is not null.
		Validator.supposeThat(counterpart).thatIsNamed("counterpart").isNotNull();
		
		//Sets the counterpart of this local duplex controller.
		this.counterpart = counterpart;
	}
	
	//method
	/**
	 * Aborts this local duplex controller.
	 * 
	 * @throws InvalidStateException if this local duplex controller is already aborted.
	 */
	public void abort00() {
		
		//Checks if this local duplex controller is not aborted.
		throwExceptionIfAborted();
		
		aborted = true;
	}
	
	//method
	/**
	 * Aborts this local duplex controller because of the given abort reason.
	 * 
	 * @param abortReason
	 * @throws NullArgumentException if the given abort reason is null.
	 * @throws EmptyArgumentException if the given abort reason is empty.
	 * @throws InvalidStateException if this local duplex controller is already aborted.
	 */
	public void abort00(final String abortReason) {
		
		//Checks if the given abort reason is not null or empty.
		Validator.supposeThat(abortReason).isNotEmpty();
		
		abort();
		
		//Sets the abort reason of this local duplex controller.
		this.abortReason = abortReason;
	}
	
	//method
	/**
	 * @return the abort reason of this local duplex controller.
	 * @throws InvalidStateException if this local duplex controller is not aborted.
	 * @throws UnexistingAttributeException if this local duplex controller has no abort reason.
	 */
	public final String getAbortReason00() {
		
		//Checks if this local duplex controller is aborted.
		if (!isAborted()) {
			throw new InvalidStateException(this, "is not aborted");
		}
		
		//Checks if this local duplex controller has an abort reason.
		if (!hasAbortReason()) {
			throw new UnexistingAttributeException(this, "abort reason");
		}
		
		return abortReason;
	}
	
	//method
	/**
	 * @return the data the given request requests from this local duplex controller.
	 * @throws UnexistingAttributeException if this local duplex controller has no receiver controller.
	 */
	public Specification getData(final Statement request) {
		return counterpart.getRefReceiverController().getData(request);
	}
	
	//method
	/**
	 * @return true if this local duplex controller has an abort reason
	 */
	public boolean hasAbortReason00() {
		return (abortReason != null);
	}
	
	//method
	/**
	 * @return true if this local duplex controller is aborted.
	 */
	public final boolean isAborted00() {
		return aborted;
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
		throwExceptionIfAborted();
		
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
		throwExceptionIfAborted();
		
		final ILevel2Controller counterpartReceiverController
		= counterpart.getRefReceiverController();
		
		commands.forEach(c -> counterpartReceiverController.run(c));
	}

	@Override
	protected void noteAbort() {
		// TODO Auto-generated method stub
		
	}
}
