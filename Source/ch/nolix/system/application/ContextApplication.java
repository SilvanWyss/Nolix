//package declaration
package ch.nolix.system.application;

//Java import
import java.lang.reflect.Constructor;

//own import

import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A context application is an application with a context.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 10
 */
public final class ContextApplication<CO, CL extends Client<CL>> extends Application<CL> {

	//attribute
	private final CO context;
	
	//constructor
	/**
	 * Creates new context application with the given name, the given context and the given initial session class.
	 * 
	 * @param name
	 * @param context
	 * @param initialSessionClass
	 * @throws Exception if:
	 * -The given name is null or an empty string.
	 * -The given context is null.
	 * -The given initial session class is null.
	 */
	public ContextApplication(
		final String name,
		final CO context,
		final Class<?> initialSessionClass) {
		
		//Calls constructor of the base class.
		super(name, initialSessionClass);
		
		//Checks the given context.
		ZetaValidator.supposeThat(context).isNotNull();
		
		//Sets the context of this context application.
		this.context = context;
	}
	
	//constructor
	/**
	 * Creates new context application with the given name, port, context and initial session class.
	 * 
	 * @param name					The name of this context application.
	 * @param port					The port this context application is hosted on.
	 * @param context				The context of this context application.
	 * @param initialSessionClass	The initial session class of this context application.
	 * @throws NullArgumentException if the given context is null
	 */
	public ContextApplication(
		final String name,
		final int port,
		final CO context,
		final Class<?> initialSessionClass
	) {
		//Calls constructor of the base class.
		super(name, port, initialSessionClass);
		
		//Checks the given context.
		ZetaValidator.supposeThat(context).thatIsNamed("context").isNotNull();
		
		//Sets the context of this context application.
		this.context = context;
	}
	
	//method
	/**
	 * Lets the content application take the given client.
	 * 
	 * @param client
	 */
	
	@SuppressWarnings("unchecked")
	protected Session<CL> createInitialSession() {	
		
		Constructor<?> constructor = initialSessionClass.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		
		try {	
			return (Session<CL>)constructor.newInstance(context);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
