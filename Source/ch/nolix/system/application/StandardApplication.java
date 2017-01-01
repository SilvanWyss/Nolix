//package declaration
package ch.nolix.system.application;

//Java import
import java.lang.reflect.Constructor;

//class
/**
 * A standard application is a normal application.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 70
 * @param <C> - The type of the client of the standard application.
 */
public final class StandardApplication<C extends Client<C>> extends Application<C> {

	//constructor
	/**
	 * Creates new standard application with the given name and initial session class.
	 * 
	 * @param name - The name of this standard application.
	 * @param initialSessionClass - The initial session class of this standard application.
	 * @throws EmptyArgumentException if the given name is null or an empty string.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public StandardApplication(
		final String name,
		final Class<?> initialSessionClass
	) {
		
		//Calls constructor of the base class.
		super(name, initialSessionClass);
	}
	
	//constructor
	/**
	 * Creates new standard application with the given name, port and initial session class.
	 * 
	 * @param name - The name of this standard application.
	 * @param port - The port of this stanard application.
	 * @param initialSessionClass - The initial session class of this standard application.
	 * @throws EmptyArgumentException if the given name is null or an empty string.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public StandardApplication(
		final String name,
		final int port, 
		final Class<?> initialSessionClass
	) {
		
		//Calls constructor of the base class.
		super(name, port, initialSessionClass);
	}

	//method
	/**
	 * @return a newl initial session of this application
	 */
	@SuppressWarnings("unchecked")
	protected Session<C> createInitialSession() {	
			
		//Extracts the constructor of the initial session class of this application.
		final Constructor<?> constructor = initialSessionClass.getDeclaredConstructors()[0];
		constructor.setAccessible(true);

		try {	
			return (Session<C>)constructor.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
