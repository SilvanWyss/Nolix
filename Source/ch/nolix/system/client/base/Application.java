//package declaration
package ch.nolix.system.client.base;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementGetter;
import ch.nolix.core.generalskillapi.Castable;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programcontrol.sequencer.Sequencer;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 300
 * @param <C> is the type of the {@link Client}s of a {@link Application}.
 */
public class Application<C extends Client<C>> implements Castable, Named {
	
	//attributes
	private final String name;
	private final Class<C> clientClass;
	private final Class<?> initialSessionClass;
	
	//optional attribute
	private final Object context;
	
	//multi-attribute
	private final LinkedList<C> clients = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass and initialSessionClass.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public Application(final String name, final Class<?> initialSessionClass) {
		
		//Asserts that the given name is not null or blank.
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
				
		//Asserts that the given initialSessionClass is not null.
		Validator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		
		this.name = name;
		this.initialSessionClass = initialSessionClass;
		clientClass = createInitialSession().internalGetRefClientClass();
		this.context = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass, initialSessionClass and context.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @param context
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blak.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 * @throws ArgumentIsNullException if the given context is null.
	 */
	public Application(
		final String name,
		final Class<?> initialSessionClass,
		final IElementGetter<?> context
	) {
		
		//Calls other constructor.
		this(name, initialSessionClass, (Object)context);
	}
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass, initialSessionClass and context.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @param context
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blak.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 * @throws ArgumentIsNullException if the given context is null.
	 */
	public Application(
		final String name,
		final Class<?> initialSessionClass,
		final Object context
	) {
		
		//Asserts that the given name is not null or blank.
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		//Asserts that the given initialSessionClass is not null.
		Validator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		
		//Asserts that the given context is not null.
		Validator.assertThat(context).thatIsNamed(LowerCaseCatalogue.CONTEXT).isNotNull();
		
		this.name = name;
		this.initialSessionClass = initialSessionClass;
		clientClass = createInitialSession().internalGetRefClientClass();
		this.context = context;
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
		
		removeClosedClients();
		
		return clients;
	}
	
	//method
	/**
	 * @param <CO> is the type of the context of the current {@link Application}.
	 * @return the context of the current {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Application} does not have a context.
	 */
	@SuppressWarnings("unchecked")
	public final <CO> CO getRefContext() {
		
		//Asserts that the current Application has a context.
		if (!hasContext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.CONTEXT);
		}
		
		return (CO)context;
	}
	
	//method
	/**
	 * @param type
	 * @param <CO> is the type of the context of the current {@link Application}.
	 * @return the context of the current {@link Application} as the given type.
	 * @throws ArgumentIsNullException if the given type is null.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Application} does not have a context.
	 */
	@SuppressWarnings("unchecked")
	public final <CO> CO getRefContextAs(final Class<CO> type) {
		
		//Asserts that the given type is not null.
		Validator.assertThat(type).thatIsNamed(LowerCaseCatalogue.TYPE).isNotNull();
		
		//Asserts that the current Application has a context.
		if (!hasContext()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.CONTEXT);
		}
		
		return (CO)context;
	}
	
	//method
	/**
	 * @return true if the current {@link Application} has a {@link Client} connected.
	 */
	public final boolean hasClientConnected() {
		return getRefClients().containsAny();
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
		final var lClient = ((C)client);
		lClient.setParentApplication(this);
		clients.addAtEnd(lClient);
		Sequencer.runInBackground(() -> lClient.push(createInitialSession()));
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
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw new WrapperException(exception);
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
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw new WrapperException(exception);
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
	 * @return the constructor of the {@link Client} class of the current {@link Application}.
	 */
	private Constructor<C> getClientConstructor() {		
		try {
			
			//For a better performance, this implementation does not use all comfortable methods.
			final var clientConstructor = clientClass.getConstructor(EndPoint.class);
			
			clientConstructor.setAccessible(true);
			
			return clientConstructor;
		} catch (final NoSuchMethodException | SecurityException exception) {
			throw new WrapperException(exception);
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
	 * Removes the closed {@link Client}s of the current {@link Application}.
	 */
	private void removeClosedClients() {
		clients.removeAll(Client::isClosed);
	}
}
