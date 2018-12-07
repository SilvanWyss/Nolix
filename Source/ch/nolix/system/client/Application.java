//package declaration
package ch.nolix.system.client;

import java.lang.reflect.Constructor;
//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 260
 * @param <C> The type of the clients of an application.
 */
public class Application<C extends Client<C>> extends NamedElement {

	//attributes
	private final Class<C> clientClass;
	private final Class<?> initialSessionClass;
	
	//optional attribute
	private final Object context;
	
	//multi-attribute
	private final List<C> clients = new List<C>();
	
	//constructor
	/**
	 * Creates a new application
	 * with the given name, client class and initial session class.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given client class is null.
	 * @throws NullArgumentException if the given initial session class is null.
	 */
	public Application(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass
	) {
	
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given client class is not null.
		Validator
		.suppose(clientClass)
		.thatIsNamed("client class")
		.isInstance();
		
		//Checks if the given initial session class is not null.
		Validator
		.suppose(initialSessionClass)
		.thatIsNamed("initial session class")
		.isInstance();
		
		this.clientClass = clientClass;
		this.initialSessionClass = initialSessionClass;
		this.context = null;
	}
	
	//constructor
	/**
	 * Creates a new application
	 * with the given name, client class, initial session class and context.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @param context
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given client class is null.
	 * @throws NullArgumentException if the given initial session class is null.
	 * @throws NullArgumentException if the given context is null.
	 */
	public Application(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass,
		final Object context
	) {
	
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given client class is not null.
		Validator
		.suppose(clientClass)
		.thatIsNamed("client class")
		.isInstance();
		
		//Checks if the given initial session class is not null.
		Validator
		.suppose(initialSessionClass)
		.thatIsNamed("initial session class")
		.isInstance();
		
		//Checks if the given context is not null.
		Validator
		.suppose(context)
		.thatIsNamed(VariableNameCatalogue.CONTEXT)
		.isInstance();
		
		this.clientClass = clientClass;
		this.initialSessionClass = initialSessionClass;
		this.context = context;
	}
	
	//constructor
	/**
	 * Creates a new application that:
	 * -Has the given name, client class and initial session class.
	 * -Creates a server for itself, and for itself only, that listens to clients on the given port.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given client class is null.
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
		
		//Creates a server for this application.
		new NetServer(port).addDefaultApplication(this);
	}
	
	//method
	/**
	 * @return the class of the clients of this session.
	 */
	public final Class<C> getClientClass() {
		return clientClass;
	}
	
	//method
	/**
	 * @return the clients of this application.
	 */
	public final IContainer<C> getRefClients() {
		return clients.removeAll(c -> c.isClosed());
	}
	
	//method
	/**
	 * @return the context of this application.
	 * @throws UnexistingAttributeException if this application does not have a context.
	 */
	public final Object getRefContext() {
		
		//Checks if this application has a context.
		supposeHasContext();
		
		return context;
	}
	
	//method
	/**
	 * @return true if this application has a context.
	 */
	public final boolean hasContext() {
		return (context != null);
	}
	
	//method
	/**
	 * Lets this application take the given client.
	 * 
	 * @param client
	 */
	@SuppressWarnings("unchecked")
	public final void takeClient(final Client<?> client) {
		
		final var client_ = ((C)client);
		
		client_.setParentApplication(this);
		final Session<C> initialSession = createInitialSession();		
		clients.addAtEnd(client_);
		Sequencer.runInBackground(() -> client_.pushSession(initialSession));
	}
	
	//method
	/**
	 * Lets this application take the given duplecx controller.
	 * 
	 * @param endPoint
	 */
	public final void takeDuplexController(final EndPoint endPoint) {
		try {
			final var constructor = getClientClass().getConstructor(EndPoint.class);
			constructor.setAccessible(true);
			C client = constructor.newInstance(endPoint);
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
	
	//method
	/**
	 * @return the initial session class of this application.
	 */
	protected final Class<?> getRefInitialSessionClass() {
		return initialSessionClass;
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if this application does not have a context.
	 */
	private void supposeHasContext() {
		
		//Checks if this application has a context.
		if (!hasContext()) {
			throw
			new UnexistingAttributeException(
				this,
				VariableNameCatalogue.CONTEXT
			);
		}
	}
}
