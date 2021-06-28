//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;

//class
/**
 * A {@link BaseServer} can contain {@link Application}s.
 * A {@link BaseServer} is closable.
 * 
 * @author Silvan Wyss
 * @date 2016-11-01
 * @lines 220
 */
public abstract class BaseServer implements ICloseableElement {
	
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
	 * @throws InvalidArgumentException if the current {@link BaseServer}
	 * contains already a {@link Application} with the same name as the given application.
	 */
	public final void addApplication(final Application<?> application) {
		
		//Asserts that the given application is not null.
		Validator.assertThat(application).isOfType(Application.class);
		
		//Asserts that the current Server
		//does not contain already an Application with the same name as the given application..
		if (containsApplication(application.getName())) {
			throw
			new InvalidArgumentException(
				this,
				"contains already an Application with the name " + application.getNameInQuotes()
			);
		}
		
		applications.addAtEnd(application);
	}
	
	//method
	/**
	 * Adds the given applications to the current {@link BaseServer}.
	 * 
	 * @param applications
	 * @throws ArgumentIsNullException if one of the given applications is null.
	 * @throws InvalidArgumentException if the current {@link BaseServer}
	 * contains already an other application with the same name as one of the given applications.
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
	 * @throws InvalidArgumentException
	 * if the current {@link BaseServer} contains already a {@link Application} with the given name.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public final void addApplication(final String name, Class<?> initialSessionClass) {
		
		//Calls other method.
		addApplication(new Application<>(name, initialSessionClass));
	}
	
	//method
	/**
	 * Adds the given defaultApplication to the current {@link BaseServer}.
	 * A default {@link Application} will take the {@link Client}s without target.
	 * 
	 * @param defaultApplication
	 * @throws ArgumentIsNullException if the given defaultApplication is null.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already a default {@link Application}.
	 * @throws InvalidArgumentException if the current {@link BaseServer}
	 * contains already a {@link Application} with the same name as the given defaultApplication.
	 */
	public final void addDefaultApplication(final Application<?> defaultApplication) {
		
		//Asserts that the current Server does not contain already a default Application.
		if (containsDefaultApplication()) {
			throw new InvalidArgumentException(this, "contains already a default Application");
		}
		
		addApplication(defaultApplication);
		
		this.defaultApplication = defaultApplication;
	}
	
	/**
	 * Adds a new default {@link Application} with the given name and initialSessionClass to the current {@link BaseServer}.
	 * 
	 * @param name
	 * @param initialSessionClass
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already a default {@link Application}.
	 * @throws InvalidArgumentException
	 * if the current {@link BaseServer} contains already a {@link Application} with the given name.
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given initialSessionClass is null.
	 */
	public final void addDefaultApplication(final String name, Class<?> initialSessionClass) {
		
		//Calls other method.
		addDefaultApplication(new Application<>(name, initialSessionClass));
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link BaseServer} contains a {@link Application} with the given name.
	 */
	public final boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
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
	 * @return the {@link Application} with the given name from the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BaseServer} does not contain a {@link Application} with the given name.
	 */
	public final Application<?> getRefApplication(final String name) {
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
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BaseServer} does not contain a default {@link Application}.
	 */
	public final Application<?> getRefDefaultApplication() {
		
		//Asserts that the current Server contains a default Application.
		if (!containsDefaultApplication()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "default Application");
		}
		
		return defaultApplication;
	}

	//method
	/**
	 * @return true if the current {@link BaseServer} has a {@link Client} connected.
	 */
	public final boolean hasClientConnected() {
		return applications.contains(Application::hasClientConnected);
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
	 */
	public final void takeClient(final Client<?> client) {
		
		//Handles the case that the given client does not have a target.
		if (!client.hasTarget()) {
			getRefDefaultApplication().takeClient(client);
			
		//Handles the case that the given client has a target.
		} else {
			getRefApplication(client.getTarget()).takeClient(client);
		}
	}
}
