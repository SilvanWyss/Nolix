/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
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

  /**
   * The {@link AbstractApplication} the current {@link AbstractBackendClient}
   * belongs to.
   */
  private AbstractApplication<C, S> memberParentApplication;

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
   * Sets the {@link AbstractApplication} the current
   * {@link AbstractBackendClient} will belong to.
   * 
   * @param parentApplication
   * @throws RuntimeException if the given parentApplication is null
   * @throws RuntimeException if the current {@link AbstractBackendClient}
   *                          references already its parent
   *                          {@link AbstractApplication}.
   */
  final void internalSetParentApplication(final AbstractApplication<C, S> parentApplication) {
    // Asserts that the given parent application is not null.
    Validator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();

    // Asserts that the current client does not reference its parent application.
    assertDoesNotReferenceParentApplication();

    // Sets the parent Application of the current Client.
    memberParentApplication = parentApplication;
  }

  /**
   * @throws RuntimeException if the current {@link AbstractBackendClient}
   *                          references already its parent
   *                          {@link AbstractApplication}.
   */
  private void assertDoesNotReferenceParentApplication() {
    if (referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "references already its parent application");
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractBackendClient} does
   *                          not reference its parent
   *                          {@link AbstractApplication}.
   */
  private void assertReferencesParentApplication() {
    if (!referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not reference its parent application");
    }
  }

  /**
   * @return the parent {@link AbstractApplication} of the current
   *         {@link AbstractBackendClient}
   * @throws RuntimeException if the current {@link AbstractBackendClient} does
   *                          not have a parent {@link AbstractApplication}.
   */
  private AbstractApplication<C, S> getStoredParentApplication() {
    assertReferencesParentApplication();

    return memberParentApplication;
  }

  /**
   * @return true if the current {@link AbstractBackendClient} references its
   *         parent {@link AbstractApplication}, false otherwise
   */
  private boolean referencesParentApplication() {
    return (memberParentApplication != null);
  }
}
