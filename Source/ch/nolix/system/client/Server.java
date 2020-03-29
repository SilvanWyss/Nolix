//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Server} contains {@link Application}s.
 * A {@link Server} is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 210
 */
public class Server extends ClosableElement implements Clearable<Server> {
	
	//optional attribute
	private Application<?> mainApplication;
	
	//multi-attribute
	private final LinkedList<Application<?>> applications = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Server}.
	 */
	public Server() {}
	
	//constructor
	/**
	 * Creates a new {@link Server} with the given applications.
	 * 
	 * @param applications
	 * @throws ArgumentIsNullException if one of the given applications is null.
	 * @throws InvalidArgumentException
	 * if the given applications contains several {@link Application}s with the same name.
	 */
	public Server(final Application<?>... applications) {
		addApplication(applications);
	}
	
	//method
	/**
	 * Adds the given {@link application} to the current {@link Server}.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the current {@link Server} contains already a {@link Application}
	 * with the same name as the given application.
	 */
	public final void addApplication(final Application<?> application) {
		
		//Checks if the given application is not null.
		Validator.suppose(application).isOfType(Application.class);
		
		//Checks if the given the current Server
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
	 * Adds the given mainApplication to the current {@link Server}.
	 * A main {@link Application} will take all {@link Client}s without target.
	 * 
	 * @param mainApplication
	 * @throws ArgumentIsNullException if the given mainApplication is null.
	 * @throws InvalidArgumentException if the current {@link Server} contains already a {@link Application}
	 * with the same name as the given mainApplication.
	 */
	public final void addMainApplication(final Application<?> mainApplication) {
		
		//Checks if the current Server does not contain already a main Application.
		if (containsMainApplication()) {
			throw new InvalidArgumentException(this, "contains already a main Application");
		}
		
		addApplication(mainApplication);
		
		this.mainApplication = mainApplication;
	}
		
	//method
	/**
	 * Adds the given applications to the current {@link Server}.
	 * 
	 * @param applications
	 * @throws ArgumentIsNullException if one of the given applications is null.
	 * @throws InvalidArgumentException if the current {@link Server}
	 * contains already an other application with the same name as one of the given applications.
	 */
	public final void addApplication(final Application<?>... applications) {
		
		//Iterates the given applications.
		for (Application<?> a: applications) {
			addApplication(a);
		}
	}
	
	//method
	/**
	 * Removes all applications of the current {@link Server}.
	 * 
	 * @return the current {@link Server}.
	 */
	@Override
	public Server clear() {
		
		applications.clear();
		mainApplication = null;
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link Server} contains a {@link Application} with the given name.
	 */
	public final boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
	}
	
	//method
	/**
	 * @return true if the current {@link Server} contains a main {@link Application}.
	 */
	public final boolean containsMainApplication() {
		return (mainApplication != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Server} does not contain a {@link Application}.
	 */
	@Override
	public final boolean isEmpty() {
		return applications.isEmpty();
	}
	
	//method
	/**
	 * Lets the current {@link Server} take the given client.
	 * 
	 * @param client
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the given client does not have a target
	 * and if the current {@link Server} does not contain a main {@link Applicaiton}.
	 */
	public void takeClient(final Client<?> client) {
		
		//Handles the case that the given client does not have a target.
		if (!client.hasTarget()) {
			getRefMainApplication().takeClient(client);
		}
		
		//Handles the case that the given client has a target.
		else {
			getRefApplicationByName(client.getTarget()).takeClient(client);
		}
	}
	
	//method
	/**
	 * @param name
	 * @return the {@link Application} with the given name from the current {@link Server}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Server} does not contain a {@link Application} with the given name.
	 */
	final Application<?> getRefApplicationByName(final String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}
	
	//method
	/**
	 * @return the main {@link Application} of the current {@link Server}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Server} does not contain a main {@link Application}.
	 */
	final Application<?> getRefMainApplication() {
		
		//Checks if the current Server contains a main Application.
		if (!containsMainApplication()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "main Application");
		}
		
		return mainApplication;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteClose() {}
}
