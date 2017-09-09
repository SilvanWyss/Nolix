//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.basic.OptionalSignableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.duplexController.LocalDuplexController;
import ch.nolix.core.duplexController.NetDuplexController;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.interfaces.Resettable;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A client is an a connection end point.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 630
 * @param <C> The type of a client.
 */
public abstract class Client<C extends Client<C>>
extends OptionalSignableElement<C>
implements Closable, Resettable {
	
	//commands
	protected static final String INVOKE_RUN_METHOD_COMMAND = "InvokeRunMethod";
	
	//requests
	protected static final String TYPE_REQUEST = "Type";
	protected static final String TARGET_REQUEST = "TargetRequest";
	protected static final String DATA_METHOD_REQUEST = "Data";
		
	//attributes
	private DuplexController duplexController;
	private boolean requestedConnectionFlag;
	
	//optional attributes
	private String target;
	private Session<C> session;
	
	protected void internal_connect(final Application<?> target) {
		requestedConnectionFlag = true;
		this.target = target.getName();
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientReceiverController(this));
		target.createClient(((LocalDuplexController)duplexController).getRefCounterpart());
	}
	
	protected void internal_connect(final DuplexController duplexController) {
		requestedConnectionFlag = false;
		this.duplexController = duplexController;
		duplexController.setReceiverController(new ClientReceiverController(this));
		target = duplexController.getData(TARGET_REQUEST).toString();
	}
	
	protected void internal_connect(String ip, int port, String target) {
		requestedConnectionFlag = true;
		this.target = target;
		duplexController = new NetDuplexController(ip, port);
	}
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application.
	 * 
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 */
	/*
	public Client(final Application<C> targetApplication) {
		
		//1. Sets the request connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Creates the duplex controller of this client.
		controller = new LocalController();
		controller.setReceiverController(new ClientReceiverController(this));
		
		//5. Connects this client to the given application.
		final LocalController duplexController2 = new LocalController();
		((LocalController)controller).connectWith(duplexController2);
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
	/*
	@SuppressWarnings("unchecked")
	public Client(final Application<?> targetApplication, final IElementTakerRunner<C> initializationFunction) {
		
		//1. Sets the request connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Initializes this client.
		initializationFunction.run((C)this);
		
		//5. Creates the duplex controller of this client.
		controller = new LocalController();
		controller.setReceiverController(new ClientReceiverController(this));
		
		//6. Connects this client to the given application.
		final LocalController duplexController2 = new LocalController();
		((LocalController)controller).connectWith(duplexController2);
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
	/*
	public Client(final Application<?> targetApplication, final Session<C> initialSession) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not null.
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotNull();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication.getName();
		
		//4. Creates the duplex controller of this client.
		controller = new LocalController();
		controller.setReceiverController(new ClientReceiverController(this));
		
		//5. Checks if the given initial session is not null.
		Validator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//6. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//7. Connects this client to the given application.
		final LocalController endClientController = new LocalController();
		endClientController.connectWith((LocalController)controller);
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
	 * @param controller
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	/*
	public Client(final Controller controller) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Checks if the given duplex controller is not null.
		Validator.supposeThat(controller).thatIsInstanceOf(Controller.class).isNotNull();
		
		//4. Sets the duplex controller of this client.
		this.controller = controller;
		controller.setReceiverController(new ClientReceiverController(this));
		
		//5. Sends ready signal.
		sendReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Has the given duplex controller.
	 * -Has the given initial session.
	 * 
	 * @param controller
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	/*
	public Client(final Controller controller, final Session<C> initialSession) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Checks if the given duplex controller is not null.
		Validator.supposeThat(controller).thatIsInstanceOf(Controller.class).isNotNull();

		//4. Sets the duplex controller of this client.
		this.controller = controller;
		this.controller.setReceiverController(new ClientReceiverController(this));
		
		//5. Checks if the given initial session is not null.
		Validator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//6. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//7. Initializes the initial session of this client.
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
	 * @param controller
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 * @throws NullArgumentException if the given initialization function is null.
	 */
	/*
	@SuppressWarnings("unchecked")
	public Client(
		final Controller controller,
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
		Validator.supposeThat(controller).thatIsInstanceOf(Controller.class).isNotNull();

		//5. Sets the duplex controller of this client.
		this.controller = controller;
		this.controller.setReceiverController(new ClientReceiverController(this));
		
		//6. Checks if the given initial session is not null.
		Validator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//7. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//8. Initializes the initial session of this client.
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
	/*
	public Client(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not empty.
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Creates the duplex controller of this client.
		controller =	new NetController(ip, port);
		controller.setReceiverController(new ClientReceiverController(this));
		
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
	/*
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
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Initializes this client.
		initializationFunction.run((C)this);
		
		//5. Creates the duplex controller of this client.
		controller =	new NetController(ip, port);
		controller.setReceiverController(new ClientReceiverController(this));
		
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
	/*
	public Client(
		final String ip,
		final int port,
		final String targetApplication,
		final Session<C> initialSession
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnectionFlag = true;
		
		//2. Checks if the given target application is not empty.
		Validator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Checks if the given initial session is not null.
		Validator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//5. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//6. Creates the duplex controller of this client.
		controller =	new NetController(ip, port);
		controller.setReceiverController(new ClientReceiverController(this));
		
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
	 * @return true if this client is aborted.
	 */
	public final boolean isClosed() {
		return internal_getRefDuplexController().isClosed();
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
		Validator.supposeThat(session).thatIsInstanceOf(Session.class).isNotNull();
		
		//Sets the given session to this client.
		session.setClient(this);
		this.session = session;
		
		reset();
		
		//Initializes the given session.
		session.initialize();
		internal_finishSessionInitialization();
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
			case TYPE_REQUEST:
				return new StandardSpecification(getType());
			case TARGET_REQUEST:
				return new StandardSpecification(internal_getTarget());
			case DATA_METHOD_REQUEST:
				return new StandardSpecification(internal_invokeDataMethod(request.getRefOneAttribute()).toString());
			default:
				throw new InvalidArgumentException(new ArgumentName("reqest"), new Argument(request));
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
	protected final String internal_getTarget() {
		
		//Checks if this client has a target application.
		/*
		if !internal_hasTarget()) {
			throw new UnexistingAttributeException(this, "target application");
		}
		*/
		
		return target;
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
		if (!internal_hasSession()) {
			throw new UnexistingAttributeException(this, Session.class);
		}
		
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
		if (!internal_hasSession()) {
			throw new UnexistingAttributeException(this, Session.class);
		}
		
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
				throw new InvalidArgumentException(new ArgumentName("command"), new Argument(command));
		}
	}
}
