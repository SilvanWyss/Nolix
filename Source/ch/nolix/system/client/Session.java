//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

import ch.nolix.core.containers.List;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.Node;
import ch.nolix.core.reflection.MethodHelper;
import ch.nolix.core.validator.Validator;

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
	private IFunction popFunction;

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
	 * @throws ArgumentMissesAttributeException if the {@link Application},
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
	 * @throws NullArgumentException if the given parent client is null.
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
	
	//package-visible method
	/**
	 * Runs the pop function of the current {@link Session}
	 * if the current {@link Session} has a pop function.
	 */
	final void runProbabalePopFunction() {
		if (hasPopFunction()) {
			popFunction.run();
		}
	}
	
	//package-visible method
	/**
	 * Sets the pop function of the current {@link Session}.
	 * 
	 * @param popFunction
	 * @throws NullArgumentException if the given pop function is null.
	 */
	final void setPopFunction(final IFunction popFunction) {
		
		Validator
		.suppose(popFunction)
		.thatIsNamed("pop function")
		.isNotNull();
		
		this.popFunction = popFunction;
	}
	
	//method
	/**
	 * @param name
	 * @return the user data method with the given name from the current {@link Session}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link Session} does not contain a user data method with the given name.
	 */
	private Method getUserDataMethod(final String name) {
		return userDataMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the user run method with the given name from the current {@link Session}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link Session} does not contain a user run method with the given name.
	 */
	private Method getUserRunMethod(final String name) {
		return userRunMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @return true if the current {@link Session} has a pop function.
	 */
	private boolean hasPopFunction() {
		return (popFunction != null);
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
