//package declaration
package ch.nolix.system.client.base;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.client.baseguiclient.BaseBackGUIClient;

//class
/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 470
 * @param <C> is the type of the client of a {@link Session}.
 */
public abstract class Session<C extends Client<C>> {
	
	//attribute
	private C parentClient;
	
	//optional attribute
	private Object result;
	
	//multi-attributes
	private final LinkedList<Method> runMethods = new LinkedList<>();
	private final LinkedList<Method> dataMethods = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Session}.
	 */
	public Session() {
		
		//Extracts the user run methods and the user data methods of the current session.
		//Iterates the methods of the current Session.
		Class<?> lClass = getClass();
		while (lClass != null) {
			for (final var m : lClass.getMethods()) {
				if (SessionHelper.isRunMethod(m)) {
					
					SessionHelper.validateRunMethod(m);
					
					//Setting the method accessible is needed that it can be accessed.
					m.setAccessible(true);
					
					runMethods.addAtEnd(m);
				} else if (SessionHelper.isDataMethod(m)) {
					
					SessionHelper.validateDataMethod(m);
					
					//Setting the method accessible is needed that it can be accessed.
					m.setAccessible(true);
					
					dataMethods.addAtEnd(m);
				}
			}
			
			lClass = lClass.getSuperclass();
		}
	}
	
	//method
	/**
	 * @return true if the current {@link Session} belongs to a client.
	 */
	public final boolean belongsToClient() {
		return (parentClient != null);
	}
	
	//method
	/**
	 * @return the name of the parent {@link Application} of the parent {@link Client} of the current {@link Session}.
	 */
	public final String getApplicationName() {
		return getParentClient().getApplicationName();
	}
	
	//method
	/**
	 * @return the parent {@link Application} of the parent {@link Client} of the current {@link Session}.
	 */
	public Application<C> getParentApplication() {
		return getParentClient().internalGetParentApplication();
	}
	
	//method
	/**
	 * @return the parent client of the current {@link Session}.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a client.
	 */
	public final C getParentClient() {
		
		//Asserts that the current {@link Session} belonts to a client.
		supposeBelongsToClient();
		
		return parentClient;
	}
	
	//method
	/**
	 * @param <CO> is the type of the given application context.
	 * @return the context of the parent {@link Application}
	 * of the parent {@link Client} of the current {@link Session}.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a {@link Client}.
	 * @throws InvalidArgumentException if the parent {@link Client} of the current {@link Session}
	 * does not belong to a {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the {@link Application}
	 * of the parent {@link Client} of the current {@link Session} does not have a context.
	 */
	public final <CO> CO getRefApplicationContext() {
		return getParentClient().getRefApplicationContext();
	}
	
	//method
	/**
	 * @param type
	 * @param <CO> is the type of the application context of the parent {@link Application} of the current {@link Session}.
	 * @return the context of the parent {@link Application}
	 * of the parent {@link Client} of the current {@link Session} as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a {@link Client}.
	 * @throws InvalidArgumentException if the parent {@link Client} of the current {@link Session}
	 * does not belong to a {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the {@link Application}
	 * of the parent {@link Client} of the current {@link Session} does not have a context.
	 */
	public final <CO> CO getRefApplicationContextAs(final Class<CO> type) {
		return getParentClient().getRefApplicationContextAs(type);
	}
	
	//method
	/**
	 * Pops the current {@link Session} from its parent {@link Client}.
	 */
	public final void pop() {
		getParentClient().popCurrentSession();
	}
	
	//method
	/**
	 * Pops the current {@link Session} from its parent {@link Client} with the given result.
	 * 
	 * @param result
	 * @throws ArgumentIsNullException if the given result is null.
	 */
	public final void pop(final Object result) {
		getParentClient().popCurrentSession(result);
	}
	
	//method
	/**
	 * Pushes the given session to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final void push(final Session<C> session) {
		getParentClient().push(session);
	}
	
	//method
	/**
	 * Pushes the given session to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @param <R> is the type of the returned result.
	 * @return the result from the given session.
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final <R> R pushAndGetResult(final Session<C> session) {
		return getParentClient().pushAndGetResult(session);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given command locally.
	 * 
	 * @param command
	 */
	public void runLocally(final String command) {
		internalInvokeSessionUserRunMethod(Node.fromString(command));
		updateCounterpart();
	}
	
	//method
	/**
	 * Sets the next session of the parent {@link Client} of the current {@link Session}.
	 * That means the current {@link Session} will be popped from its parent {@link Client}
	 * and the given session is pushed to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final void setNext(final Session<C> session) {
		getParentClient().setCurrentSession(session);
	}
	
	//method declaration
	/**
	 * Initializes the current {@link Session}.
	 */
	protected abstract void initialize();
	
	//method declaration
	/**
	 * Initializes the current {@link Session} for the first time.
	 */
	protected abstract void initializeForFirstTime();
	
	//method
	/**
	 * @return the {@link Client} class of the current {@link Session}.
	 */
	protected abstract Class<C> internalGetRefClientClass();
	
	//method
	/**
	 * Invokes the user data method of the current session of the current {@link Client},
	 * the given session user data method request requests.
	 * 
	 * @param sessionUserDataMethodRequest
	 * @return the data the invoked user data method returns.
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final Node internalInvokeSessionUserDataMethod(
			final BaseNode sessionUserDataMethodRequest
	) {
		//Extracts the name of the session user data method.
		final var sessionUserDataMethodName = sessionUserDataMethodRequest.getHeader();
		
		//Extracts the arguments of the given session user data method request.
		final var arguments = sessionUserDataMethodRequest.getRefAttributes().toStringArray();
		
		//Invokes the session user data method.
		return invokeUserDataMethod(sessionUserDataMethodName, arguments);
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
	protected final Node internalInvokeSessionUserDataMethod(
		final String name,
		final String... arguments
	) {
		return invokeUserDataMethod(name, arguments);
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
	protected final void internalInvokeSessionUserRunMethod(
		final String name,
		final String... arguments
	) {
		invokeUserRunMethod(name, arguments);
	}
	
	//method
	/**
	 * Invokes the user run method of the current session of the current {@link Client}
	 * the given session user run method request requests.
	 * 
	 * @param sessionUserRunMethodRequest
	 * @throws InvalidArgumentException if the current {@link Client} does not contain a current session.
	 */
	protected final void internalInvokeSessionUserRunMethod(final BaseNode sessionUserRunMethodRequest) {
		
		//Extracts the name of the session user run method.
		final String sessionUserRunMethodName = sessionUserRunMethodRequest.getHeader();
		
		//Extracts the arguments of the given session user run method request.
		final var arguments = sessionUserRunMethodRequest.getRefAttributes().toStringArray();
		
		//Extracts the session user run method.
		internalInvokeSessionUserRunMethod(sessionUserRunMethodName, arguments);
	}
	
	//method
	/**
	 * Lets the current {@link Session} run the given command.
	 * 
	 * @param command
	 */
	protected final void run(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.RUN_METHOD_HEADER:
				internalInvokeSessionUserRunMethod(command.getOneAttributeAsNode());
				break;
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method declaration
	/**
	 * Updates the counterpart of the {@link Client} of the current {@link Session}.
	 */
	protected abstract void updateCounterpart();

	Node getData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.DATA_METHOD_HEADER:
				return internalInvokeSessionUserDataMethod(request.getOneAttributeAsNode());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REQUEST, request,"is not valid");
		}
	}
	
	final Object getRefResult() {
		
		if (result == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.RESULT);
		}
		
		return result;
	}
	
	//method
	/**
	 * Invokes the user data method of the current {@link Session},
	 * that has the given name,
	 * with the given arguments.
	 * 
	 * @param name
	 * @param arguments
	 * @return the data the given data method returns for the given parameters.
	 * @throws RuntimeException if an error occurs.
	 */
	final Node invokeUserDataMethod(String name, String... arguments) {
		try {
			return
			(Node)
			getUserDataMethod(name).invoke(this, (Object[])arguments);
		} catch (final Exception exception) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	/**
	 * Invokes the user run method of the current {@link Session},
	 * that has the given name,
	 * with the given arguments.
	 * 
	 * @param name
	 * @param arguments
	 * @throws RuntimeException if an error occurs.
	 */
	final void invokeUserRunMethod(final String name, final String... arguments) {
		try {
			getUserRunMethod(name).invoke(this, (Object[])arguments);
		} catch (final Exception exception) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	/**
	 * Removes the parent client of the current {@link Session}.
	 */
	final void removeParentClient() {
		parentClient = null;
	}
	
	//method
	/**
	 * Sets the parent client of the current {@link Session}.
	 * 
	 * @param parentClient
	 * @throws ArgumentIsNullException if the given parent client is null.
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	final void setParentClient(C parentClient) {
		
		//Asserts that the given client is not null.
		Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
		
		//Asserts that the current session does not belong to a client.
		suppoeDoesNotBelongToClient();
						
		//Sets the parent client of the current session.
		this.parentClient = parentClient;
		
		//Initializes the current Session the first time.
		initializeForFirstTime();
	}
	
	//method
	final void setResult(final Object result) {
		
		Validator.assertThat(result).thatIsNamed(LowerCaseCatalogue.RESULT).isNotNull();
		
		this.result = result;
	}
	
	//method
	/**
	 * @param name
	 * @return the user data method with the given name from the current {@link Session}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Session} does not contain a user data method with the given name.
	 */
	private Method getUserDataMethod(final String name) {
		return dataMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the user run method with the given name from the current {@link Session}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Session} does not contain a user run method with the given name.
	 */
	private Method getUserRunMethod(final String name) {
		return runMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a client.
	 */
	private void supposeBelongsToClient() {
		
		//Asserts that the current {@link Session} belongs to a client.
		if (!belongsToClient()) {
			throw new InvalidArgumentException(this, "does not belong to a client");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	private void suppoeDoesNotBelongToClient() {
		
		//Asserts that the current {@link Session} does not belong to a client.
		if (belongsToClient()) {
			throw new InvalidArgumentException(this, "belongs to a client");
		}
	}
}
