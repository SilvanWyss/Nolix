//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.generalskillapi.ISmartObject;
import ch.nolix.core.generalskillapi.TypeRequestable;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.net.endpoint3.LocalEndPoint;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.programcontrol.sequencer.Sequencer;

//class
/**
 * A {@link Client} is an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>> implements GroupCloseable, ISmartObject<C>, TypeRequestable {
	
	//constants
	protected static final String SESSION_USER_RUN_METHOD_HEADER = "SessionUserRunMethod";
	protected static final String SESSION_USER_DATA_METHOD_HEADER = "SessionUserDataMethod";
	
	//attributes
	private final CloseController closeController = new CloseController(this);
	private EndPoint endPoint;
	private final ClientSessionManager<C> sessionManager = new ClientSessionManager<>(this);
	
	//optional attribute
	/**
	 * The {@link Application} the current {@link Client} belongs to
	 * if the current {@link Client} is on the same machine
	 * as the {@link Application} it belongs to.
	 */
	private Application<C> parentApplication;
	
	//method
	/**
	 * @return true if the current {@link Client} belongs to a {@link Application}.
	 */
	public final boolean belongsToApplication() {
		return (parentApplication != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contains a current session.
	 */
	public final boolean containsCurrentSession() {
		return sessionManager.containsCurrentSession();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contais a next session.
	 */
	public final boolean containsNextSession() {
		return sessionManager.containsNextSession();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} contains a previous session.
	 */
	public final boolean containsPreviousSession() {
		return sessionManager.containsPreviousSession();
	}
	
	//method
	/**
	 * @return the name of the {@link Application} the current {@link Client} belongs to.
	 */
	public final String getApplicationName() {
		return internalGetParentApplication().getName();
	}
	
	//method
	/**
	 * @param <CO> is the type of the context of the {@link Application} of the current {@link Client}.
	 * @return the context of the parent {@link Application} of the current {@link Client} as the given type.
	 * @throws InvalidArgumentException if the current {@link Client} does not belongs to a {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the parent {@link Application} of the current {@link Client}
	 * does not have a context.
	 */
	public final <CO> CO getRefApplicationContext() {
		return internalGetParentApplication().getRefContext();
	}
	
	//method
	/**
	 * @param type
	 * @param <CO> is the given type.
	 * @return the context of the parent {@link Application} of the current {@link Client} as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the current {@link Client} does not belongs to a {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the parent {@link Application} of the current {@link Client}
	 * does not have a context.
	 */
	public final <CO> CO getRefApplicationContextAs(final Class<CO> type) {
		return internalGetParentApplication().getRefContextAs(type);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
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
	 * @return true if the current {@link Client} has requested the connection.
	 */
	public final boolean hasRequestedConnection() {
		return endPoint.hasRequestedConnection();
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
		return endPoint.isLocalEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a net client.
	 */
	public final boolean isNetClient() {
		return endPoint.isNetEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a web{@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	public final boolean isWebClient() {
		return getRefEndPoint().isWebEndPoint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method
	/**
	 * Pops the current {@link Session} from the current {@link Client}.
	 * Closes the current {@link Client} if the current {@link Session} of the current {@link Client}
	 * is the only {@link Session} of the current {@link Client}.
	 * 
	 * @InvalidArgumentException if the current session of the current {@link Client}
	 * is not the top {@link Session} of the current {@link Client}.
	 */
	final void popCurrentSession() {
		sessionManager.popCurrentSession();
	}
	
	//method
	/**
	 * Pops the current {@link Session} from the current {@link Client} with the given result.
	 * Closes the current {@link Client} if the current {@link Session} of the current {@link Client}
	 * is the only {@link Session} of the current {@link Client}.
	 * 
	 * @param result
	 * @InvalidArgumentException if the current session of the current {@link Client}
	 * is not the top {@link Session} of the current {@link Client}.
	 */
	final void popCurrentSession(final Object result) {
		sessionManager.popCurrentSession(result);
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final void push(final Session<C> session) {
		sessionManager.pushSession(session);
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client} with the given pop function.
	 * 
	 * @param session
	 * @param <R> is the type of the returned result.
	 * @return the result from the given session.
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final <R> R pushAndGetResult(final Session<C> session) {
		return sessionManager.pushSessionAndGetResult(session);
	}
	
	//method
	/**
	 * Sets the next session of the current {@link Client}.
	 * That means the current {@link Session} of the current {@link Client}
	 * will be popped from the current{@link Client}
	 * and the given session willl be pushed to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final void setCurrentSession(final Session<C> session) {
		sessionManager.setCurrentSession(session);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final Application<?> application) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new LocalEndPoint());
		
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final int port) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new NetEndPoint(port));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @param name
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final int port, final String name) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new NetEndPoint(port, name));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the default application
	 * on the given server.
	 * 
	 * @param baseServer
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final BaseServer baseServer) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new LocalEndPoint());
		
		//Connects the current client to the default application on the given server.
		baseServer.getRefDefaultApplication().takeEndPoint(
			((LocalEndPoint)endPoint).getRefCounterpart()
		);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on the given server.
	 * 
	 * @param baseServer
	 * @param name
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final BaseServer baseServer, final String name) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new LocalEndPoint());
		
		//Connects the current client to the application with the given name on the given server.
		baseServer.getRefApplicationByName(name).takeEndPoint(
			((LocalEndPoint)endPoint).getRefCounterpart()
		);
	}
	
	//method
	/**
	 * Connects the current {@link Client} to
	 * the default application on the {@link Server#DEFAULT_PORT} on the machine with the given ip.
	 * 
	 * @param ip
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final String ip) {
		internalSetEndPoint(new NetEndPoint(ip));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the default application
	 * on given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(final String ip, final int port) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new NetEndPoint(ip, port));
	}
	
	//method
	/**
	 * Connects the current {@link Client} to the application with the given name
	 * on given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalConnectTo(String ip, int port, String name) {
		
		//Creates the duplex controller of the current client.
		internalSetEndPoint(new NetEndPoint(ip, port, name));
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link Client}.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected Node internalGetData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.SESSION_HEADER:
				return internalGetRefCurrentSession().internalInvokeSessionUserDataMethod(request.getOneAttributeAsNode());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REQUEST, request,"is not valid");
		}
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from the counterpart of the current {@link Client}.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected BaseNode internalGetDataFromCounterpart(final ChainedNode request) {
		return endPoint.getData(request);
	}
	
	//method
	/**
	 * @return the {@link Application} the current {@link Client} belongs to.
	 * @throws InvalidArgumentException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 */
	protected final Application<C> internalGetParentApplication() {
		
		//Asserts that the current client references the application it belongs to.
		supposeReferencesParentApplication();
		
		return parentApplication;
	}
	
	//method
	/**
	 * @return the current session of the current {@link Client}.
	 */
	protected final Session<C> internalGetRefCurrentSession() {
		return sessionManager.getRefCurrentSession();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is connected.
	 */
	protected final boolean internalIsConnected() {
		return (endPoint != null);
	}
	
	//method
	/**
	 * Lets the current {@link Client} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internalRun(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.SESSION_HEADER:
				internalGetRefCurrentSession().run(command.getNextNode());
				break;
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	/**
	 * Runs the given command on the counterpart of the current {@link Client}.
	 * 
	 * @param command
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final void internalRunOnCounterpart(final ChainedNode command) {
		
		assertIsConnected();
		
		endPoint.run(command);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of the current {@link Client}.
	 * 
	 * @param commands
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected void internalRunOnCounterpart(final Iterable<ChainedNode> commands) {
		
		assertIsConnected();
		
		endPoint.run(commands);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of the current {@link Client}.
	 * 
	 * @param commands
	 */
	protected final void internalRunOnCounterpart(final ChainedNode... commands) {
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
	protected final void internalSetEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given duplex controller is not null.
		Validator.assertThat(endPoint).isOfType(EndPoint.class);
		
		//Asserts that the current client is not connected.
		supposeIsNotConnected();
		
		//Sets the duplex controller of the current client.
		this.endPoint = endPoint;
		
		//Sets the receiver controller of the duplex controller of the current client.
		endPoint.setReceiverController(new ClientReceiverController(this));
		
		createCloseDependencyTo(endPoint);
	}
	
	//method
	/**
	 * Waits until the current {@link Client} is connected.
	 */
	protected final void internalWaitUntilIsConnected() {
		Sequencer.waitUntil(this::internalIsConnected);
	}
	
	//method
	/**
	 * Sets the {@link Application} the current {@link Client} will belong to.
	 * 
	 * @param parentApplication
	 * @throws ArgumentIsNullException if the given parent application is null.
	 * @throws InvalidArgumentException if the current {@link Client}
	 * references already a parent application.
	 */
	final void setParentApplication(final Application<C> parentApplication) {
		
		//Asserts that the given parent application is not null.
		Validator
		.assertThat(parentApplication)
		.thatIsNamed("parent application")
		.isNotNull();
		
		//Asserts that the current client does not reference already a parent application.
		if (belongsToApplication()) {
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
	/**
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	private void assertIsConnected() {
		if (!internalIsConnected()) {
			throw new UnconnectedArgumentException(this);
		}
	}
	
	/**
	 * @return the {@link EndPoint} of the current {@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	private EndPoint getRefEndPoint() {
		
		//Asserts that the current Client is connected.
		assertIsConnected();
		
		return endPoint;
	}
			
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client} is connected.
	 */
	private void supposeIsNotConnected() {
		
		//Asserts that the current client is not connected.
		if (internalIsConnected()) {
			throw new InvalidArgumentException(this, "is connected");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client}
	 * does not reference the {@link Application} it belongs to.
	 */
	private void supposeReferencesParentApplication() {
		
		//Asserts that the current client references the application it belongs to.
		if (!belongsToApplication()) {
			throw
			new InvalidArgumentException(
				this,
				"does not reference a parent application"
			);
		}
	}
}
