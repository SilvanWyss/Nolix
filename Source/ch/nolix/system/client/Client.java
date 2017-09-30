//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.bases.OptionalSignableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.duplexController.LocalDuplexController;
import ch.nolix.core.duplexController.NetDuplexController;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.interfaces.Resettable;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.ClosedStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A client is like an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 300
 * @param <C> The type of a client.
 */
public abstract class Client<C extends Client<C>>
extends OptionalSignableElement<C>
implements Closable, Resettable {
	
	//command
	protected static final String INVOKE_RUN_METHOD_COMMAND = "InvokeRunMethod";
	
	//request
	protected static final String DATA_METHOD_REQUEST = "Data";
		
	//attributes
	private DuplexController duplexController;
	private boolean requestedConnectionFlag;
	
	//optional attributes
	private Session<C> session;
	
	//method
	/**
	 * Aborts this client.
	 * 
	 * @throws RuntimeException if this client is already aborted.
	 */
	public final void close() {
		duplexController.close();
	}
	
	//method
	/**
	 * @return the type of this client.
	 */
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return true if this client is closed.
	 */
	public final boolean isClosed() {
		return duplexController.isClosed();
	}

	//method
	/**
	 * @return true if this client is a local client.
	 */
	public final boolean isLocalClient() {
		return duplexController.isLocalDuplexController();
	}
	
	//method
	/**
	 * @return true if this client is a net client.
	 */
	public final boolean isNetClient() {
		return duplexController.isNetDuplexController();
	}
	
	//method
	/**
	 * Sets the session of this client.
	 * 
	 * @param session
	 * @throws NullArgumentException if the given session is null.
	 * @throws InvalidStateException if the given session has already a client.
	 */
	public void setSession(final Session<C> session) {
		
		//Checks if the given session is not null.
		Validator.suppose(session).thatIsInstanceOf(Session.class).isNotNull();
		
		//Handles the option that this client has already a session.
		if (internal_hasSession()) {
			internal_getSession().removeClient();
		}
		
		//Sets the given session to this client.
		session.setClient(this);
		this.session = session;
		
		//Resets this client.
		reset();
		
		try {
			//Initializes the given session.
			session.initialize();
			internal_finishSessionInitialization();
		}
		
		//A client swallows always a closed state exception.
		catch (final ClosedStateException closedStateException) {}
	}
	
	//abstract method
	/**
	 * Finishes the initialization of the session of this client.
	 */
	protected abstract void internal_finishSessionInitialization();	
	
	//method
	/**
	 * @return the data the given request requests from this client.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected StandardSpecification internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case DATA_METHOD_REQUEST:
				return new StandardSpecification(internal_invokeDataMethod(request.getRefOneAttribute()).toString());
			default:
				throw new InvalidArgumentException(new ArgumentName("reqest"), new Argument(request));
		}
	}
	
	protected void internal_connect(final Application<?> target) {
		requestedConnectionFlag = true;
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientSubReceiverController(this));
		target.takeDuplexController(((LocalDuplexController)duplexController).getRefCounterpart());
	}
	
	protected void internal_connectWith(final DuplexController duplexController) {		
		requestedConnectionFlag = false;
		this.duplexController = duplexController;	
		duplexController.setReceiverController(new ClientSubReceiverController(this));	
	}
	
	//method
	/**
	 * Connects this client to the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	protected void internal_connectTo(final int port) {
		
		requestedConnectionFlag = true;
		
		duplexController = new NetDuplexController(port);
		
		getRefDuplexController()
		.setReceiverController(new ClientSubReceiverController(this));	
	}
	
	//method
	/**
	 * Connects this client to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	protected void internal_connectTo(final String ip, final int port) {
		
		requestedConnectionFlag = true;
		
		duplexController = new NetDuplexController(ip, port);
		
		getRefDuplexController()
		.setReceiverController(new ClientSubReceiverController(this));	
	}
	
	protected void internal_connect(String ip, int port, String target) {
		requestedConnectionFlag = true;
		duplexController = new NetDuplexController(ip, port, target);
		duplexController.setReceiverController(new ClientSubReceiverController(this));	
	}
	
	protected StandardSpecification internal_getDataFromCounterpart(final String request) {
		return duplexController.getData(request);
	}
	
	//method
	/**
	 * @return the session of this client
	 * @throws UnexistingAttributeException if this client has no session.
	 */
	protected final Session<C> internal_getSession() {
		
		//Checks if this client has a session.
		internal_supposeHasSession();
		
		return session;
	}
	
	//method
	/**
	 * @return the target of this client.
	 * @throws UnexistingAttributeException if this client has no target.
	 */
	protected final String internal_getTarget() {
		return duplexController.getTarget();
	}
	
	//method
	/**
	 * @return true if this client has requested the connection
	 */
	protected final boolean internal_hasRequestedConnection() {
		return requestedConnectionFlag;
	}
	
	//method
	/**
	 * @return true if this client has a session.
	 */
	protected boolean internal_hasSession() {
		return (session != null);
	}
	
	//method
	/**
	 * Invokes a data method of the session of this client.
	 * 
	 * @param dataMethodRequest
	 * @return the data the given data method request requests
	 * @throws UnexistingAttributeException if this client has no session.
	 */
	protected final Object internal_invokeDataMethod(final StandardSpecification dataMethodRequest) {
		
		//Checks if this client has a session.
		internal_supposeHasSession();
		
		//Extracts the name of the data method.
		final String dataMethod = dataMethodRequest.getHeader();
		
		//Extracts the arguments of the given request.
		final List<String> parameters = dataMethodRequest.getRefAttributes().to(a -> a.toString());
		
		//Invokes the data method with the arguments and returns the result.
		return session.invokeDataMethod(dataMethod, parameters);
	}
	
	//method
	/**
	 * Invokes a run method of the session of this client.
	 * 
	 * @param runMethodCommand
	 * @throws UnexistingAttributeException if this client has no session.
	 */
	protected final void internal_invokeRunMethod(final StandardSpecification runMethodCommand) {
		
		//Checks if this client has a session.
		internal_supposeHasSession();
		
		//Extracts the name of the command method.
		final String runMethod = runMethodCommand.getHeader();
		
		//Extracts the arguments of the given command.
		final List<String> arguments = runMethodCommand.getRefAttributes().to(a -> a.toString());
		
		//Invokes the run method with the arguments.
		session.invokeRunMethod(runMethod, arguments);
	}
	
	//method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case INVOKE_RUN_METHOD_COMMAND:
				internal_invokeRunMethod(command.getRefOneAttribute());
				break;			
			default:
				throw new InvalidArgumentException(
					new ArgumentName("command"),
					new Argument(command)
				);
		}
	}
	
	//method
	/**
	 * Runs the given command on the counterpart of this client.
	 * 
	 * @param command
	 */
	protected void internal_runOnCounterpart(final String command) {
		duplexController.run(command);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of this clients.
	 * 
	 * @param commands
	 */
	protected void internal_runOnCounterpart(final String... commands) {
		duplexController.appendCommand(commands);
		duplexController.runAppendedCommands();
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if this client has no session.
	 */
	protected void internal_supposeHasSession() {
		if (!internal_hasSession()) {
			throw new UnexistingAttributeException(this, "session");
		}
	}
	
	//method
	/**
	 * @return the duplex controller of this Client.
	 */
	private DuplexController getRefDuplexController() {
		return duplexController;
	}
}
