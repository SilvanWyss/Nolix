//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.helper.MethodHelper;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 210
 * @param <C> The type of the client of a {@link Session}.
 */
public abstract class Session<C extends Client<C>> {
	
	//attribute
	private C parentClient;

	//multi-attribute
	private final List<Method> userRunMethods = new List<Method>();
	private final List<Method> userDataMethods = new List<Method>();
	
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
						
						else if (m.getReturnType().equals(StandardSpecification.class)) {
							
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
	 * @throws InvalidStateException if the current {@link Session} belongs to no client.
	 */
	public final C getParentClient() {
		
		//Checks if the current {@link Session} belonts to a client.
		supposeBelongsToClient();
		
		return parentClient;
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
	final StandardSpecification invokeUserDataMethod(String name, String... arguments) {
		try {
			return
			(StandardSpecification)
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
	 * @throws NullArgumentException if the given parent client is null.
	 * @throws InvalidStateException if the current {@link Session} belongs to a client.
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
	
	//method
	/**
	 * @param name
	 * @return the user data method with the given name from the current {@link Session}.
	 * @throws UnexistingAttributeException
	 * if the current {@link Session} contains no user data method with the given name.
	 */
	private Method getUserDataMethod(final String name) {
		return userDataMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the user run method with the given name from the current {@link Session}.
	 * @throws UnexistingAttributeException
	 * if the current {@link Session} contains no user run method with the given name.
	 */
	private Method getUserRunMethod(final String name) {
		return userRunMethods.getRefFirst(m -> m.getName().equals(name));
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link Session} does not belong to a client.
	 */
	private void supposeBelongsToClient() {
		
		//Checks if the current {@link Session} belongs to a client.
		if (!belongsToClient()) {
			throw new InvalidStateException(this, "does not belong to a client");
		}	
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link Session} belongs to a client.
	 */
	private void suppoeDoesNotBelongToClient() {
		
		//Checks if the current {@link Session} does not belong to a client.
		if (belongsToClient()) {
			throw new InvalidStateException(this, "belongs to a client");
		}	
	}
}
