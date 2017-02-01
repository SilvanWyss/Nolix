//package declaration
package ch.nolix.common.application;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexControllerListener;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * A server contains applications and listens to clients on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 100
 */
public final class Server {
	
	//multiple attribute
	private final List<Application<?>> applications = new List<Application<?>>();
	
	//constructor
	/**
	 * Creates new server that listens to clients on the given port.
	 * 
	 * @param port
	 */
	public Server(final int port) {	
		new DuplexControllerListener(port, new ServerDuplexControllerTaker(this));
	}
	
	//constructor
	/**
	 * Creates new server that:
	 * -Listens to clients on the given port.
	 * -Contains the given applications.
	 * 
	 * @param port
	 * @param applications
	 * @throws NullArgumentException if one of the given applications is null.
	 * @throws InvalidArgumentException if the given applications contains several applications with the same name.
	 */
	public Server(final int port, final Application<?>... applications) {
		
		//Calls other constructor.
		this(port);
		
		addApplication(applications);
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
		ZetaValidator.supposeThat(application).thatIsInstanceOf(Application.class).isNotNull();
		
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
	 * @param name
	 * @return true if this server contains an application with the given name.
	 */
	public final boolean containsApplication(final String name) {
		return applications.contains(a -> a.hasName(name));
	}
	
	//method
	/**
	 * @param name
	 * @return the application with the given name from this server.
	 * @throws RuntimeException if this server contains no application with the given name.
	 */
	public final Application<?> getRefApplicationByName(String name) {
		return applications.getRefFirst(a -> a.hasName(name));
	}
}
