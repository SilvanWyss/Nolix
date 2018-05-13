//package declaration
package ch.nolix.system.client;

import java.lang.reflect.Constructor;

import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A context application is an application with a context.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 90
 * @param <CL> - The type of the clients of a context application.
 * @param <CO> - The type of the context of a context application.
 */
public class ContextApplication<CL extends Client<CL>, CO>
extends Application<CL> {

	//attribute
	private final CO context;
	
	//constructor
	/**
	 * Creates a new context application with the given name, context and initial session class.
	 * 
	 * @param name
	 * @param context
	 * @param initialSessionClass
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is string.
	 * @throws NullArgumentException if the given initial session class is null.
	 * @throws NullArgumentException if the given context is null.
	 */
	public ContextApplication(
		final String name,
		final CO context,
		final Class<CL> clientClass,
		final Class<?> initialSessionClass
	) {
		//Calls constructor of the base class.
		super(name, clientClass, initialSessionClass);
		
		//Checks if the given context is not null.
		Validator.suppose(context).thatIsNamed("context").isNotNull();
		
		//Sets the context of this context application.
		this.context = context;
	}
	
	//constructor
	/**
	 * Creates a new context application that:
	 * -Has the given name, context and initial session class.
	 * -Creates a server for itself, and for itself only, that listens to clients on the given port.
	 * 
	 * @param name
	 * @param context
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is string.
	 * @throws NullArgumentException if the given initial session class is null.
	 * @throws NullArgumentException if the given context is null.
	 */
	public ContextApplication(
		final String name,
		final CO context,
		final Class<CL> clientClass,
		final Class<?> initialSessionClass,		
		final int port
	) {
		//Calls constructor of the base class.
		super(name, clientClass, initialSessionClass, port);
		
		//Checks the given context.
		Validator.suppose(context).thatIsNamed("context").isNotNull();
		
		//Sets the context of this context application.
		this.context = context;
	}
	
	//method
	/**
	 * @return a new initial session for a client of this context application.
	 * @throws RuntimeException if an error occurs.
	 */
	@SuppressWarnings("unchecked")
	protected Session<CL> createInitialSession() {
		
		final Constructor<?> constructor = getRefInitialSessionClass().getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		
		try {	
			return (Session<CL>)constructor.newInstance(context);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
