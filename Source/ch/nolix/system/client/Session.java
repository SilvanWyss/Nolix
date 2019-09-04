//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflection.MethodHelper;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.baseGUIClient.BaseBackGUIClient;

//abstract class
/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 260
 * @param <C> The type of the client of a {@link Session}.
 */
public abstract class Session<C extends Client<C>> {
	
	//attribute
	private C parentClient;
	
	//optional attribute
	private Object result;
	
	//multi-attributes
	private final List<Method> runMethods = new List<>();
	private final List<Method> dataMethods = new List<>();
	
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
				if (
					Character.isUpperCase(m.getName().charAt(0))
					&& MethodHelper.allParametersOfMethodAreStrings(m)
				) {
					
					if (m.getAnnotation(RunMethod.class) != null) {
					
						//TODO: Check signature of the current method.
						
						//Setting the method accessible is needed that it can be accessed.
						m.setAccessible(true);
						
						runMethods.addAtEnd(m);
					}
					
					else if (m.getAnnotation(DataMethod.class) != null) {
						
						//TODO: Check signature of the current method.
						
						//Setting the method accessible is needed that it can be accessed.
						m.setAccessible(true);
						
						dataMethods.addAtEnd(m);
					}
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
	 * @return the parent client of the current {@link Session}.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a client.
	 */
	public final C getParentClient() {
		
		//Checks if the current {@link Session} belonts to a client.
		supposeBelongsToClient();
		
		return parentClient;
	}
	
	//method
	/**
	 * @return the context of the {@link Application}
	 * the {@link Client} of the current {@link Session} belongs to.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a {@link Client}.
	 * @throws InvalidArgumentException if the {@link Client} of the current {@link Session}
	 * does not reference the {@link Application} it belongs to.
	 * @throws ArgumentDoesNotHaveAttributeException if the {@link Application},
	 * the {@link Client} of the current {@link Session} belongs to, does not have a context.
	 */
	public final Object getRefApplicationContext() {
		return getParentClient().getRefApplicationContext();
	}
	
	//abstract method
	/**
	 * Initializes the current {@link Session}.
	 */
	public abstract void initialize();
	
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
	 * @param resultType - The type of the result of the given session.
	 * @return the result from the given session.
	 * @throws ArgumentIsNullException if the given session is null.
	 * @throws ArgumentIsNullException if the given resultType is null.
	 */
	public final <R> R pushAndGetResult(final Session<C> session, final Class<R> resultType) {
		return getParentClient().pushAndGetResult(session, resultType);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given command locally.
	 * 
	 * @param command
	 * @return the current {@link BaseBackGUIClient}.
	 */
	public void runLocally(final String command) {
		internal_invokeSessionUserRunMethod(Node.fromString(command));
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
		getParentClient().setNext(session);
	}
	
	//method
	protected void internal_cleanForInitialization() {}
	
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
	protected final Node internal_invokeSessionUserDataMethod(
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
	protected final void internal_invokeSessionUserRunMethod(
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
	
	//abstract method
	/**
	 * Updates the counterpart of the {@link Client} of the current {@link Session}.
	 */
	protected abstract void updateCounterpart();

	Node getData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.DATA_METHOD_HEADER:
				return internal_invokeSessionUserDataMethod(request.getRefOneAttribute());
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.REQUEST, request,"is not valid");
		}
	}
	
	final Object getRefResult() {
		
		if (result == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.RESULT);
		}
		
		return result;
	}
	
	//package-visible method
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
		}
		catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//package-visible method
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
		}
		catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//package-visible method
	/**
	 * Removes the parent client of the current {@link Session}.
	 */
	final void removeParentClient() {
		parentClient = null;
	}
	
	//package-visible method
	protected void run(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.RUN_METHOD_HEADER:
				internal_invokeSessionUserRunMethod(command.getRefOneAttribute());
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//package-visible method
	/**
	 * Sets the parent client of the current {@link Session}.
	 * 
	 * @param parentClient
	 * @throws ArgumentIsNullException if the given parent client is null.
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	final void setParentClient(C parentClient) {
		
		//Checks if the given client is not null.
		Validator
		.suppose(parentClient)
		.thatIsNamed("parent client")
		.isNotNull();
		
		//Checks if the current session does not belong to a client.
		suppoeDoesNotBelongToClient();
		
		//Sets the parent client of the current session.
		this.parentClient = parentClient;
	}
	
	//package-visibel method
	final void setResult(final Object result) {
		
		Validator.suppose(result).thatIsNamed(VariableNameCatalogue.RESULT).isNotNull();
		
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
		
		//Checks if the current {@link Session} belongs to a client.
		if (!belongsToClient()) {
			throw new InvalidArgumentException(this, "does not belong to a client");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	private void suppoeDoesNotBelongToClient() {
		
		//Checks if the current {@link Session} does not belong to a client.
		if (belongsToClient()) {
			throw new InvalidArgumentException(this, "belongs to a client");
		}
	}
}
