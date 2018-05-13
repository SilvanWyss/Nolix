//package declaration
package ch.nolix.system.client;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 120
 * @param <C> - The type of the clients of an application.
 */
public abstract class Application<C extends Client<C>> extends NamedElement {

	//attributes
	private final Class<C> clientClass;
	private final Class<?> initialSessionClass;
	
	//multiple attribute
	private final List<C> clients = new List<C>();
	
	//constructor
	/**
	 * Creates a new application with the given name and initial session class.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public Application(final String name, final Class<C> clientClass, final Class<?> initialSessionClass) {
	
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given initial session class is not null.
		Validator.suppose(initialSessionClass).thatIsNamed("initial session class").isNotNull();

		//Sets the initial session class of this application.
		this.clientClass = clientClass;
		this.initialSessionClass = initialSessionClass;
	}
	
	//constructor
	/**
	 * Creates a new application that:
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
		final Class<C> clientClass,
		final Class<?> initialSessionClass,
		final int port
	) {
		
		//Calls other constructor.
		this(name, clientClass, initialSessionClass);
		
		//Creates server for this application.
		new NetServer(port).addArbitraryApplication(this);
	}
	
	//abstract method
	/**
	 * @return the class of the client of this session.
	 */
	public final Class<C> getClientClass() {
		return clientClass;
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
	
	public final void takeDuplexController(final DuplexController duplexController) {
		try {
			final var constructor = getClientClass().getConstructor(DuplexController.class);
			constructor.setAccessible(true);
			C client = (C)constructor.newInstance(duplexController);
			takeClient(client);
		} catch (final 
			IllegalAccessException
			| IllegalArgumentException
			| InstantiationException
			| InvocationTargetException
			| NoSuchMethodException
			| SecurityException
			exception
		) {
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
