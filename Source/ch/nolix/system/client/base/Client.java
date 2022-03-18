//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.generalskillapi.IFluentObject;
import ch.nolix.core.net.endpoint3.EndPoint;
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
	
	//optional attribute
	private EndPoint endPoint;
	
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
	
	//method declaration
	/**
	 * @return true if the current {@link Clinet} is a backend {@link Client}.
	 */
	public abstract boolean isBackendClient();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		return getRefEndPoint().isClosed();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link Clinet} is a frontend {@link Client}.
	 */
	public abstract boolean isFrontendClient();
	
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
	 * Sets the {@link EndPoint} of the current {@link Client}.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given endPoint is null.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	final void internalSetEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given endPoint is not null.
		Validator.assertThat(endPoint).thatIsNamed(EndPoint.class).isNotNull();
		
		//Asserts that the current Client is not already connected.
		assertIsNotConnected();
		
		//Sets the EndPoint of the current Client.
		this.endPoint = endPoint;
		
		//Creates a close dependency between the current Client and its EndPoint.
		createCloseDependencyTo(endPoint);
		
		//Sets the receiver controller of the EndPoint of the current Client.
		endPoint.setReceiverController(new ClientDataProviderController(this));
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
}
