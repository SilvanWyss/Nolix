package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;

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
  public final S getStoredApplicationContext() {
    return getStoredParentApplication().getStoredApplicationService();
  }

  /**
   * @return the parent {@link Application} of the current {@link AbstractBackendClient}.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient} does
   *                                  not reference its parent
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
   * @return the current {@link Session} of the current {@link AbstractBackendClient}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractBackendClient} does not
   *                                               have a current {@link Session}.
   */
  protected final Session<C, S> getStoredCurrentSession() {
    return sessionManager.getStoredCurrentSession();
  }

  /**
   * Sets the {@link EndPoint} of the current {@link AbstractBackendClient}.
   * 
   * @param endPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient} is
   *                                  already connected.
   */
  protected final void setEndPoint(final EndPoint endPoint) {
    internalSetEndPoint(endPoint);
  }

  /**
   * @return the size of the {@link Session} stack of the current
   *         {@link AbstractBackendClient}.
   */
  final int internalGetSessionStackSize() {
    return sessionManager.getSessionStackSize();
  }

  /**
   * Pops the current {@link Session} of the current {@link AbstractBackendClient} from
   * the current {@link AbstractBackendClient}. Closes the current {@link AbstractBackendClient}
   * if the current {@link Session} of the current {@link AbstractBackendClient} was the
   * last {@link Session} of the current {@link AbstractBackendClient}.
   * 
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link AbstractBackendClient} is not the top
   *                           {@link Session} of the current
   *                           {@link AbstractBackendClient}.
   */
  final void internalPopCurrentSession() {
    sessionManager.popCurrentSession();
  }

  /**
   * Pops the current {@link Session} of the current {@link AbstractBackendClient} from
   * the current {@link AbstractBackendClient} Forwards the given result. Closes the
   * current {@link AbstractBackendClient} if the current {@link Session} of the current
   * {@link AbstractBackendClient} was the last {@link Session} of the current
   * {@link AbstractBackendClient}.
   * 
   * @param result
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link AbstractBackendClient} is not the top
   *                           {@link Session} of the current
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
  final void internalPush(final Session<C, S> session) {
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
  final <R> R internalPushAndGetResult(final Session<C, S> session) {
    return sessionManager.pushSessionAndGetResult(session);
  }

  /**
   * Sets the current {@link Session} of the current {@link AbstractBackendClient}. That
   * means the current {@link Session} of the current {@link AbstractBackendClient} will
   * be popped from the current {@link AbstractBackendClient} and the given session will
   * be pushed to the current {@link AbstractBackendClient}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  final void internalSetCurrentSession(final Session<C, S> session) {
    sessionManager.setCurrentSession(session);
  }

  /**
   * Sets the {@link Application} the current {@link AbstractBackendClient} will belong
   * to.
   * 
   * @param parentApplication
   * @throws ArgumentIsNullException  if the given parentApplication is null.
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient}
   *                                  references already its parent
   *                                  {@link Application}.
   */
  final void internalSetParentApplication(final Application<C, S> parentApplication) {

    //Asserts that the given parent application is not null.
    GlobalValidator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();

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
   * @throws InvalidArgumentException if the current {@link AbstractBackendClient} does
   *                                  not reference its parent
   *                                  {@link Application}.
   */
  private void assertReferencesParentApplication() {
    if (!referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not reference its parent application");
    }
  }

  /**
   * @return true if the current {@link AbstractBackendClient} references its parent
   *         {@link Application}.
   */
  private boolean referencesParentApplication() {
    return (parentApplication != null);
  }
}
