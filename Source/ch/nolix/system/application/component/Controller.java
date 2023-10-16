//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;

//class
public abstract class Controller<AC> { //NOSONAR: This class is a base type that does not have abstract methods.

  //attribute
  private WebClientSession<AC> session;

  //method
  protected final AC getStoredApplicationContext() {
    return getStoredSession().getStoredApplicationContext();
  }

  //method
  protected final WebClientSession<AC> getStoredSession() {
    return session;
  }

  //method
  final void internalSetSession(final WebClientSession<AC> session) {

    GlobalValidator.assertThat(session).thatIsNamed("session").isNotNull();

    this.session = session;
  }
}
