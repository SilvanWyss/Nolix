//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.reflection.MethodHelper;
import ch.nolix.common.validator.Validator;

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
	
	//multi-attribute
	private final List<Method> userRunMethods = new List<>();
	private final List<Method> userDataMethods = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link Session}.
	 */
	public Session() {
		
		//Extracts the user run methods and the user data methods of the current session.
			//Iterates the methods of the current session.
			Class<?> c = getClass();
			while (c != null) {
				for (final var m : c.getMethods()) {
					if (
						Character.isUpperCase(m.getName().charAt(0))
						&& MethodHelper.allParametersOfMethodAreStrings(m)
					) {
						
						if (m.getReturnType().equals(Void.TYPE)) {
							
							//Setting the method accessible is needed that it can be accessed.
							m.setAccessible(true);
							
							userRunMethods.addAtEnd(m);
						}
						
						else if (m.getReturnType().equals(Node.class)) {
							
							//Setting the method accessible is needed that it can be accessed.
							m.setAccessible(true);
							
							userDataMethods.addAtEnd(m);
						}
					}
				}
				
				c = c.getSuperclass();
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
		return userDataMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the user run method with the given name from the current {@link Session}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Session} does not contain a user run method with the given name.
	 */
	private Method getUserRunMethod(final String name) {
		return userRunMethods.getRefFirst(m -> m.getName().equals(name));
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
