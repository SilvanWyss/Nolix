//package declaration
package ch.nolix.system.client;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 330
 * @param <C> The type of the {@link Client}s of a {@link Application}.
 */
public class Application<C extends Client<C>> implements Named {
	
	//attributes
	private final String name;
	private final Class<C> clientClass;
	private final Class<?> initialSessionClass;
	
	//optional attribute
	private final Object context;
	
	//multi-attribute
	private final List<C> clients = new List<>();
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass and initialSessionClass.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given clientClass is null.
	 * @throws NullArgumentException if the given initialSessionClass is null.
	 */
	public Application(final String name, final Class<C> clientClass, final Class<?> initialSessionClass) {
		
		//Checks if the given name is not null or blank and sets the name of the current Application.
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
		//Checks if the given clientClass is not null and sets the client class of the current Application.
		this.clientClass = Validator.suppose(clientClass).thatIsNamed("client class").isNotNull().andReturn();
		
		//Checks if the given initialSessionClass is not null.
		Validator.suppose(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		
		this.initialSessionClass = initialSessionClass;
		this.context = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link Application} that:
	 * -Has the given name, clientClass and initialSessionClass.
	 * -Will create a {@link NetServer} for itself, and for itself only,
	 * that will listen to {@link Clients} on the given port.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given clientClass is null.
	 * @throws NullArgumentException if the given initialSessionClass is null.
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public Application(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass,
		final int port
	) {
		
		//Calls other constructor.
		this(name, clientClass, initialSessionClass);
		
		//Creates a NetServer for the current Application.
		new NetServer(port).addMainApplication(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass, initialSessionClass and context.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @param context
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blak.
	 * @throws NullArgumentException if the given clientClass is null.
	 * @throws NullArgumentException if the given initialSessionClass is null.
	 * @throws NullArgumentException if the given context is null.
	 */
	public Application(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass,
		final Object context
	) {
		
		//Checks if the given name is not null or blank and sets the name of the current Application.
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
				
		//Checks if the given clientClass is not null and sets the client class of the current Application.
		this.clientClass = Validator.suppose(clientClass).thatIsNamed("client class").isNotNull().andReturn();
		
		//Checks if the given initialSessionClass is not null.
		Validator.suppose(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		
		//Checks if the given context is not null.
		Validator.suppose(context).thatIsNamed(VariableNameCatalogue.CONTEXT).isNotNull();
		
		this.initialSessionClass = initialSessionClass;
		this.context = context;
	}
	
	//constructor
	/**
	 * Creates a new {@link Application} that:
	 * -Has the given name, clientClass, initialSessionClass and context.
	 * -Will create a {@link NetServer} for itself, and for itself only,
	 * that will listen to {@link Clients} on the given port.
	 * 
	 * @param name
	 * @param clientClass
	 * @param initialSessionClass
	 * @param port
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given clientClass is null.
	 * @throws NullArgumentException if the given initialSessionClass is null.
	 * @throws NullArgumentException if the given context is null.
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public Application(
		final String name,
		final Class<C> clientClass,
		final Class<?> initialSessionClass,
		final Object context,
		final int port
	) {
		
		//Calls other constructor.
		this(name, clientClass, initialSessionClass, context);
		
		//Creates a NetServer for the current Application.
		new NetServer(port).addMainApplication(this);
	}
	
	//method
	/**
	 * @return the class of the {@link Client}s of the current {@link Application}.
	 */
	public final Class<C> getClientClass() {
		return clientClass;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
	
	//method
	/**
	 * @return the {@link Client}s of the current {@link Application}.
	 */
	public final IContainer<C> getRefClients() {
		return clients;
	}
	
	//method
	/**
	 * @return the context of the current {@link Application}.
	 * @throws ArgumentMissesAttributeException if the current {@link Application}.does not have a context.
	 */
	public final Object getRefContext() {
		
		//Checks if the current Application has a context.
		supposeHasContext();
		
		return context;
	}
	
	//method
	/**
	 * @return true if the current {@link Application} has a context.
	 */
	public final boolean hasContext() {
		return (context != null);
	}
	
	//method
	/**
	 * Lets the current {@link Application} take the given client.
	 * 
	 * @param client
	 */
	@SuppressWarnings("unchecked")
	public final void takeClient(final Client<?> client) {
		final var client_ = ((C)client);
		client_.setParentApplication(this);
		clients.addAtEnd(client_);
		Sequencer.runInBackground(() -> client_.pushSession(createInitialSession()));
	}
	
	//method
	/**
	 * Lets the current {@link Application} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		try {
			takeClient(getClientConstructor().newInstance(endPoint));
		} 
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * @return a new initial {@link Session} for a {@link Client} of the current {@link Application}.
	 */
	@SuppressWarnings("unchecked")
	protected final Session<C> createInitialSession() {
		try {
			return (Session<C>)getInitialSessionConstructor().newInstance();
		}
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * @return the initial {@link Session} class of the current {@link Application}.
	 */
	protected final Class<?> getRefInitialSessionClass() {
		return initialSessionClass;
	}
	
	//method
	/**
	 * Removes the given client of the current {@link Application}.
	 * 
	 * @param client
	 * @throws InvalidArgumentException if the current {@link Application} does not contain the given client.
	 */
	final void removeClient(final Client<C> client) {
		clients.removeFirst(client);	
	}
	
	//method
	/**
	 * @return the constructor of the {@link Client} class of the current {@link Application}.
	 */
	private Constructor<C> getClientConstructor() {		
		try {
			
			//For a better performance, this implementation does not use all comfortable methods.
			final var clientConstructor = clientClass.getConstructor(EndPoint.class);
			
			clientConstructor.setAccessible(true);
			
			return clientConstructor;
		}
		catch (final NoSuchMethodException | SecurityException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * @return the constructor of the initial {@link Session} class of the current {@link Application}.
	 */
	private Constructor<?> getInitialSessionConstructor() {
		final var constructor = getRefInitialSessionClass().getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		return constructor;
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Application} does not have a context.
	 */
	private void supposeHasContext() {
		
		//Checks if the current Application has a context.
		if (!hasContext()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.CONTEXT);
		}
	}
}
