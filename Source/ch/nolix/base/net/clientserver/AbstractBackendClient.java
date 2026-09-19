/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.baseapi.net.clientserver.BackendClient;
import ch.nolix.baseapi.net.clientserver.Session;
import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractBackendClient}.
 * @param <S> the type of the application service of the {@link Application} of
 *            a {@link AbstractBackendClient}.
 */
public abstract class AbstractBackendClient<C extends AbstractBackendClient<C, S>, S>
extends AbstractClient
implements BackendClient<C, S> {
  @SuppressWarnings("unchecked")
  private final BackendClientSessionManager<C, S> sessionManager = BackendClientSessionManager.forClient((C) this);

  private Application<C, S> optionalParentApplication;

  /**
   * @return the name of the parent {@link AbstractApplication} of the current
   *         {@link AbstractBackendClient}.
   */
  public final String getApplicationName() {
    return getStoredParentApplication().getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IApplicationTarget getApplicationAsTarget() {
    return getStoredParentApplication().toTarget();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getSessionStackSize() {
    return sessionManager.getSessionStackSize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredApplicationService() {
    return getStoredParentApplication().getStoredApplicationService();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalPopCurrentSession() {
    sessionManager.popCurrentSession();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalPopCurrentSessionWithResult(final Object result) {
    sessionManager.popCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalPushSession(final Session<C, S> session) {
    sessionManager.pushSession(session);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Object internalPushSessionAndGetResult(final Session<C, S> session) {
    return sessionManager.pushSessionAndGetResult(session);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetNextSession(final Session<C, S> session) {
    sessionManager.setCurrentSession(session);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetParentApplication(final Application<C, S> parentApplication) {
    Validator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();
    assertDoesNotBelongToApplication();

    // Sets the parent Application of the current Client.
    optionalParentApplication = parentApplication;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBackendClient() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFrontendClient() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    while (sessionManager.containsCurrentSession()) {
      sessionManager.popCurrentSession();
    }
  }

  /**
   * @return the current {@link AbstractSession} of the current
   *         {@link AbstractBackendClient}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractBackendClient}
   *                                               does not have a current
   *                                               {@link AbstractSession}.
   */
  protected final Session<C, S> getStoredCurrentSession() {
    return sessionManager.getStoredCurrentSession();
  }

  /**
   * @throws RuntimeException if the current {@link AbstractBackendClient} does
   *                          not have a parent {@link Application}
   */
  private void assertBelongsToApplication() {
    if (!belongsToApplication()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Application.class);
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractBackendClient} belongs
   *                          to an {@link Application}
   */
  private void assertDoesNotBelongToApplication() {
    if (belongsToApplication()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentApplication());
    }
  }

  /**
   * @return true if the current {@link AbstractBackendClient} references its
   *         parent {@link AbstractApplication}, false otherwise
   */
  private boolean belongsToApplication() {
    return (optionalParentApplication != null);
  }

  /**
   * @return the parent {@link Application} of the current
   *         {@link AbstractBackendClient}
   * @throws RuntimeException if the current {@link AbstractBackendClient} does
   *                          not have a parent {@link AbstractApplication}
   */
  private Application<C, S> getStoredParentApplication() {
    assertBelongsToApplication();

    return optionalParentApplication;
  }
}
