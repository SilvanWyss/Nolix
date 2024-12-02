package ch.nolix.system.application.component;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.webapplication.WebClientSession;

public abstract class Controller<AS> { //NOSONAR: This class is a base type that does not have abstract methods.

  private WebClientSession<AS> webClientSession;

  protected final AS getStoredApplicationContext() {
    return getStoredWebClientSession().getStoredApplicationContext();
  }

  protected final WebClientSession<AS> getStoredWebClientSession() {
    return webClientSession;
  }

  final void internalSetSession(final WebClientSession<AS> webClientSession) {

    GlobalValidator.assertThat(webClientSession).thatIsNamed(WebClientSession.class).isNotNull();

    this.webClientSession = webClientSession;
  }
}
