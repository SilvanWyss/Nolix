//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.Method;


//own imports




import ch.nolix.core.container.List;
import ch.nolix.core.helper.MethodHelper;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A session handles the run method commands and data method requests a client receives.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 150
 * @param <C> - The type of the client of a session.
 */
public abstract class Session<C extends Client<?>> {
	
	//attribute
	private C client;

	//multiple attributes	
	private final List<Method> runMethods = new List<Method>();
	private final List<Method> dataMethods = new List<Method>();
	
	//constructor
	/**
	 * Creates new session.
	 */
	public Session() {
		
		//Extracts the run methods and the data methods of this session by iterating the methods of this session.
		for (Method m: getClass().getMethods()) {
			if (Character.isUpperCase(m.getName().charAt(0)) && MethodHelper.allParametersOfMethodAreStrings(m)) {	
				
				if (m.getReturnType().equals(Void.TYPE)) {
					
					//Setting the method accessible is needed that it can accessed from a package-visible sub class.
					m.setAccessible(true); 
					
					runMethods.addAtEnd(m);
				}
				
				if (m.getReturnType().getSimpleName().equals(Object.class.getSimpleName())) {
					
					//Setting the method accessible is needed that it can accessed from a package-visible sub class.
					m.setAccessible(true);
					
					dataMethods.addAtEnd(m);
				}
			}
		}
	}
	
	//abstract method
	/**
	 * Initializes this session.
	 */
	public abstract void initialize();
	
	//method
	/**
	 * @return the client of this session.
	 * @throws UnexistingAttributeException if this session has no client.
	 */
	protected final C getRefClient() {
		
		//Checks if this session has a client.
		if (!hasClient()) {
			throw new UnexistingAttributeException(this, Client.class);
		}
		
		return client;
	}
	
	//package-visible method
	/**
	 * Invokes the given data method with the given parameters.
	 * 
	 * @param dataMethod
	 * @param parameters
	 * @return the data the given data method returns for the given parameters.
	 * @throws RuntimeException if an error occurs.
	 */
	final Object invokeDataMethod(String dataMethod, List<String> parameters) {
		
		//Creates parameter array.
		final Object[] parameterArray = parameters.toArray();
		
		try {
			return dataMethods.getRefFirst(m -> m.getName().equals(dataMethod)).invoke(this, parameterArray);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//package-visible method
	/**
	 * Invokes the given run method with the given parameters.
	 * 
	 * @param runMethod
	 * @param parameters
	 * @throws RuntimeException if an error occurs.
	 */
	final void invokeRunMethod(final String runMethod, final List<String> parameters) {
		
		//Creates parameter array.
		final Object[] parameterArray = parameters.toArray();
		
		try {
			runMethods.getRefFirst(m -> m.getName().equals(runMethod)).invoke(this, parameterArray);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
		
	//method
	/**
	 * Sets the client of this session.
	 * 
	 * @param client
	 * @throws NullArgumentException if the given client is null.
	 * @throws InvalidStateException if this session has already a client.
	 */
	@SuppressWarnings("unchecked")
	final void setClient(Client<?> client) {
		
		//Checks if the given client is not null.
		Validator.supposeThat(client).thatIsInstanceOf(Client.class).isNotNull();
		
		//Checks if this session has not already a client.
		if (hasClient()) {
			throw new InvalidStateException(this, "has already a client");
		}
		
		//Sets the client of this session.
		this.client = (C)client;
	}
	
	//method
	/**
	 * @return true if this session has a client.
	 */
	private boolean hasClient() {
		return (client != null);
	}
}
