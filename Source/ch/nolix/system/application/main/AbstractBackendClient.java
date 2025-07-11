package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.endpoint3.AbstractEndPoint;

/**
 * @author Silvan Wyss
 * @version 2022-03-18
 * @param <C> is the type of a {@link AbstractBackendClient}.
 * @param <S> is the type of the context of the parent {@link Application} of a
 *            {@link AbstractBackendClient}.
 */
public abstract class AbstractBackendClient<C extends AbstractBackendClient<C, S>, S> extends AbstractClient<C> {

  @SuppressWarnings("unchecked")
  private final BackendClientSessionManager<C, S> sessionManager = BackendClientSessionManager.forClient((C) this);

  /**
   * The {@link Application} the current {@link AbstractBackendClient} belongs to.
   */
  private Application<C, S> parentApplication;

  /**
   * @return the name of the parent {@link Application} of the current
   *         {@link AbstractBackendClient}.
   */
  public final String getApplicationName() {
    return getStoredParentApplication().getInstanceName();
  }

  /**
   * @return the context of the parent {@link Application} of the current
   *         {@link AbstractBackendClient}.
   */
  public final S getStoredApplicationService() {
    return getStoredParentApplication().getStoredApplicationService();
  }

  /**
   * @return the parent {@link Application} of the current
   *         {@link AbstractBackendClient}.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  does not reference its parent
   *                                  {@link Application}.
   */
  public final Application<C, S> getStoredParentApplication() {

    assertReferencesParentApplication();

    return parentApplication;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isOnBackend() {
    return true;
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
   *         {@link AbstractBackendClient}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractBackendClient}
   *                                               does not have a current
   *                                               {@link AbstractSession}.
   */
  protected final AbstractSession<C, S> getStoredCurrentSession() {
    return sessionManager.getStoredCurrentSession();
  }

  /**
   * Sets the {@link AbstractEndPoint} of the current
   * {@link AbstractBackendClient}.
   * 
   * @param abstractEndPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  is already connected.
   */
  protected final void setEndPoint(final AbstractEndPoint abstractEndPoint) {
    internalSetEndPoint(abstractEndPoint);
  }

  /**
   * @return the size of the {@link AbstractSession} stack of the current
   *         {@link AbstractBackendClient}.
   */
  final int internalGetSessionStackSize() {
    return sessionManager.getSessionStackSize();
  }

  /**
   * Pops the current {@link AbstractSession} of the current
   * {@link AbstractBackendClient} from the current {@link AbstractBackendClient}.
   * Closes the current {@link AbstractBackendClient} if the current
   * {@link AbstractSession} of the current {@link AbstractBackendClient} was the
   * last {@link AbstractSession} of the current {@link AbstractBackendClient}.
   * 
   * @InvalidArgumentException if the current {@link AbstractSession} of the
   *                           current {@link AbstractBackendClient} is not the
   *                           top {@link AbstractSession} of the current
   *                           {@link AbstractBackendClient}.
   */
  final void internalPopCurrentSession() {
    sessionManager.popCurrentSession();
  }

  /**
   * Pops the current {@link AbstractSession} of the current
   * {@link AbstractBackendClient} from the current {@link AbstractBackendClient}
   * Forwards the given result. Closes the current {@link AbstractBackendClient}
   * if the current {@link AbstractSession} of the current
   * {@link AbstractBackendClient} was the last {@link AbstractSession} of the
   * current {@link AbstractBackendClient}.
   * 
   * @param result
   * @InvalidArgumentException if the current {@link AbstractSession} of the
   *                           current {@link AbstractBackendClient} is not the
   *                           top {@link AbstractSession} of the current
   *                           {@link AbstractBackendClient}.
   */
  final void internalPopCurrentSessionAndForwardGivenResult(final Object result) {
    sessionManager.popCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * Pushes the given session to the current {@link AbstractBackendClient}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  final void internalPush(final AbstractSession<C, S> session) {
    sessionManager.pushSession(session);
  }

  /**
   * Pushes the given session to the current {@link AbstractBackendClient}.
   * 
   * @param session
   * @param <R>     is the type of the returned result.
   * @return the result from the given session.
   * @throws ArgumentIsNullException if the given session is null.
   */
  final <R> R internalPushAndGetResult(final AbstractSession<C, S> session) {
    return sessionManager.pushSessionAndGetResult(session);
  }

  /**
   * Sets the current {@link AbstractSession} of the current
   * {@link AbstractBackendClient}. That means the current {@link AbstractSession}
   * of the current {@link AbstractBackendClient} will be popped from the current
   * {@link AbstractBackendClient} and the given session will be pushed to the
   * current {@link AbstractBackendClient}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  final void internalSetCurrentSession(final AbstractSession<C, S> session) {
    sessionManager.setCurrentSession(session);
  }

  /**
   * Sets the {@link Application} the current {@link AbstractBackendClient} will
   * belong to.
   * 
   * @param parentApplication
   * @throws ArgumentIsNullException  if the given parentApplication is null.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  references already its parent
   *                                  {@link Application}.
   */
  final void internalSetParentApplication(final Application<C, S> parentApplication) {

    //Asserts that the given parent application is not null.
    Validator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();

    //Asserts that the current client does not reference its parent application.
    assertDoesNotReferenceParentApplication();

    //Sets the parent Application of the current Client.
    this.parentApplication = parentApplication;
  }

  /**
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  references already its parent
   *                                  {@link Application}.
   */
  private void assertDoesNotReferenceParentApplication() {
    if (referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "references already its parent application");
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  does not reference its parent
   *                                  {@link Application}.
   */
  private void assertReferencesParentApplication() {
    if (!referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not reference its parent application");
    }
  }

  /**
   * @return true if the current {@link AbstractBackendClient} references its
   *         parent {@link Application}.
   */
  private boolean referencesParentApplication() {
    return (parentApplication != null);
  }
}
