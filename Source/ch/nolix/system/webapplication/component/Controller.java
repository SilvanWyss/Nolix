/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.component;

import ch.nolix.base.net.clientserver.AbstractBackendClient;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.system.webapplication.main.WebClientSession;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of the {@link Application} of
 *            the {@link AbstractBackendClient} of the {@link WebClientSession}
 *            of a {@link Controller}.
 */
public abstract class Controller<S> { // NOSONAR: A Controller is a base class without abstract methods.

  private WebClientSession<S> memberWebClientSession;

  protected final S getStoredApplicationService() {
    return getStoredWebClientSession().getStoredApplicationService();
  }

  // For a better performance, this implementation does not use all available comfort methods.
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
