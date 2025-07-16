package ch.nolix.system.webapplication.component;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.webapplication.main.WebClientSession;

public abstract class Controller<AS> { //NOSONAR: This class is a base type that does not have abstract methods.

  private WebClientSession<AS> webClientSession;

  protected final AS getStoredApplicationService() {
    return getStoredWebClientSession().getStoredApplicationService();
  }

  protected final WebClientSession<AS> getStoredWebClientSession() {
    return webClientSession;
  }

  final void internalSetSession(final WebClientSession<AS> webClientSession) {

    Validator.assertThat(webClientSession).thatIsNamed(WebClientSession.class).isNotNull();

    this.webClientSession = webClientSession;
  }
}
