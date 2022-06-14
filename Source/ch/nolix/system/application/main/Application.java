//package declaration
package ch.nolix.system.application.main;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.IContextOwner;
import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.reflection.GlobalClassHelper;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <BC> is the type of the {@link BackendClient}s of a {@link Application}.
 * @param <C> is the type of the context of a {@link Application}.
 */
public class Application<
	BC extends BackendClient<BC, C>,
	C
> implements IContextOwner<C>, Named {
	
	//attribute
	private final String name;
	
	//attribute
	private final Class<BC> clientClass;
	
	//attribute
	private final Class<Session<BC, C>> initialSessionClass;
	
	//attribute
	private final C context;
	
	//multi-attribute
	private final LinkedList<BC> clients = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given name, clientClass, initialSessionClass and context.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @param context
	 * @param <S> is the type of the given initalSessionClass.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blak.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 * @throws ArgumentIsNullException if the given context is null.
	 */
	@SuppressWarnings("unchecked")
	public <S extends Session<BC, C>> Application(
		final String name,
		final Class<S> initialSessionClass,
		final C context
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		GlobalValidator.assertThat(context).thatIsNamed(LowerCaseCatalogue.CONTEXT).isNotNull();
		
		this.name = name;
		this.initialSessionClass = (Class<Session<BC, C>>)initialSessionClass;
		clientClass = (Class<BC>)(createInitialSession().internalGetRefClientClass());
		this.context = context;
	}
	
	//method
	/**
	 * @return the class of the {@link Client}s of the current {@link Application}.
	 */
	public final Class<BC> getClientClass() {
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
	public final IContainer<BC> getRefClients() {
		
		removeClosedClients();
		
		return clients;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final C getRefContext() {
		return context;
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
	 * Lets the current {@link Application} take the given client.
	 * 
	 * @param client
	 */
	@SuppressWarnings("unchecked")
	public final void takeClient(final BackendClient<?, ?> client) {
		final var lClient = ((BC)client);
		lClient.internalSetParentApplication(this);
		clients.addAtEnd(lClient);
		GlobalSequencer.runInBackground(() -> lClient.internalPush(createInitialSession()));
	}
	
	//method
	/**
	 * Lets the current {@link Application} take the given endPoint.
	 * 
	 * @param endPoint
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		takeClient(createBackendClientWithEndPoint(endPoint));
	}
	
	//method
	/**
	 * @return a new initial {@link Session} for a {@link Client} of the current {@link Application}.
	 */
	@SuppressWarnings("unchecked")
	protected final Session<BC, C> createInitialSession() {
		try {
			return (Session<BC, C>)getInitialSessionConstructor().newInstance();
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
	 * @param endPoint
	 * @return a new {@link BackendClient} with the given endPoint
	 */
	private BC createBackendClientWithEndPoint(final EndPoint endPoint) {
		
		final var backendClient = GlobalClassHelper.createInstanceFromDefaultConstructorOf(clientClass);
		backendClient.internalSetEndPoint(endPoint);
		
		return backendClient;
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
