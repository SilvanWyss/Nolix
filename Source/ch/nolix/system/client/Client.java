//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.bases.OptionalSignableElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.core.endPoint5.LocalEndPoint;
import ch.nolix.core.endPoint5.NetEndPoint;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.ClosedStateException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.skillAPI.Closable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A {@link Client} is like an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 740
 * @param <C> The type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>>
extends OptionalSignableElement<C>
implements Closable {
	
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
	
	//optional attribute
	private Session<C> currentSession;
	
	//multi-attribute
	private final List<Session<C>> sessions = new List<Session<C>>();
	
	//method
	/**
	 * Closes the current {@link Client}.
	 */
	public final void close() {
		endPoint.close();
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
	/**
	 * @return the context of the {@link Application} the current {@link Client} belongs to.
	 * @throws InvalidStateException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 * @throws UnexistingAttributeException if the {@link Application},
	 * the current {@link Client} belongs to, has no context.
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
	 * @return the type of the current {@link Client}.
	 */
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is closed.
	 */
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
	 * @throws UnexistingAttributeException if the current {@link Client} contains no next session.
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
	 * @throws UnexistingAttributeException if the current {@link Client} contains no previous session.
	 */
	public final void openPreviousSession() {
		
		supposeContainsPreviousSession();
		
		final var previousSessionIndex = sessions.getIndexOf(internal_getRefCurrentSession()) - 1;		
		this.currentSession = sessions.getRefAt(previousSessionIndex);
		internal_finishSessionInitialization();
	}
	
	//method
	/**
	 * Pops the current session of the current {@link Client}.
	 * 
	 * @InvalidStateException if the current {@link Client} does not contain more than 1 session.
	 * @InvalidStateException if the current session of the current {@link Client} is not the last session.
	 */
	public final void popSession() {
		
		//Checks if the current client contains more than 1 session.
		if (getSessionStackSize() < 2) {
			throw new InvalidStateException(this, "does not contain more than 1 session");
		}
		
		//Checks if the current session of the current client is the last session
		if (internal_getRefCurrentSession() != sessions.getRefLast()) {
			throw
			new InvalidStateException(
				this, "cannot pop a session that is not the last session."
			);
		}
		
		//Removes the last session of the current client.
			final var lastSession = sessions.removeAndGetRefLast();
			lastSession.runProbabalePopFunction();
			lastSession.removeParentClient();
			
		currentSession = sessions.getRefLast();		
		internal_finishSessionInitialization();
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client}.
	 * 
	 * @param session
	 * @throws NullArgumentException if the given session is null.
	 */
	public final void pushSession(final Session<C> session) {
		
		//Checks if the given session is not null.
		Validator.suppose(session).isInstanceOf(Session.class);
		
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
			catch (final ClosedStateException closedStateException) {}
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client} with the given pop function.
	 * 
	 * @param session
	 * @param popFunction
	 * @throws NullArgumentException if the given session is null.
	 * @throws NullArgumentException if the given pop function is null.
	 */
	public final void pushSession(
		final Session<C> session,
		final IFunction popFunction
	) {
		session.setPopFunction(popFunction);
		pushSession(session);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} references a parent application.
	 */
	public final boolean referencesParentApplication() {
		return (parentApplication != null);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the given application.
	 * 
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws InvalidStateException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Application<?> application) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the given application.
		application.takeDuplexController(
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
	 * @throws InvalidStateException if the current {@link Client} is already connected.
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
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidStateException if the current {@link Client} is already connected.
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
	 * @throws InvalidStateException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Server server) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the default application on the given server.
		server.getRefDefaultApplication().takeDuplexController(
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
	 * @throws InvalidStateException if the current {@link Client} is already connected.
	 */
	protected final void internal_connectTo(final Server server, final String name) {
		
		//Creates the duplex controller of the current client.
		internal_setDuplexController(new LocalEndPoint());
		
		//Connects the current client to the application with the given name on the given server.
		server.getRefApplicationByName(name).takeDuplexController(
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
	 * @throws InvalidStateException if the current {@link Client} is already connected.
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
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidStateException if the current {@link Client} is already connected.
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
	protected DocumentNode internal_getData(final Statement request) {
		
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
	protected DocumentNode internal_getDataFromCounterpart(final String request) {
		return endPoint.getData(request);
	}
	
	//method
	/**
	 * @return the {@link Application} the current {@link Client} belongs to.
	 * @throws InvalidStateException if the current {@link Client}
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
	 * @throws InvalidStateException if the current {@link Client} contains no current session.
	 */
	protected final Session<C> internal_getRefCurrentSession() {
		
		//Checks if the current client contains a current session.
		supposeContainsCurrentSession();
		
		return currentSession;
	}
	
	//method
	/**
	 * @return the name of the target application of the current {@link Client}.
	 */
	protected final String internal_getTarget() {
		return endPoint.getTarget();
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
	 * @throws InvalidStateException if the current {@link Client} contains no current session.
	 */
	protected final DocumentNode internal_invokeSessionUserDataMethod(
			final DocumentNodeoid sessionUserDataMethodRequest
	) {
		//Extracts the name of the session user data method.
		final var sessionUserDataMethodName = sessionUserDataMethodRequest.getHeader();
		
		//Extracts the arguments of the given session user data method request.
		final var arguments = sessionUserDataMethodRequest.getRefAttributes().toStringArray();
		
		//Invokes the session user data method.
		return (DocumentNode)internal_getRefCurrentSession().invokeUserDataMethod(
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
	 * @throws InvalidStateException if the current {@link Client} contains no current session.
	 */
	protected final DocumentNode internal_invokeSessionUserDataMethod(
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
	 * @throws InvalidStateException if the current {@link Client} contains no current session.
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
	 * @throws InvalidStateException if the current {@link Client} contains no current session.
	 */
	protected final void internal_invokeSessionUserRunMethod(final DocumentNodeoid sessionUserRunMethodRequest) {
		
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
	protected void internal_run(final Statement command) {
		
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
	protected final void internal_runOnCounterpart(final Statement command) {
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
	 * * 
	 * @param endPoint
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws InvalidStateException if the current {@link Client} is connected.
	 */
	protected final void internal_setDuplexController(final EndPoint endPoint) {
		
		//Checks if the given duplex controller is not null.
		Validator.suppose(endPoint).isInstanceOf(EndPoint.class);
		
		//Checks if the current client is not connected.
		supposeIsNotConnected();
		
		//Sets the duplex controller of the current client.
		this.endPoint = endPoint;	
		
		//Sets the receiver controller of the duplex controller of the current client.
		endPoint.setReceiverController(new ClientReceiverController(this));	
	}
	
	//package-visible method
	/**
	 * Sets the {@link Application} the current {@link Client} will belong to.
	 * 
	 * @param parentApplication
	 * @throws NullArgumentException if the given parent application is null.
	 * @throws InvalidStateException if the current {@link Client}
	 * references already a parent application.
	 */
	final void setParentApplication(final Application<C> parentApplication) {
		
		//Checks if the given parent application is not null.
		Validator
		.suppose(parentApplication)
		.thatIsNamed("parent application")
		.isInstance();
		
		//Checks if the current client does not reference already a parent application.
		if (referencesParentApplication()) {
			throw
			new InvalidStateException(
				this,
				"references already a parent application"
			);
		}
		
		//Sets the parent application of the current client.
		this.parentApplication = parentApplication;
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link Client} contains no current session.
	 */	
	private void supposeContainsCurrentSession() {
		
		//Checks if the current client contains a current session.
		if (!containsCurrentSession()) {
			throw new UnexistingAttributeException(this, "current session");
		}
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link Client} contains no next session.
	 */	
	private void supposeContainsNextSession() {
		
		//Checks if the current client contains a next session.
		if (!containsNextSession()) {
			throw new UnexistingAttributeException(this, "next session");
		}
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link Client} contains no previous session.
	 */	
	private void supposeContainsPreviousSession() {
		
		//Checks if the current client contains a previous session.
		if (!containsPreviousSession()) {
			throw new UnexistingAttributeException(this, "previous session");
		}
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link Client} is connected.
	 */
	private void supposeIsNotConnected() {
		
		//Checks if the current client is not connected.
		if (internal_isConnected()) {
			throw new InvalidStateException(this, "is connected");
		}
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 */
	private void supposeReferencesParentApplication() {
		
		//Checks if the current client references the application it belongs to.
		if (!referencesParentApplication()) {
			throw
			new InvalidStateException(
				this,
				"does not reference a parent application"
			);
		}
	}
}
