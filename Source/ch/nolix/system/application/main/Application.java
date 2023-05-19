//package declaration
package ch.nolix.system.application.main;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <BC> is the type of the {@link BackendClient}s of a {@link Application}.
 * @param <AC> is the type of the application context of a {@link Application}.
 */
public abstract class Application<
	BC extends BackendClient<BC, AC>,
	AC
>
implements IApplication<AC> {
	
	//attribute
	private String instanceName;
	
	//attribute
	private final AC applicationContext;
	
	//multi-attribute
	private final LinkedList<BC> clients = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given applicationContext.
	 * 
	 * @param applicationContext
	 * @throws ArgumentIsNullException if the given applicationContext is null.
	 */
	protected Application(final AC applicationContext) {
		
		GlobalValidator.assertThat(applicationContext).thatIsNamed("application context").isNotNull();
		
		this.applicationContext = applicationContext;
	}
	
	//method
	/**
	 * @return the class of the {@link Client}s of the current {@link Application}.
	 */
	@SuppressWarnings("unchecked")
	public final Class<BC> getClientClass() {
		return (Class<BC>)(createInitialSession().getClientClass());
	}
	
	//method
	/**
	 * @return the instance name of the current {@link Application}.
	 */
	@Override
	public final String getInstanceName() {
		return instanceName;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final AC getOriApplicationContext() {
		return applicationContext;
	}
	
	//method
	/**
	 * @return the {@link Client}s of the current {@link Application}.
	 */
	public final IContainer<BC> getOriClients() {
		
		removeClosedClients();
		
		return clients;
	}
	
	//method
	/**
	 * @return true if the current {@link Application} has a {@link Client} connected.
	 */
	public final boolean hasClientConnected() {
		return getOriClients().containsAny();
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
	public final void takeEndPoint(final IEndPoint endPoint) {
		takeClient(createBackendClientWithEndPoint(endPoint));
	}
	
	//method
	/**
	 * @return a new initial {@link Session} for a {@link Client} of the current {@link Application}.
	 */
	@SuppressWarnings("unchecked")
	protected final Session<BC, AC> createInitialSession() {
		try {
			return (Session<BC, AC>)getInitialSessionConstructor().newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			exception
		) {
			throw WrapperException.forError(exception);
		}
	}
	
	//method
	/**
	 * @return the initial {@link Session} class of the current {@link Application}.
	 */
	protected abstract Class<?> getInitialSessionClass();
	
	//method
	/**
	 * Sets the given instanceName to the current {@link Application}.
	 * 
	 * @param instanceName
	 * @throws ArgumentIsNullException if the given instanceName is null
	 * @throws InvalidArgumentException if the given instanceName is blank.
	 * @throws ArgumentHasAttributeException if the current {@link Application} has already an instance name.
	 */
	final void internalSetInstanceName(final String instanceName) {
		
		GlobalValidator.assertThat(instanceName).thatIsNamed("instance name").isNotBlank();
		
		assertDoesNotHaveInstanceName();
		
		this.instanceName = instanceName;
	}
	
	//method
	/**
	 * @throws ArgumentHasAttributeException if the current {@link Application} has already an instance name.
	 */
	private void assertDoesNotHaveInstanceName() {
		if (hasInstanceName()) {
			throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "instance name");
		}
	}

	//method
	/**
	 * @param endPoint
	 * @return a new {@link BackendClient} with the given endPoint
	 */
	private BC createBackendClientWithEndPoint(final IEndPoint endPoint) {
		
		final var backendClient = GlobalClassHelper.createInstanceFromDefaultConstructorOf(getClientClass());
		backendClient.internalSetEndPoint(endPoint);
		
		return backendClient;
	}
	
	//method
	/**
	 * @return the constructor of the initial {@link Session} class of the current {@link Application}.
	 */
	private Constructor<?> getInitialSessionConstructor() {
		final var constructor = getInitialSessionClass().getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		return constructor;
	}
	
	//method
	/**
	 * @return true if the current {@link Appication} has an instance name.
	 */
	private boolean hasInstanceName() {
		return (instanceName != null);
	}
	
	//method
	/**
	 * Removes the closed {@link Client}s of the current {@link Application}.
	 */
	private void removeClosedClients() {
		clients.removeAll(Client::isClosed);
	}
}
