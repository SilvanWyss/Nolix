//package declaration
package ch.nolix.system.client.base;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;

//class
/**
 * A {@link BaseServer} can contain {@link Application}s.
 * A {@link BaseServer} is closable.
 * 
 * @author Silvan Wyss
 * @date 2016-11-01
 */
public abstract class BaseServer implements GroupCloseable {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private Application<?> defaultApplication;
	
	//multi-attribute
	private final LinkedList<Application<?>> applications = new LinkedList<>();
	
	//method
	/**
	 * Adds the given application to the current {@link BaseServer}.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the same name as the given application.
	 */
	public final void addApplication(final Application<?> application) {
		
		addApplicationToList(application);
		
		noteAddedApplication(application);
	}

	//method
	/**
	 * Adds the given applications to the current {@link BaseServer}.
	 * 
	 * @param applications
	 * @throws ArgumentIsNullException if one of the given applications is null.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the same name as one of the given applications.
	 */
	public final void addApplication(final Application<?>... applications) {
		
		//Iterates the given applications.
		for (final var a: applications) {
			addApplication(a);
		}
	}
	
	/**
	 * Adds a new {@link Application} with the given name and initialSessionClass to the current {@link BaseServer}.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the given name.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public final <BC extends BackendClient<BC>> void addApplication(
		final String name,
		final Class<Session<BC>> initialSessionClass
	) {
		
		//Calls other method.
		addApplication(new Application<>(name, initialSessionClass));
	}
	
	//method
	/**
	 * Adds the given defaultApplication to the current {@link BaseServer}.
	 * A default {@link Application} takes all {@link Client}s that do not have a target.
	 * 
	 * @param defaultApplication
	 * @throws ArgumentIsNullException if the given defaultApplication is null.
	 * @throws InvalidArgumentException if the current {@link BaseServer}
	 * contains already a {@link Application} with the same name as the given defaultApplication.
	 */
	public final <BC extends BackendClient<BC>> void addDefaultApplication(final Application<BC> defaultApplication) {
		
		addApplicationToList(defaultApplication);
		this.defaultApplication = defaultApplication;
		
		noteAddedDefaultApplication(defaultApplication);
	}
	
	/**
	 * Adds a new default {@link Application} with
	 * the given name and initialSessionClass to the current {@link BaseServer}.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a default {@link Application}.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the given name.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public final <S extends Session<BC>, BC extends BackendClient<BC>> void addDefaultApplication(
		final String name,
		final Class<S> initialSessionClass
	) {
		
		//Calls other method
		addDefaultApplication(new Application<BC>(name, initialSessionClass));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseServer} contains a default {@link Application}.
	 */
	public final boolean containsDefaultApplication() {
		return (defaultApplication != null);
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link BaseServer} contains a {@link Application} with the given name.
	 */
	public final boolean containsApplicationWithName(final String name) {
		return applications.containsAny(a -> a.hasName(name));
	}
		
	//method
	/**
	 * @param name
	 * @return the {@link Application} with the given name from the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a {@link Application} with the given name.
	 */
	public final Application<?> getRefApplicationByName(final String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * @return the default {@link Application} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link Application}.
	 */
	public final Application<?> getRefDefaultApplication() {
		
		//Asserts that the current Server contains a default Application.
		assertContainsDefaultApplication();
		
		return defaultApplication;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseServer} has a {@link Client} connected.
	 */
	public final boolean hasClientConnected() {
		return applications.containsAny(Application::hasClientConnected);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method
	/**
	 * Lets the current {@link BaseServer} take the given client.
	 * 
	 * @param client
	 * @throws ArgumentDoesNotHaveAttributeException if the given client does not have a target
	 * and the current {@link BaseServer} does not contain a default {@link Application}.
	 * @throws ArgumentDoesNotHaveAttributeException if the given client has a target
	 * and the current {@link BaseServer} does not contain a {@link Application}
	 * with a name that equals the given target.
	 */
	public final void takeClient(final BackendClient<?> client) {
		
		//Handles the case that the given client does not have a target.
		if (!client.hasTarget()) {
			getRefDefaultApplication().takeClient(client);
			
		//Handles the case that the given client has a target.
		} else {
			getRefApplicationByName(client.getTarget()).takeClient(client);
		}
	}
	
	//method declaration
	/**
	 * Notes that the curent {@link BaseServer} has added the given application.
	 * 
	 * @param application
	 */
	protected abstract void noteAddedApplication(Application<?> application);

	//method declaration
	/**
	 * Notes that the curent {@link BaseServer} has added the given defaultApplication2.
	 * 
	 * @param defaultApplication2
	 */
	protected abstract void noteAddedDefaultApplication(Application<?> defaultApplication2);
	
	//method
	/**
	 * Adds the given application to the list of {@link Application}s of the current {@link BaseServer}.
	 * 
	 * @param application
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the same name as one of the given applications.
	 */
	private void addApplicationToList(final Application<?> application) {
		
		//Asserts that the current Server does not contain already
		//an Application with the same name as the given application..
		assertDoesNotContainApplicationWithName(application.getName());
		
		//Adds the given application to the list of Applications of the current BaseServer.
		applications.addAtEnd(application);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link Application}.
	 */
	private void assertContainsDefaultApplication() {
		if (!containsDefaultApplication()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "default Application");
		}
	}
	
	//method
	/**
	 * @param name
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link Application} with the same name as one of the given applications.
	 */
	private void assertDoesNotContainApplicationWithName(final String name) {
		if (containsApplicationWithName(name)) {
			throw new InvalidArgumentException(this, "contains already an Application with the name '" + name + "'");
		}
	}
}
