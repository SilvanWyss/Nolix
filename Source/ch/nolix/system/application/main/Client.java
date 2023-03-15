//package declaration
package ch.nolix.system.application.main;

//Java imports
import java.util.HashMap;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * A {@link Client} is an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>> implements GroupCloseable {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private EndPoint endPoint;
	
	//multi-attribute
	private final HashMap<String, String> sessionVariables = new HashMap<>();
	
	//method
	/**
	 * @param key
	 * @return true if the current {@link Client} contains a session variable with the given key, false otherwise.
	 */
	public final boolean containsSessionVariableWithKey(final String key) {
		return sessionVariables.containsKey(key);
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
	 * @param key
	 * @return the value of the session variable with the given key from the current {@link Client}.
	 * @throws ArgumentDoesNotContainElementException if
	 * the current {@link Client} does not contain a session variable with the given key.
	 */
	public final String getSessionVariableValueByKey(final String key) {
		
		final var value = sessionVariables.get(key);
		
		if (value == null) {
			throw
			ArgumentDoesNotContainElementException.forArgumentAndElement(
				this,
				"session variable with the key + '" + key + "'"
			);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return the name of the target {@link Application} of the current {@link Client}.
	 */
	public final String getTarget() {
		return getRefEndPoint().getCustomTargetSlot();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has requested the connection.
	 */
	public final boolean hasRequestedConnection() {
		return getRefEndPoint().isFrontendEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} has a target.
	 */
	public final boolean hasTarget() {
		return getRefEndPoint().hasCustomTargetSlot();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link Client} is a backend {@link Client}.
	 */
	public abstract boolean isBackendClient();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		/*
		 * The end point of the current Client can be requested only when the current Client is connected.
		 */
		return (isConnected() && getRefEndPoint().isClosed());
	}
	
	//method declaration
	/**
	 * @return true if the current {@link Client} is a frontend {@link Client}.
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
		return getRefEndPoint().isSocketEndPoint();
	}
	
	//method
	/**
	 * @return true if the current {@link Client} is a web {@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	public final boolean isWebClient() {
		return getRefEndPoint().isWebSocketEndPoint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method
	/**
	 * Sets a session variable with the given key and value to the current {@link Client}.
	 * 
	 * Will overwrite a previous session variable if
	 * the current {@link Client} contains already a session variable with the given key.
	 * 
	 * @param key
	 * @param value
	 */
	public final void setSessionVariableWithKeyAndValue(final String key, final String value) {
		sessionVariables.put(key, value);
	}
	
	//method
	/**
	 * @return the current {@link Client} as concrete {@link Client}.
	 */
	@SuppressWarnings("unchecked")
	protected final C asConcrete() {
		return (C)this;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from the counterpart of the current {@link Client}.
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final BaseNode<?> getDataFromCounterpart(final ChainedNode request) {
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
	 * @return true if the current {@link Client} is connected.
	 */
	protected final boolean isConnected() {
		return (endPoint != null);
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
	 * @param command
	 * @param commands
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	protected final void runOnCounterpart(final ChainedNode command, final ChainedNode... commands) {
		getRefEndPoint().run(command, commands);
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
	 * @throws ClosedArgumentException if the current {@link Client} is closed.
	 */
	void internalAssertIsOpen() {
		if (isClosed()) {
			throw ClosedArgumentException.forArgument(this);
		}
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
		GlobalValidator.assertThat(endPoint).thatIsNamed(EndPoint.class).isNotNull();
		
		//Asserts that the current Client is not already connected.
		assertIsNotConnected();
		
		//Sets the EndPoint of the current Client.
		this.endPoint = endPoint;
		
		//Creates a close dependency between the current Client and its EndPoint.
		createCloseDependencyTo(endPoint);
		
		//Sets the receiver controller of the EndPoint of the current Client.
		endPoint.setReceivingDataProviderController(new ClientDataProviderController(this));
	}
	
	//method
	/**
	 * @throws UnconnectedArgumentException if the current {@link Client} is not connected.
	 */
	private void assertIsConnected() {
		if (!isConnected()) {
			throw UnconnectedArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	private void assertIsNotConnected() {
		if (isConnected()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already connected");
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
}
