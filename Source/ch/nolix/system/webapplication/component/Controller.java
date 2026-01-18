/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.component;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.webapplication.main.WebClientSession;

public abstract class Controller<S> { //NOSONAR: A Controller is a base class without abstract methods.

  private WebClientSession<S> memberWebClientSession;

  protected final S getStoredApplicationService() {
    return getStoredWebClientSession().getStoredApplicationService();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  protected final WebClientSession<S> getStoredWebClientSession() {
    if (memberWebClientSession == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, WebClientSession.class);
    }

    return memberWebClientSession;
  }

  final void setWebClientSession(final WebClientSession<S> webClientSession) {
    Validator.assertThat(webClientSession).thatIsNamed(WebClientSession.class).isNotNull();

    memberWebClientSession = webClientSession;
  }
}
