//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.basic.OptionalSignableElement;
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.LocalDuplexController;
import ch.nolix.common.duplexController.NetDuplexController;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.ArgumentException;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.functional.IElementTakerRunner;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * A client is an a connection end point.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 660
 * @param <C> - The type of a client.
 */
public abstract class Client<C extends Client<C>>
extends OptionalSignableElement<C>
implements Abortable {
	
	//commands
	protected final static String INVOKE_RUN_METHOD_COMMAND = "InvokeRunMethod";
	protected final static String SET_READY_SIGNAL_COMMAND = "SetReadySignal";
	
	//requests
	protected final static String TYPE_REQUEST = "Type";
	protected final static String TARGET_APPLICATION_REQUEST = "TargetApplicationRequest";
	protected static final String DATA_METHOD_REQUEST = "Data";
		
	//attributes
	private final DuplexController duplexController;
	private final boolean requestedConnectionFlag;
	private boolean receivedReadySignalFlag = false;
	
	//optional attributes
	private final String targetApplication;
	private Session<C> session;
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 */
	public Client(final Application<C> targetApplication) {
		
		//1. Sets the request connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Creates the duplex controller of this client.
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//5. Connects this client to the given application.
		final LocalDuplexController duplexController2 = new LocalDuplexController();
		((LocalDuplexController)duplexController).connectWith(duplexController2);
		targetApplication.createClient(duplexController2);
		
		//6. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @param initializationFunction
	 * @throws NullArgumentException if the given target application is null.
	 * @throws NullArgumentException if the given initialization function is null.
	 */
	@SuppressWarnings("unchecked")
	public Client(final Application<?> targetApplication, final IElementTakerRunner<C> initializationFunction) {
		
		//1. Sets the request connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Initializes this client.
		initializationFunction.run((C)this);
		
		//5. Creates the duplex controller of this client.
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//6. Connects this client to the given application.
		final LocalDuplexController duplexController2 = new LocalDuplexController();
		((LocalDuplexController)duplexController).connectWith(duplexController2);
		targetApplication.createClient(duplexController2);
		
		//7. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Will connect to the given target application.
	 * -Has the given initial session.
	 * 
	 * @param targetApplication
	 * @param initialSession
	 * @throws NullArgumentException if the given target application is null.
	 * @throws NullArgumentException if the givne initial session is null.
	 */
	public Client(final Application<?> targetApplication, final Session<C> initialSession) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Creates the duplex controller of this client.
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//5. Checks if the given initial session is not null.
		ZetaValidator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//6. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//7. Connects this client to the given application.
		final LocalDuplexController endClientController = new LocalDuplexController();
		endClientController.connectWith((LocalDuplexController)duplexController);
		targetApplication.createClient(endClientController);
		
		//8. Waits to the ready signal.
		waitToReadySignal();
		
		//9. Initializes the initial session of this client.
		session.initialize();
		internal_finishSessionInitialization();
	}
	
	//constructor
	/**
	 * Creates new client with the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public Client(final DuplexController duplexController) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Checks if the given duplex controller is not null.
		ZetaValidator.supposeThat(duplexController).thatIsInstanceOf(DuplexController.class).isNotNull();
		
		//4. Sets the duplex controller of this client.
		this.duplexController = duplexController;
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//5. Sends ready signal.
		sendReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Has the given duplex controller.
	 * -Has the given initial session.
	 * 
	 * @param duplexController
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	public Client(final DuplexController duplexController, final Session<C> initialSession) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Checks if the given duplex controller is not null.
		ZetaValidator.supposeThat(duplexController).thatIsInstanceOf(DuplexController.class).isNotNull();

		//4. Sets the duplex controller of this client.
		this.duplexController = duplexController;
		this.duplexController.setReceiverController(new ClientReceiverController(this));
		
		//5. Checks if the given initial session is not null.
		ZetaValidator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//6. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//7. Initializes the inital session of this client.
		session.initialize();
		internal_finishSessionInitialization();
		
		//8. Sends ready signal
		sendReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Has the given duplex controller.
	 * -Has the given initial session.
	 * 
	 * @param duplexController
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 * @throws NullArgumentException if the given initialization function is null.
	 */
	@SuppressWarnings("unchecked")
	public Client(
		final DuplexController duplexController,
		final IElementTakerRunner<C> initializationFunction,
		final Session<C> initialSession
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Initializes this client.
		initializationFunction.run((C)this);
		
		//4. Checks if the given duplex controller is not null.
		ZetaValidator.supposeThat(duplexController).thatIsInstanceOf(DuplexController.class).isNotNull();

		//5. Sets the duplex controller of this client.
		this.duplexController = duplexController;
		this.duplexController.setReceiverController(new ClientReceiverController(this));
		
		//6. Checks if the given initial session is not null.
		ZetaValidator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//7. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//8. Initializes the inital session of this client.
		session.initialize();
		internal_finishSessionInitialization();
		
		//9. Sends ready signal
		sendReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public Client(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not empty.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Creates the duplex controller of this client.
		duplexController =	new NetDuplexController(ip, port);
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//5. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param initializationFunction
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws NullArgumentException if the given initialization function
	 */
	@SuppressWarnings("unchecked")
	public Client(
		final String ip,
		final int port,
		final String targetApplication,
		final IElementTakerRunner<C> initializationFunction
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not empty.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Initializes this client.
		initializationFunction.run((C)this);
		
		//5. Creates the duplex controller of this client.
		duplexController =	new NetDuplexController(ip, port);
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//6. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -That will connect to the given target application on the given port on the machine with the given ip.
	 * -Has the given initial session.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param initialSession
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is an empty string.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	public Client(
		final String ip,
		final int port,
		final String targetApplication,
		final Session<C> initialSession
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not empty.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Checks if the given initial session is not null.
		ZetaValidator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//5. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//6. Creates the duplex controller of this client.
		duplexController =	new NetDuplexController(ip, port);
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//7. Waits to the ready signal.
		waitToReadySignal();
		
		//8. Initializes the initial session of this client.
		session.initialize();
		internal_finishSessionInitialization();
	}
	
	//method
	/**
	 * Aborts this client.
	 * 
	 * @throws RuntimeException if this client is already aborted.
	 */
	public final void abort() {
		duplexController.abort();
	}
	
	//method
	/**
	 * Aborts this client because of the given abort reason.
	 * 
	 * @param abortReason
	 * @throws RuntimeException if this client is already aborted.
	 * @throws NullArgumentException if the given abort reason is null.
	 * @throws EmptyArgumentException if the given abort reason is empty.
	 */
	public final void abort(final String abortReason) {
		internal_getRefDuplexController().abort(abortReason);
	}
	
	//method
	/**
	 * @return the abort reason of this client.
	 * @throws UnexistingAttributeException if this client has no abort reason.
	 */
	public final String getAbortReason() {
		return internal_getRefDuplexController().getAbortReason();
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
	 * @return true if this client is aborted.
	 */
	public final boolean isAborted() {
		return internal_getRefDuplexController().isAborted();
	}

	//method
	/**
	 * @return true if this client is a local client.
	 */
	public final boolean isLocalClient() {
		return internal_getRefDuplexController().isLocalDuplexController();
	}
	
	//method
	/**
	 * @return true if this client is a net client.
	 */
	public final boolean isNetClient() {
		return internal_getRefDuplexController().isNetDuplexController();
	}
	
	//abstract method
	/**
	 * Finishes the initialization of the session of this client.
	 */
	protected abstract void internal_finishSessionInitialization();	
	
	//method
	/**
	 * @return the data the given request requests from this client.
	 * @throws ArgumentException if the given request is not valid.
	 */
	protected Object internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case TYPE_REQUEST:
				return getType();
			case TARGET_APPLICATION_REQUEST:
				return internal_getTargetApplication();
			case DATA_METHOD_REQUEST:
				return internal_invokeDataMethod(request.getRefOneAttribute());
			default:
				throw new ArgumentException(new ArgumentName("reqest"), new Argument(request));
		}
	}
	
	//method
	/**
	 * @return the duplex controller of this client.
	 */
	protected final DuplexController internal_getRefDuplexController() {
		return duplexController;
	}
	
	//method
	/**
	 * @return the target application of this client.
	 * @throws UnexistingAttributeException if this client has no target application.
	 */
	protected final String internal_getTargetApplication() {
		
		//Checks if this client has a target application.
		if (!internal_hasTargetApplication()) {
			throw new UnexistingAttributeException(this, "target application");
		}
		
		return targetApplication;
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
	 * @return true if this client has a target application
	 */
	protected final boolean internal_hasTargetApplication() {
		return (targetApplication != null);
	}
	
	//method
	/**
	 * Invokes a data method of the session of this client.
	 * 
	 * @param dataMethodRequest
	 * @return the data the given data method request requests
	 * @throws UnexistingAttributeException if this client has no session.
	 */
	protected final Object internal_invokeDataMethod(final Specification dataMethodRequest) {
		
		//Checks if this client has a session.
		if (!internal_hasSession()) {
			throw new UnexistingAttributeException(this, Session.class);
		}
		
		//Extracts the name of the data method.
		final String dataMethod = dataMethodRequest.getHeader();
		
		//Extracts the arguments of the given request.
		final List<String> parameters = dataMethodRequest.getRefAttributes().toContainer(a -> a.toString());
		
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
	protected final void internal_invokeRunMethod(final Specification runMethodCommand) {
		
		//Checks if this client has a session.
		if (!internal_hasSession()) {
			throw new UnexistingAttributeException(this, Session.class);
		}
		
		//Extracts the name of the command method.
		final String runMethod = runMethodCommand.getHeader();
		
		//Extracts the arguments of the given command.
		final List<String> arguments = runMethodCommand.getRefAttributes().toContainer(a -> a.toString());
		
		//Invokes the run method with the arguments.
		session.invokeRunMethod(runMethod, arguments);
	}
	
	//method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws ArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case SET_READY_SIGNAL_COMMAND:
				setReceivedReadySignalFlag();
				break;
			case INVOKE_RUN_METHOD_COMMAND:
				internal_invokeRunMethod(command.getRefOneAttribute());
				break;			
			default:
				throw new ArgumentException(new ArgumentName("command"), new Argument(command));
		}
	}
	
	//method
	/**
	 * Sets the session of this client.
	 * 
	 * @param session
	 * @throws NullArgumentException if the given session is null.
	 * @throws RuntimeException if the given session has already a client.
	 */
	protected final void internal_setSessionAndInitializeSession(final Session<C> session) {
		
		//Checks if the given session is not null.
		ZetaValidator.supposeThat(session).thatIsInstanceOf(Session.class).isNotNull();
		
		//Sets the given session to this client.
		session.setClient(this);
		this.session = session;
		
		//Initializes the given session.
		session.initialize();
		internal_finishSessionInitialization();
	}
	
	//method
	/**
	 * Lets this client send the ready signal.
	 */
	private final void sendReadySignal() {
		duplexController.run(SET_READY_SIGNAL_COMMAND);
	}
	
	//method
	/**
	 * Sets the received ready signal flag of this client.
	 */
	private void setReceivedReadySignalFlag() {
		receivedReadySignalFlag = true;
	}
	
	//method
	/**
	 * Lets this client wait to the ready signal.
	 * 
	 * @throws Exception if this client reached its timeout before receiving the ready signal.
	 */
	private final void waitToReadySignal() {
		
		final long time = System.currentTimeMillis();
		
		while (!receivedReadySignalFlag) {			
			if (System.currentTimeMillis() - time > duplexController.getTimeoutInMilliseconds()) {
				throw new RuntimeException("TimeOut");
			}
		}
	}
}
