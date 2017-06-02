//package declaration
package ch.nolix.system.client;

import java.lang.reflect.Constructor;

//class
/**
 * A standard application is a normal application.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 60
 * @param <C> - The type of the clients of a standard application.
 */
public final class StandardApplication<C extends Client<C>>
extends Application<C> {

	//constructor
	/**
	 * Creates new standard application with the given name and initial session class.
	 * 
	 * @param name
	 * @param initialSessionClas
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is string.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public StandardApplication(final String name, final Class<?> initialSessionClass) {
		
		//Calls constructor of the base class.
		super(name, initialSessionClass);
	}
	
	//constructor
	/**
	 * Creates new standard application that:
	 * -Has the given name and initial session class.
	 * -Creates a server for itself, and for itself only, that listens to clients on the given port.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is string.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public StandardApplication(
		final String name,
		final Class<?> initialSessionClass,
		final int port
	) {
		//Calls constructor of the base class.
		super(name, initialSessionClass, port);
	}

	//method
	/**
	 * @return a new initial session for a client of this standard application.
	 * @throws RuntimeException if an error occurs.
	 */
	@SuppressWarnings("unchecked")
	protected Session<C> createInitialSession() {
		
		//Extract the constructor of the initial session class of this standard application.
		final Constructor<?> constructor = getRefInitialSessionClass().getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		
		try {	
			return (Session<C>)constructor.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
