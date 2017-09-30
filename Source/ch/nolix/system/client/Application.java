//package declaration
package ch.nolix.system.client;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 * @param <C> - The type of the clients of an application.
 */
public abstract class Application<C extends Client<C>> extends NamedElement {

	//attribute
	private final Class<?> initialSessionClass;
	
	//multiple attribute
	private final List<C> clients = new List<C>();
	
	//constructor
	/**
	 * Creates new application with the given name and initial session class.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public Application(final String name, final Class<?> initialSessionClass) {
	
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given initial session class is not null.
		Validator.suppose(initialSessionClass).thatIsNamed("initial session class").isNotNull();

		//Sets the initial session class of this application.
		this.initialSessionClass = initialSessionClass;
	}
	
	//constructor
	/**
	 * Creates new application that:
	 * -Has the given name and initial session class.
	 * -Creates a server for itself, and for itself only, that listens to clients on the given port.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	@SuppressWarnings("resource")
	public Application(
		final String name,
		final Class<?> initialSessionClass,
		final int port
	) {
		
		//Calls other constructor.
		this(name, initialSessionClass);
		
		//Creates server for this application.
		new NetServer(port).addArbitraryApplication(this);
	}
	
	//method
	/**
	 * This method uses the change to remove all aborted clients of this application.
	 * 
	 * @return the clients of this application.
	 */
	public final IContainer<C> getRefClients() {
		return clients.removeAll(c -> c.isClosed());
	}
	
	@SuppressWarnings("unchecked")
	public final void takeClient(final Client<?> client) {
		
		C c = ((C)client);
		
		final Session<C> initialSession = createInitialSession();	
		
		clients.addAtEnd(c);
		Sequencer.runInBackground(() -> c.setSession(initialSession));
	}
	
	@SuppressWarnings("unchecked")
	public final void takeDuplexController(final DuplexController duplexController) {
		try {
			
			//Extracts the direct sub class of the initial session class of this application.
			Class<?> initialSessionDirectSubClass = initialSessionClass;
			while (!initialSessionDirectSubClass.getSuperclass().getSimpleName().equals(Session.class.getSimpleName())) {
				initialSessionDirectSubClass = initialSessionDirectSubClass.getSuperclass();
			}
			
			//Extracts the constructor of the client class of this application.
			final String className
			= ((ParameterizedType)initialSessionDirectSubClass
			.getGenericSuperclass())
			.getActualTypeArguments()[0].toString().split("\\s")[1];
			final Constructor<?> constructor = Class.forName(className).getConstructor(DuplexController.class);	
			constructor.setAccessible(true);
			
			constructor.newInstance(duplexController);
			
			//Creates client.
			C client = (C)constructor.newInstance(duplexController);
			
			takeClient(client);			
		}
		catch (final Exception exception) {
			System.out.println(exception.getMessage());
			throw new RuntimeException(exception);
		}
	}
	
	//abstract method
	/**
	 * @return a new initial session for a client of this application.
	 */
	protected abstract Session<C> createInitialSession();
	
	//method
	/**
	 * @return the initial session class of this application.
	 */
	protected final Class<?> getRefInitialSessionClass() {
		return initialSessionClass;
	}
}
