package ch.nolix.system.application.component;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;

public abstract class Controller<AC> { //NOSONAR: This class is a base type that does not have abstract methods.

  private WebClientSession<AC> webClientSession;

  protected final AC getStoredApplicationContext() {
    return getStoredWebClientSession().getStoredApplicationContext();
  }

  protected final WebClientSession<AC> getStoredWebClientSession() {
    return webClientSession;
  }

  final void internalSetSession(final WebClientSession<AC> webClientSession) {

    GlobalValidator.assertThat(webClientSession).thatIsNamed(WebClientSession.class).isNotNull();

    this.webClientSession = webClientSession;
  }
}
