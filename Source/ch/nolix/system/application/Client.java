/*
 * file:	Client.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	590
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.basic.OptionalSignableElement;
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.LocalDuplexController;
import ch.nolix.common.duplexController.NetDuplexController;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * A client is a connection end point.
 */
public abstract class Client<C extends Client<C>>
extends OptionalSignableElement<C>
implements Abortable
{	
	//attributes
	private DuplexController duplexController;
	final boolean requestedConnection;
	private boolean receivedReadySignal = false;
	
	//optional attributes
	Session<?> session;
	private final String targetApplication;
	
	//constructor
	/**
	 * Creates new client that will connect to the given application.
	 * 
	 * @param application
	 */
	protected Client(final Application<C> application) {
		
		//1. Sets the request connection flag of this client.
		requestedConnection = true;
		
		//2. Sets the target application of this client.
		targetApplication = application.getName();
		
		//3. Creates the duplex controller of this client.
		duplexController = new LocalDuplexController(new ClientReceiverController(this));
		
		//4. Connects this client to the given application.
		application.createClient(new LocalDuplexController(duplexController));
		
		//5. Lets this client wait to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client with the given setup object that will connect to the given application.
	 * 
	 * @param setupObject
	 * @param application
	 */
	protected Client(Object setupObject, Application<?> application) {
		
		requestedConnection = true;
		
		//1. clearing targetApplication
		targetApplication = null;
		
		//2. setup
		this.initialize(setupObject);
		
		//3. setting duplex controller
		duplexController = new LocalDuplexController();
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//4. connecting
		LocalDuplexController endClientController = new LocalDuplexController();
		endClientController.connectWith((LocalDuplexController)duplexController);
		application.createClient(endClientController);
		
		//5. waiting to ready signal
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Has the given initial session.
	 * -Will connect to the given application.
	 * 
	 * @param application
	 * @param initialSession
	 */
	protected Client(
		final Application<?> application,
		final Session<?> initialSession
	) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnection = true;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Creates the duplex controller of this client.
		duplexController =
		new LocalDuplexController()
		.setReceiverController(new ClientReceiverController(this));
		
		//4. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//4. connecting
		LocalDuplexController endClientController = new LocalDuplexController();
		endClientController.connectWith((LocalDuplexController)duplexController);
		application.createClient(endClientController);
		
		//5. waiting to ready signal
		waitToReadySignal();
		
		//6. initial session initializing
		session.initialize();
		completeRunMethod();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Will connect to the given target application on the given port on the machine with the given ip.
	 * -Will be initialized with the given initializing object.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param initializingObject
	 */
	protected Client(
		final String ip,
		final int port,
		final String targetApplication,
		final Object initializingObject
	) {
		//1. Sets the requested connection flag.
		requestedConnection = true;
		
		//2. Checks if the given target application is not null and no empty string.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Initializes this client.
		initialize(initializingObject);
		
		//5. Creates the duplex controller of this client.
		duplexController =
		new NetDuplexController(ip, port)
		.setReceiverController(new ClientReceiverController(this));
		
		//6. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is an empty string.
	 */
	protected Client(
		final String ip,
		final int port,
		final String targetApplication
	) {
		//1. Sets the requested connection flag of this client.
		requestedConnection = true;
		
		//2. Checks if the given target application is not null and no empty string.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Creates the duplex controller of this client.
		duplexController = new NetDuplexController(ip, port, new ClientReceiverController(this));
		
		//5. Waits to the ready signal.
		waitToReadySignal();
	}
	
	//constructor
	/**
	 * Creates new client that:
	 * -Has the given initial session.
	 * -That will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param initialSession
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is an empty string.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	protected Client(
		final String ip,
		final int port,
		final String targetApplication,
		final Session<?> initialSession
	) {
		//1. Sets the requested connection flag of this client.
		requestedConnection = true;
		
		//2. Checks if the given target application is not null and no empty string.
		ZetaValidator.supposeThat(targetApplication).thatIsNamed("target application").isNotEmpty();
		
		//3. Sets the target application of this client.
		this.targetApplication = targetApplication;
		
		//4. Checks if the given initial session is not null.
		ZetaValidator.supposeThat(initialSession).thatIsNamed("initial session").isNotNull();
		
		//5. Sets the initial session of this client.
		session = initialSession;
		session.setClient(this);
		
		//6. Creates the duplex controller of this client.
		duplexController = new NetDuplexController(ip, port, new ClientReceiverController(this));
		
		//7. Waits to the ready signal.
		waitToReadySignal();
		
		//8. Initializes the initial session of this client.
		session.initialize();
		completeRunMethod();
	}
	
	//package-visible constructor
	protected Client(final DuplexController duplexController) {
		
		//1. Sets the requested connection flag of this client.
		requestedConnection = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Sets the duplex controller of this client.
		this.duplexController = duplexController;
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//4. Sends the ready signal.
		sendReadySignal();
	}
	
	//package-visible constructor
	protected Client(DuplexController duplexController, Session<?> initialSession) {
		
		requestedConnection = false;
		
		//1. clearing target application
		targetApplication = null;
		
		//2. setting initial session & initializing initial session
		session = initialSession;
		session.setClient(this);
		
		//3. setting duplex controller
		this.duplexController = duplexController;
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//4.sending ready signal	
		sendReadySignal();
	
		//5.
		session.initialize();
		completeRunMethod();
	}
	
	//constructor
	protected Client(Object object, DuplexController duplexController, Session<?> initialSession) {
		
		//1. Sets the requested connection flag.
		requestedConnection = false;
		
		//2. Clears the target application of this client.
		targetApplication = null;
		
		//3. Setups.
		initialize(object);
		
		//4. setting initial session & initializing initial session
		session = initialSession;
		session.setClient(this);
		
		//5. setting duplex controller
		this.duplexController = duplexController;
		duplexController.setReceiverController(new ClientReceiverController(this));
		
		//6.sending ready signal	
		sendReadySignal();
		
		//temp
		receivedReadySignal = true;
	
		//5.
		session.initialize();
		completeRunMethod();
	}
	
	//method
	/**
	 * @return the stop reason of this stoppable object
	 * @throws Exception if this client has no stop reason
	 */
	public final String getAbortReason() {
		return getRefDuplexController().getAbortReason();
	}
	
	public void invoke(String command) {
		getRefDuplexController().run(ClientProtocol.INVOKE_ON_ORIGIN_MACHINE_COMMAND + "(" + createUpdateSpecification() + "," + command + ")");
	}
	
	//method
	public void invokeOnOriginMachine(String command) {
		getRefDuplexController().run(ClientProtocol.INVOKE_COMMAND + "(" + createUpdateSpecification() + "," + command + ")");
	}

	//method
	/**
	 * @return true if this client is a local client
	 */
	public final boolean isLocalClient() {
		return getRefDuplexController().isLocalDuplexController();
	}
	
	//method
	/**
	 * @return true if this client is a net client
	 */
	public final boolean isNetClient() {
		return getRefDuplexController().isNetDuplexController();
	}
	
	//method
	/**
	 * @return true if this client is stopped
	 */
	public final boolean isAborted() {
		return getRefDuplexController().isAborted();
	}
	
	//try
	/**
	 * Sets the session of this client.
	 * 
	 * @param session
	 * @throws Exception if:
	 * -The given session is null.
	 * -The given session has already a client
	 */
	public void setSession(final Session<C> session) {
		
		//Checks the given session.
		Validator.throwExceptionIfValueIsNull("session", session);
		
		if (hasRequestedConnection()) {
			
		}
		
		session.setClient(this);
		this.session = session;
		
		session.initialize();
		completeRunMethod();
	}
	
	//method
	/**
	 * Stops this client.
	 * 
	 * @throws Exception if this client is stopped already
	 */
	public final void abort() {
		duplexController.abort();
	}
	
	//method
	/**
	 * Stops this client  because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if this client is stopped already
	 */
	public final void abort(String stopReason) {
		getRefDuplexController().abort(stopReason);
	}
	
	//abstract method
	/**
	 * @return newly created update specification for the origin machine of this client
	 */
	protected abstract Specification createUpdateSpecification();
		
	//method
	/**
	 * @return the data the given request requests from the origin machine
	 */
	protected Object getData(Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case ClientProtocol.TYPE_REQUEST:
				return getType();
			case ClientProtocol.TARGET_APPLICATION_REQUEST:
				return getTargetApplication();
			default:
				
				//Extracts the name of the data method.
				final String dataMethod = request.getHeader();
				
				//Extracts the arguments of the data method.
				final List<String> parameters = request.getRefAttributes().toContainer(a -> a.toString());
				
				//Invokes the data method with the arguments and returns the result.
				return session.invokeDataMethod(dataMethod, parameters);
		}
	}
	
	//method
	/**
	 * @return the duplex controller of this client
	 */
	protected final DuplexController getRefDuplexController() {
		return duplexController;
	}
	
	//method
	/**
	 * @return the session of this client
	 */
	protected final Session<?> getRefSession() {
		return session;
	}
	
	//method
	/**
	 * @return the target application of this client
	 * @throws Exception if this client has no target application
	 */
	protected final String getTargetApplication() {
		
		if (!hasTargetApplication()) {
			throw new UnexistingAttributeException(this, "target application on origin machine");
		}
		
		return targetApplication;
	}
	
	//method
	/**
	 * @return the type of this client
	 */
	protected final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return true if this client has requested the connection
	 */
	protected final boolean hasRequestedConnection() {
		return requestedConnection;
	}
	
	//method
	/**
	 * @return true if this client has a target application
	 */
	protected final boolean hasTargetApplication() {
		return (targetApplication != null);
	}
	
	//method
	/**
	 * Setups this client.
	 * 
	 * @param initializingObject
	 */
	protected abstract void initialize(Object initializingObject);
	
	//package-visible method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	protected void run(Statement command) {	

		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ClientProtocol.SET_READY_SIGNAL_COMMAND:
				receivedReadySignal = true;
				break;
			case ClientProtocol.INVOKE_COMMAND:
				
				update(command.getRefAttributes().getRefAt(1));

				final Specification runCommand = command.getRefAttributes().getRefAt(2);
				
				//Extracts the name of the command method.
				final String runMethod = runCommand.getHeader();
				
				//Extracts the arguments of the given command.
				final List<String> arguments = runCommand.getRefAttributes().toContainer(a -> a.toString());
				
				//Invokes the command method with the arguments.
				getRefSession().invokeRunMethod(runMethod, arguments);
				
				completeRunMethod();
				
				break;
			case ClientProtocol.INVOKE_ON_ORIGIN_MACHINE_COMMAND:
				update(command.getRefAttributes().getRefAt(1));
				invokeOnOriginMachine(command.getRefAttributes().getRefAt(2).toString());
				break;
			case ClientProtocol.UPDATE_COMMAND:
				update(command.getRefOneAttribute());
				break;
			default:	
				
				throw new InvalidArgumentException(command);
		}
	}
	
	//abstract method
	/**
	 * Updates this client using the given update specification.
	 * 
	 * @param updateSpecification
	 */
	protected abstract void update(Specification updateSpecification);
	
	//method
	/**
	 * Completes a run method of this client.
	 */
	private final void completeRunMethod() {
		getRefDuplexController().appendCommand(ClientProtocol.UPDATE_COMMAND + "(" + createUpdateSpecification() + ")");
		getRefDuplexController().runAppendedCommands();
	}
	
	//method
	/**
	 * Lets this client send the ready signal.
	 */
	private final void sendReadySignal() {
		duplexController.run(ClientProtocol.SET_READY_SIGNAL_COMMAND);
	}
	
	//method
	/**
	 * Lets this client wait to the ready signal.
	 * 
	 * @throws Exception if this client reached its timeout while waiting to the ready signal
	 */
	private final void waitToReadySignal() {
		
		final long time = System.currentTimeMillis();
		
		while (!receivedReadySignal) {			
			if (System.currentTimeMillis() - time > duplexController.getTimeoutInMilliseconds()) {
				throw new RuntimeException("TimeOut");
			}
		}
	}
}
