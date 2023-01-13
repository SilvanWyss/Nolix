//package declaration
package ch.nolix.system.application.main;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <BC> is the type of the {@link BackendClient}s of a {@link Application}.
 * @param <AC> is the type of the application context of a {@link Application}.
 */
public class Application<
	BC extends BackendClient<BC, AC>,
	AC
>
implements IApplication<AC> {
	
	//attribute
	private final String instanceName;
	
	//attribute
	private final Class<BC> clientClass;
	
	//attribute
	private final Class<Session<BC, AC>> initialSessionClass;
	
	//attribute
	private final AC applicationContext;
	
	//multi-attribute
	private final LinkedList<BC> clients = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Application} with the given instanceName, clientClass, initialSessionClass and applicationContext.
	 * 
	 * @param instanceName
	 * @param initialSessionClass
	 * @param applicationContext
	 * @param <S> is the type of the given initalSessionClass.
	 * @throws ArgumentIsNullException if the given instanceName is null.
	 * @throws InvalidArgumentException if the given instanceName is blak.
	 * @throws ArgumentIsNullException if the given clientClass is null.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 * @throws ArgumentIsNullException if the given applicationContext is null.
	 */
	@SuppressWarnings("unchecked")
	public <S extends Session<BC, AC>> Application(
		final String instanceName,
		final Class<S> initialSessionClass,
		final AC applicationContext
	) {
		
		GlobalValidator.assertThat(instanceName).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();
		GlobalValidator.assertThat(applicationContext).thatIsNamed("application context").isNotNull();
		
		this.instanceName = instanceName;
		this.initialSessionClass = (Class<Session<BC, AC>>)initialSessionClass;
		clientClass = (Class<BC>)(createInitialSession().internalGetRefClientClass());
		this.applicationContext = applicationContext;
	}
	
	//method
	@Override
	public final String getApplicationName() {
		//TODO: Implement.
		return getInstanceName();
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
	public final AC getRefApplicationContext() {
		return applicationContext;
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
