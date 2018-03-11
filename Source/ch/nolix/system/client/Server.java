//package declaration
package ch.nolix.system.client;

import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A server contains applications and listens to clients on a specific port.
 * A server is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 100
 */
public class Server extends ClosableElement implements Clearable<Server> {
	
	//optional attribute
	private Application<?> arbitraryApplication;
	
	//multiple attribute
	private final List<Application<?>> applications = new List<Application<?>>();
	
	public Server() {}
	
	//constructor
	/**
	 * Creates a new server with the given applications.
	 * 
	 * @param applications
	 * @throws NullArgumentException if one of the given applications is null.
	 * @throws InvalidArgumentException if the given applications contains several applications with the same name.
	 */
	public Server(final Application<?>... applications) {
		addApplication(applications);
	}
	
	//method
	public final void addArbitraryApplication(final Application<?> arbitraryApplication) {
		
		//Checks if this server contains no applications.
		if (containsAny()) {
			throw new InvalidStateException(this, "contains applications");
		}
		
		addApplication(arbitraryApplication);
		
		this.arbitraryApplication = arbitraryApplication;
	}
	
	//method
	/**
	 * Adds the given application to this server.
	 * 
	 * @param application
	 * @throws NullArgumentException if the given application is null.
	 * @throws InvalidArgumentException if this server contains already anapplication with the same name as the given application.
	 */
	public final void addApplication(final Application<?> application) {
		
		//Checks if the given application is not null.
		Validator.suppose(application).thatIsInstanceOf(Application.class).isNotNull();
		
		//Checks if the given  this server contains not already an other application with the same name as the given applicaiton.
		if (containsApplication(application.getName())) {
			throw new InvalidArgumentException(new Argument(this), new ErrorPredicate("contains already an application with the name " + application.getNameInQuotes() + "."));
		}
		
		applications.addAtEnd(application);
	}
	
	//method
	/**
	 * Adds the given applications to this server.
	 * 
	 * @param applications
	 * @throws NullArgumentException if one of the given applications is null.
	 * @throws InvalidArgumentException if this server already contains an other application with the same name as one of the given applications.
	 */
	public final void addApplication(final Application<?>... applications) {
		
		//Iterates the given applications.
		for (Application<?> a: applications) {
			addApplication(a);
		}
	}
	
	//method
	/**
	 * Removes all applications from this server.
	 * 
	 * @return this server.
	 */
	public Server clear() {
		
		applications.clear();
		arbitraryApplication = null;
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if this server contains an application with the given name.
	 */
	public final boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
	}
	
	//method
	/**
	 * @return true if this server has an arbitrary application.
	 */
	public final boolean hasArbitraryApplication() {
		return (arbitraryApplication != null);
	}
	
	//method
	/**
	 * @return true if this server contains no application.
	 */
	public final boolean isEmpty() {
		return applications.isEmpty();
	}
	
	//method
	/**
	 * Lets this server take the given client.
	 * 
	 * @param client
	 */
	public void takeClient(final Client<?> client) {
		
		//Handles the case that this server has no arbitrary application.
		if (!hasArbitraryApplication()) {
			applications
			.getRefFirst(a -> a.hasName(client.internal_getTarget()))
			.takeClient(client);			
		}
		
		//Handles the case that this server has an arbitrary application.
		else {
			getRefArbitraryApplication().takeClient(client);
		}
	}
	
	//method
	/**
	 * @return the arbitrary application of this server.
	 * @throws UnexistingAttributeException if this server has no arbitrary application.
	 */
	protected Application<?> getRefArbitraryApplication() {
		
		//Checks if this server has an arbitrary application.
		if (!hasArbitraryApplication()) {
			throw new UnexistingAttributeException(this, "arbitrary application");
		}
		
		return arbitraryApplication;
	}
	
	//method
	/**
	 * @param name
	 * @return the application with the given name from this server.
	 * @throws RuntimeException if this server contains no application with the given name.
	 */
	protected final Application<?> getRefApplicationByName(String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}
	
	//method
	/**
	 * Lets this server note a closing.
	 */
	protected final void noteClosing() {}
}
