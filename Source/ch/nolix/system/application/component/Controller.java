//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.main.Session;
import ch.nolix.system.application.webapplication.WebClientSession;

//class
public abstract class Controller<AC> { //NOSONAR: This class is a base type that does not have abstract methods.
	
	//attribute
	private final WebClientSession<AC> session;
	
	//constructor
	protected Controller(final WebClientSession<AC> session) {
		
		GlobalValidator.assertThat(session).thatIsNamed(Session.class).isNotNull();
		
		this.session = session;
	}
	
	//method
	protected final AC getOriApplicationContext() {
		return getOriSession().getOriApplicationContext();
	}
	
	//method
	protected final WebClientSession<AC> getOriSession() {
		return session;
	}
}
