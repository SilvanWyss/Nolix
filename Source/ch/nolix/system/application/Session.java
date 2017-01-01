/*
 * file:	Session.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	140
 */

//package declaration
package ch.nolix.system.application;

//Java imports
import java.lang.reflect.Method;


//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.helper.MethodHelper;
import ch.nolix.common.util.Validator;

//class
/**
 * A session handles the incoming commands and requests of a client on the origin computer.
 */
public class Session<C extends Client<?>> {
	
	//optional attribute
	private C client;

	//multiple attributes	
	private final List<Method> runMethods = new List<Method>();
	private final List<Method> dataMethods = new List<Method>();
	
	//constructor
	/**
	 * Creates new session.
	 */
	public Session() {
		
		//Extracts run methods and data methods of this session by iterating the methods of this session.
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
	
	//method
	/**
	 * @return the client of this session
	 * @throws Exception if this session has no client
	 */
	protected final C getRefClient() {
		
		if (!hasClient()) {
			throw new UnexistingAttributeException(this, "client");
		}
		
		return client;
	}
	
	//method
	/**
	 * Initializes this session.
	 */
	protected void initialize() {}
	
	//package-visible method
	/**
	 * Invokes the given run method using the given parameters.
	 * 
	 * @param runMethod
	 * @param parameters
	 * @throws Exception if an error occurs
	 */
	final void invokeRunMethod(String runMethod, List<String> parameters) {
		
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
	 * Invokes the given data method using the given parameters.
	 * 
	 * @param dataMethod
	 * @param parameters
	 * @throws Exception if an error occurs
	 */
	final Object invokeDataMethod(String dataMethod, List<String> parameters) {
		
		Object[] parameterArray = parameters.toArray();
		
		try {
			return dataMethods.getRefFirst(m -> m.getName().equals(dataMethod)).invoke(this, parameterArray);
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
	 * @throws Exception if:
	 * -The given client is null.
	 * -This session has already a client.
	 */
	@SuppressWarnings("unchecked")
	final void setClient(Client<?> client) {
		
		//Checks the given client.
		Validator.throwExceptionIfValueIsNull("client", client);
		
		if (hasClient()) {
			throw new RuntimeException("Session has already a client.");
		}
		
		this.client = (C)client;
	}
	
	//method
	/**
	 * @return true if this session has a client
	 */
	private boolean hasClient() {
		return (client != null);
	}
}
