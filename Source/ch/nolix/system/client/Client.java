//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.attributeAPI.OptionalLabelable;
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.endPoint5.EndPoint;
import ch.nolix.common.endPoint5.LocalEndPoint;
import ch.nolix.common.endPoint5.NetEndPoint;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.generalSkillAPI.TypeRequestable;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.ClosedArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.skillAPI.OptionalClosable;
import ch.nolix.common.validator.Validator;

//abstract class
/**
 * A {@link Client} is like an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 790
 * @param <C> The type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>>
implements OptionalClosable, OptionalLabelable<C>, ISmartObject<C>, TypeRequestable {
	
	//constants
	protected static final String SESSION_USER_RUN_METHOD_HEADER = "SessionUserRunMethod";
	protected static final String SESSION_USER_DATA_METHOD_HEADER = "SessionUserDataMethod";
	
	//attribute
	private EndPoint endPoint;
	
	//optional attribute
	/**
	 * The {@link Application} the current {@link Client} belongs to
	 * if the current {@link Client} is on the same machine
	 * as the {@link Application} it belongs to.
	 */
	private Application<C> parentApplication;
	
	//optional attributes
	private Session<C> currentSession;
	private String infoString;
	
	//multi-attribute
	private final List<Session<C>> sessions = new List<>();
	
	//method
	/**
	 * Closes the current {@link Client}.
	 */
	@Override
	public void close() {
		
		endPoint.close();
		
	    if (parentApplication != null) {
	    	parentApplication.removeClient(this);
	    }
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contains a current session.
	 */
	public final boolean containsCurrentSession() {
		return (currentSession != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contais a next session.
	 */
	public final boolean containsNextSession() {
		
		if (!containsCurrentSession()) {
			return false;
		}
		
		if (getCurrentSessionIndex() == getSessionStackSize()) {
			return false;
		}
		
		return true;
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contains a previous session.
	 */
	public final boolean containsPreviousSession() {
		
		if (!containsCurrentSession()) {
			return false;
		}
		
		if (getCurrentSessionIndex() < 2) {
			return false;
		}
		
		return true;
	}
	
	//method
	public final int getCurrentSessionIndex() {
		return sessions.getIndexOf(internal_getRefCurrentSession());
	}
	
	//method
	@Override
	public final String getInfoString() {
		
		if (infoString == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.INFO_STRING);
		}
		
		return infoString;
	}
	
	//method
	/**
	 * @return the context of the {@link Application} the current {@link Client} belongs to.
	 * @throws InvalidArgumentException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 * @throws ArgumentDoesNotHaveAttributeException if the {@link Application},
	 * the current {@link Client} belongs to, does not have a context.
	 */
	public final Object getRefApplicationContext() {
		return internal_getParentApplication().getRefContext();
	}
	
	//method
	/**
	 * @return the number of sessions on the session stack of the current {@link Client}.
	 */
	public final int getSessionStackSize() {
		return sessions.getSize();
	}
	
	//method
	/**
	 * @return the name of the target {@link Application} of the current {@link Client}.
	 */
	public final String getTarget() {
		return endPoint.getTarget();
	}

	//method
	/**
	 * @return the type of the current {@link Client}.
	 */
	@Override
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasInfoString() {
		return (infoString != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has a target.
	 */
	public final boolean hasTarget() {
		return endPoint.hasTarget();
	}

	//method
	/**
	 * @return true if the current {@link Client} is closed.
	 */
	@Override
	public final boolean isClosed() {
		return endPoint.isClosed();
	}

	//method
	/**
	 * @return true if the current {@link Client} is a local client.
	 */
	public final boolean isLocalClient() {
		return endPoint.isLocalDuplexController();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a net client.
	 */
	public final boolean isNetClient() {
		return endPoint.isNetDuplexController();
	}
	
	//method
	/**
	 * Opens the next session of the current {@link Client}.
	 * 
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Client} does not contain a next session.
	 */
	public final void openNextSession() {
		
		supposeContainsNextSession();
		
		final var nextSessionIndex = sessions.getIndexOf(internal_getRefCurrentSession()) + 1;
		this.currentSession = sessions.getRefAt(nextSessionIndex);
		internal_finishSessionInitialization();
	}
	
	//method
	/**
	 * Opens the previous session of the current {@link Client}.
	 * 
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Client} does not contain a previous session.
	 */
	public final void openPreviousSession() {
		
		supposeContainsPreviousSession();
		
		final var previousSessionIndex = sessions.getIndexOf(internal_getRefCurrentSession()) - 1;
		this.currentSession = sessions.getRefAt(previousSessionIndex);
		internal_finishSessionInitialization();
	}
	
	//TODO: Make this method not public.
	//method
	/**
	 * Pushes the given session to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final void push(final Session<C> session) {
		
		//Checks if the given session is not null.
		Validator.suppose(session).isOfType(Session.class);
		
		//Sets the given session to the current {@link Client}.
		session.setParentClient(asConcreteType());
		sessions.addAtEnd(session);
		currentSession = session;
		
		//Initializes the given session.
			try {
				session.initialize();
				internal_finishSessionInitialization();
			}
			
			//A client swallows always a closed state exception.
			catch (final ClosedArgumentException closedStateException) {}
	}
	
	//method
	/**
	 * @return true if the current {@link Client} references a parent application.
	 */
	public final boolean referencesParentApplication() {
		return (parentApplication != null);
	}
	
	//method
	@Override
	public final C removeInfoString() {
		
		infoString = null;
		
		return asConcreteType();
	}
	
	//method
	@Override
	public final C setInfoString(final String infoString) {
		
		this.infoString
		= Validator.suppose(infoString).thatIsNamed(VariableNameCatalogue.INFO_STRING).isNotBlank().andReturn();
		
		return asConcreteType();
	}
	
	//package-visible method
	/**
	 * Pops the current {@link Session} from the current {@link Client}.
	 */
	final void popCurrentSession() {
		popCurrentSessionFirstPart();
		internal_getRefCurrentSession().internal_cleanForInitialization();
		internal_getRefCurrentSession().initialize();
		internal_finishSessionInitialization();
	}

	//package-visible method
	/**
	 * Pops the current {@link Session} from the current {@link Client} with the given result.
	 * 
	 * @param result
	 * @InvalidArgumentException if the current {@link Client} does not contain more than 1 session.
	 * @InvalidArgumentException if the current session of the current {@link Client} is not the last session.
	 */
	final void popCurrentSession(final Object result) {
		internal_getRefCurrentSession().setResult(result);
		popCurrentSessionFirstPart();
	}

	//method
	/**
	 * Pushes the given session to the current {@link Client} with the given pop function.
	 * 
	 * @param session
	 * @param popFunction
	 * @throws ArgumentIsNullException if the given session is null.
	 * @throws ArgumentIsNullException if the given pop function is null.
	 */
	@SuppressWarnings("unchecked")
	final <R> R pushAndGetResult(final Session<C> session, final Class<R> resultType) {
		
		push(session);
		
		Sequencer.waitUntil(() -> isClosed() || !session.belongsToClient());
		
		if (isClosed()) {
			throw new ClosedArgumentException(this);
		}
		
		return (R)session.getRefResult();
	}
	
	//package-visible method
	/**
	 * Sets the next session of the current {@link Client}.
	 * That means the current {@link Session} of the current {@link Client}
	 * will be popped from the current{@link Client}
	 * and the given session willl be pushed to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final void setNext(final Session<C> session) {
		popCurrentSession();
		push(session);
	}

	//method
	/**
	 * Connects the current {@link Client} to the given application.
	 * 
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Application<?> application) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the given application.
		application.takeEndPoint(
			((LocalEndPoint)endPoint).getRefCounterpart()
		);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the default application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final int port) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new NetEndPoint(port));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final int port, final String name) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new NetEndPoint(port, name));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the default application
	 * on the given server.
	 * 
	 * @param server
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Server server) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the default application on the given server.
		server.getRefMainApplication().takeEndPoint(
			((LocalEndPoint)endPoint).getRefCounterpart()
		);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on the given server.
	 * 
	 * @param server
	 * @param name
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Server server, final String name) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the application with the given name on the given server.
		server.getRefApplicationByName(name).takeEndPoint(
			((LocalEndPoint)endPoint).getRefCounterpart()
		);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the default application
	 * on given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final String ip, final int port) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new NetEndPoint(ip, port));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(String ip, int port, String name) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new NetEndPoint(ip, port, name));
	}
	
	//abstract method
	/**
	 * Finishes the initialization of the session of the current {@link Client}.
	 */
	protected abstract void internal_finishSessionInitialization();
	
	//method
	/**
	 * @return the data the given request requests from the current {@link Client}.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected Node internal_getData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case SESSION_USER_DATA_METHOD_HEADER:
				return internal_invokeSessionUserDataMethod(request.getRefOneAttribute());
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.REQUEST, request,"is not valid");
		}
	}
	
	//method
	/**
	 * @return the data the given request
	 * requests from the counterpart of the current {@link Client}.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected Node internal_getDataFromCounterpart(final String request) {
		return endPoint.getData(request);
	}
	
	//method
	/**
	 * @return the {@link Application} the current {@link Client} belongs to.
	 * @throws InvalidArgumentException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 */
	protected final Application<C> internal_getParentApplication() {
		
		//Checks if the current client references the application it belongs to.
		supposeReferencesParentApplication();
		
		return parentApplication;
	}
	
	//method
	/**
	 * @return the current session of the current {@link Client}.
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final Session<C> internal_getRefCurrentSession() {
		
		Sequencer.waitUntil(() -> containsCurrentSession());
		
		//Checks if the current client contains a current session.
		supposeContainsCurrentSession();
		
		return currentSession;
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has requested the connection.
	 */
	protected final boolean internal_hasRequestedConnection() {
		return endPoint.hasRequestedConnection();
	}
	
	//method
	/**
	 * Invokes the user data method of the current session of the current {@link Client},
	 * the given session user data method request requests.
	 * 
	 * @param sessionUserDataMethodRequest
	 * @return the data the invoked user data method returns.
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final Node internal_invokeSessionUserDataMethod(
			final BaseNode sessionUserDataMethodRequest
	) {
		//Extracts the name of the session user data method.
		final var sessionUserDataMethodName = sessionUserDataMethodRequest.getHeader();
		
		//Extracts the arguments of the given session user data method request.
		final var arguments = sessionUserDataMethodRequest.getRefAttributes().toStringArray();
		
		//Invokes the session user data method.
		return internal_getRefCurrentSession().invokeUserDataMethod(
			sessionUserDataMethodName, arguments
		);
	}
	
	//method
	/**
	 * Invokes the user data method of the current session of the current {@link Client},
	 * that has the given name,
	 * with the given arguments.
	 * 
	 * @param name
	 * @param arguments
	 * @return the data the invoked user data method returns.
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final Node internal_invokeSessionUserDataMethod(
		final String name,
		final String... arguments
	) {
		return internal_getRefCurrentSession().invokeUserDataMethod(name, arguments);
	}

	//method
	/**
	 * Invokes the user run method of the current session of the current {@link Client},
	 * that has the given name,
	 * with the given arguments.
	 * 
	 * @param name
	 * @param arguments
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final void internal_invokeSessionUserRunMethod(
		final String name,
		final String... arguments
	) {
		internal_getRefCurrentSession().invokeUserRunMethod(name, arguments);
	}
	
	//method
	/**
	 * Invokes the user run method of the current session of the current {@link Client}
	 * the given session user run method request requests.
	 * 
	 * @param name
	 * @param arguments
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final void internal_invokeSessionUserRunMethod(final BaseNode sessionUserRunMethodRequest) {
		
		//Extracts the name of the session user run method.
		final String sessionUserRunMethodName = sessionUserRunMethodRequest.getHeader();
		
		//Extracts the arguments of the given session user run method request.
		final var arguments = sessionUserRunMethodRequest.getRefAttributes().toStringArray();
		
		//Extracts the session user run method.
		internal_invokeSessionUserRunMethod(sessionUserRunMethodName, arguments);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is connected.
	 */
	protected final boolean internal_isConnected() {
		return (endPoint != null);
	}
	
	//method
	/**
	 * Lets the current {@link Client} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case SESSION_USER_RUN_METHOD_HEADER:
				internal_invokeSessionUserRunMethod(command.getRefOneAttribute());
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	/**
	 * Runs the given command on the counterpart of the current {@link Client}.
	 * 
	 * @param command
	 */
	protected final void internal_runOnCounterpart(final ChainedNode command) {
		endPoint.run(command);
	}
	
	//method
	/**
	 * Runs the given command on the counterpart of the current {@link Client}.
	 * 
	 * @param command
	 */
	protected final void internal_runOnCounterpart(final String command) {
		endPoint.run(command);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of the current {@link Client}.
	 * 
	 * @param commands
	 */
	protected final void internal_runOnCounterpart(final String... commands) {
		endPoint.appendCommand(commands);
		endPoint.runAppendedCommands();
	}
	
	//method
	/**
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given duplex controller is null.
	 * @throws InvalidArgumentException if the current {@link Client} is connected.
	 */
	protected final void internal_setDuplexController(final EndPoint endPoint) {
		
		//Checks if the given duplex controller is not null.
		Validator.suppose(endPoint).isOfType(EndPoint.class);
		
		//Checks if the current client is not connected.
		supposeIsNotConnected();
		
		//Sets the duplex controller of the current client.
		this.endPoint = endPoint;
		
		//Sets the receiver controller of the duplex controller of the current client.
		endPoint.setReceiverController(new ClientReceiverController(this));
	}
	
	//method
	/**
	 * Waits until the current {@link Client}  is connected.
	 */
	protected final void internal_waitUntilIsConnected() {
		Sequencer.waitUntil(() -> this.internal_isConnected());
	}
	
	//package-visible method
	/**
	 * Sets the {@link Application} the current {@link Client} will belong to.
	 * 
	 * @param parentApplication
	 * @throws ArgumentIsNullException if the given parent application is null.
	 * @throws InvalidArgumentException if the current {@link Client}
	 * references already a parent application.
	 */
	final void setParentApplication(final Application<C> parentApplication) {
		
		//Checks if the given parent application is not null.
		Validator
		.suppose(parentApplication)
		.thatIsNamed("parent application")
		.isNotNull();
		
		//Checks if the current client does not reference already a parent application.
		if (referencesParentApplication()) {
			throw
			new InvalidArgumentException(
				this,
				"references already a parent application"
			);
		}
		
		//Sets the parent application of the current client.
		this.parentApplication = parentApplication;
	}
	
	//method
	private void popCurrentSessionFirstPart() {
		
		//Checks if the current Client contains more than 1 Session.
		if (getSessionStackSize() < 2) {
			throw new InvalidArgumentException(this, "does not contain more than 1 Session");
		}
		
		//Checks if the current Session of the current Client is the top Session of the current Client.
		if (internal_getRefCurrentSession() != sessions.getRefLast()) {
			throw
			new InvalidArgumentException(
				this, "cannot pop a session that is not the last session."
			);
		}
		
		//Removes the top Session of the current Client.
		final var topSession = sessions.removeAndGetRefLast();
		topSession.removeParentClient();
		currentSession = sessions.getRefLast();
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Client} does not contain a current session.
	 */	
	private void supposeContainsCurrentSession() {
		
		//Checks if the current client contains a current session.
		if (!containsCurrentSession()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "current session");
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Client} does not contain a next session.
	 */	
	private void supposeContainsNextSession() {
		
		//Checks if the current client contains a next session.
		if (!containsNextSession()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "next session");
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Client} does not contain a previous session.
	 */	
	private void supposeContainsPreviousSession() {
		
		//Checks if the current client contains a previous session.
		if (!containsPreviousSession()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "previous session");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client} is connected.
	 */
	private void supposeIsNotConnected() {
		
		//Checks if the current client is not connected.
		if (internal_isConnected()) {
			throw new InvalidArgumentException(this, "is connected");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 */
	private void supposeReferencesParentApplication() {
		
		//Checks if the current client references the application it belongs to.
		if (!referencesParentApplication()) {
			throw
			new InvalidArgumentException(
				this,
				"does not reference a parent application"
			);
		}
	}
}
