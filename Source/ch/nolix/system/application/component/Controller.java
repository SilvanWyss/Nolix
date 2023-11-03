//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;

//class
public abstract class Controller<AC> { //NOSONAR: This class is a base type that does not have abstract methods.

  //attribute
  private WebClientSession<AC> webClientSession;

  //method
  protected final AC getStoredApplicationContext() {
    return getStoredWebClientSession().getStoredApplicationContext();
  }

  //method
  protected final WebClientSession<AC> getStoredWebClientSession() {
    return webClientSession;
  }

  //method
  final void internalSetSession(final WebClientSession<AC> webClientSession) {

    GlobalValidator.assertThat(webClientSession).thatIsNamed(WebClientSession.class).isNotNull();

    this.webClientSession = webClientSession;
  }
}
