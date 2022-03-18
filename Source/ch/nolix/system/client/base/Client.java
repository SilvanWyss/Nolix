//package declaration
package ch.nolix.system.client.base;

//own imports
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
import ch.nolix.core.generalskillapi.IFluentObject;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.net.endpoint3.LocalEndPoint;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;

//class
/**
 * A {@link Client} is an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>> implements GroupCloseable, IFluentObject<C> {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final ClientSessionManager<C> sessionManager = new ClientSessionManager<>(this);
	
	//optional attribute
	/**
	 * The {@link Application} the current {@link Client} belongs to
	 * if the current {@link Client} is a backend client.
	 */
	private Application<C> parentApplication;
	
	//optional attribute
	private EndPoint endPoint;
	
	//TODO: Make this method work for backend and frontend Clients both.
	//method
	/**
	 * @return the name of the parent {@link Application} of the current {@link Client}.
	 */
	public final String getApplicationName() {
		return getParentApplication().getName();
	}
	
	//TODO: Move this method to backend Clients only.
	//method
	/**
	 * @return the {@link Application} of the current {@link Client}.
	 * @throws InvalidArgumentException if
	 * the current {@link Client} does not reference its parent {@link Application}.
	 */
	public final Application<C> getParentApplication() {
		
		assertReferencesParentApplication();
		
		return parentApplication;
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
		return getRefEndPoint().getTarget();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has requested the connection.
	 */
	public final boolean hasRequestedConnection() {
		return getRefEndPoint().hasRequestedConnection();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has a target.
	 */
	public final boolean hasTarget() {
		return getRefEndPoint().hasTarget();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		return getRefEndPoint().isClosed();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a local {@link Client}.
	 */
	public final boolean isLocalClient() {
		return getRefEndPoint().isLocalEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a net {@link Client}.
	 */
	public final boolean isNetClient() {
		return getRefEndPoint().isNetEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a web {@link Client}.
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
	 * @param request
	 * @return the data the given request requests from the counterpart of the current {@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final BaseNode getDataFromCounterpart(final ChainedNode request) {
		return getRefEndPoint().getData(request);
	}
	
	//method declaration
	/**
	 * @param request
	 * @return the data the given request requests from the current {@link Client}.
	 */
	protected abstract Node getDataFromHere(ChainedNode request);
	
	//method
	/**
	 * @return the current {@link Session} of the current {@link Client}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link Client} does not have a current {@link Session}.
	 */
	protected final Session<C> getRefCurrentSession() {
		return sessionManager.getRefCurrentSession();
	}
	
	//method declaration
	/**
	 * Lets the current {@link Client} run the given command.
	 * 
	 * @param command
	 */
	protected abstract void runHere(ChainedNode command);
	
	//method
	/**
	 * Runs the given command on the counterpart of the current {@link Client}.
	 * 
	 * @param command
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final void runOnCounterpart(final ChainedNode command) {
		getRefEndPoint().run(command);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of the current {@link Client}.
	 * 
	 * @param commands
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final void runOnCounterpart(final ChainedNode... commands) {
		getRefEndPoint().run(commands);
	}
	
	//method
	/**
	 * Runs the given commands on the counterpart of the current {@link Client}.
	 * 
	 * @param commands
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final void runOnCounterpart(final Iterable<ChainedNode> commands) {
		getRefEndPoint().run(commands);
	}
	
	//method
	/**
	 * Pops the current {@link Session} of the current {@link Client} from the current {@link Client}.
	 * Closes the current {@link Client} if
	 * the current {@link Session} of the current {@link Client} was
	 * the last {@link Session} of the current {@link Client}.
	 * 
	 * @InvalidArgumentException if
	 * the current {@link Session} of the current {@link Client} is not
	 * the top {@link Session} of the current {@link Client}.
	 */
	final void internalPopCurrentSession() {
		sessionManager.popCurrentSession();
	}
	
	//method
	/**
	 * Pops the current {@link Session} of the current {@link Client} from the current {@link Client}
	 * Forwards the given result.
	 * Closes the current {@link Client} if
	 * the current {@link Session} of the current {@link Client} was
	 * the last {@link Session} of the current {@link Client}.
	 * 
	 * @param result
	 * @InvalidArgumentException if
	 * the current {@link Session} of the current {@link Client} is not
	 * the top {@link Session} of the current {@link Client}.
	 */
	final void internalPopCurrentSessionAndForwardGivenResult(final Object result) {
		sessionManager.popCurrentSessionAndForwardGivenResult(result);
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final void internalPush(final Session<C> session) {
		sessionManager.pushSession(session);
	}
	
	//method
	/**
	 * Pushes the given session to the current {@link Client}.
	 * 
	 * @param session
	 * @param <R> is the type of the returned result.
	 * @return the result from the given session.
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final <R> R internalPushAndGetResult(final Session<C> session) {
		return sessionManager.pushSessionAndGetResult(session);
	}
	
	//method
	/**
	 * Sets the current {@link Session} of the current {@link Client}.
	 * That means the current {@link Session} of the current {@link Client} will
	 * be popped from the current {@link Client} and
	 * the given session will be pushed to the current {@link Client}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	final void internalSetCurrentSession(final Session<C> session) {
		sessionManager.setCurrentSession(session);
	}
	
	//method
	/**
	 * Sets the {@link Application} the current {@link Client} will belong to.
	 * 
	 * @param parentApplication
	 * @throws ArgumentIsNullException if the given parentApplication is null.
	 * @throws InvalidArgumentException if
	 * the current {@link Client} references already its parent {@link Application}.
	 */
	final void internalSetParentApplication(final Application<C> parentApplication) {
		
		//Asserts that the given parent application is not null.
		Validator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();
		
		//Asserts that the current client does not reference its parent application.
		assertDoesNotReferenceParentApplication();
				
		//Sets the parent Application of the current Client.
		this.parentApplication = parentApplication;
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
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given duplex controller is null.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void internalSetEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given duplex controller is not null.
		Validator.assertThat(endPoint).isOfType(EndPoint.class);
		
		//Asserts that the current client is not already connected.
		assertIsNotConnected();
		
		//Sets the duplex controller of the current client.
		this.endPoint = endPoint;
		
		//Sets the receiver controller of the duplex controller of the current client.
		endPoint.setReceiverController(new ClientReceiverController(this));
		
		createCloseDependencyTo(endPoint);
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if
	 * the current {@link Client} references already its parent {@link Application}.
	 */
	private void assertDoesNotReferenceParentApplication() {
		if (referencesParentApplication()) {
			throw new InvalidArgumentException(this, "references already its parent application");
		}
	}
	
	//method
	/**
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	private void assertIsConnected() {
		if (!isConnected()) {
			throw new UnconnectedArgumentException(this);
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	private void assertIsNotConnected() {
		if (isConnected()) {
			throw new InvalidArgumentException(this, "is already connected");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if
	 * the current {@link Client} does not reference its parent {@link Application}.
	 */
	private void assertReferencesParentApplication() {
		if (!referencesParentApplication()) {
			throw new InvalidArgumentException(this, "does not reference its parent application");
		}
	}
	
	/**
	 * @return the {@link EndPoint} of the current {@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	private EndPoint getRefEndPoint() {
		
		assertIsConnected();
		
		return endPoint;
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is connected.
	 */
	private boolean isConnected() {
		return (endPoint != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Client} references its parent {@link Application}.
	 */
	private boolean referencesParentApplication() {
		return (parentApplication != null);
	}
}
