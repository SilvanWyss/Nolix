package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;

/**
 * @author Silvan Wyss
 * @version 2022-03-18
 * @param <BC> is the type of a {@link BackendClient}.
 * @param <AS> is the type of the context of the parent {@link Application} of a
 *             {@link BackendClient}.
 */
public abstract class BackendClient<BC extends BackendClient<BC, AS>, AS> extends Client<BC> {

  @SuppressWarnings("unchecked")
  private final BackendClientSessionManager<BC, AS> sessionManager = BackendClientSessionManager.forClient((BC) this);

  /**
   * The {@link Application} the current {@link BackendClient} belongs to.
   */
  private Application<BC, AS> parentApplication;

  /**
   * @return the name of the parent {@link Application} of the current
   *         {@link BackendClient}.
   */
  public final String getApplicationName() {
    return getStoredParentApplication().getInstanceName();
  }

  /**
   * @return the context of the parent {@link Application} of the current
   *         {@link BackendClient}.
   */
  public final AS getStoredApplicationContext() {
    return getStoredParentApplication().getStoredApplicationService();
  }

  /**
   * @return the parent {@link Application} of the current {@link BackendClient}.
   * @throws InvalidArgumentException if the current {@link BackendClient} does
   *                                  not reference its parent
   *                                  {@link Application}.
   */
  public final Application<BC, AS> getStoredParentApplication() {

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
   * @return the current {@link Session} of the current {@link BackendClient}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BackendClient} does not
   *                                               have a current {@link Session}.
   */
  protected final Session<BC, AS> getStoredCurrentSession() {
    return sessionManager.getStoredCurrentSession();
  }

  /**
   * Sets the {@link EndPoint} of the current {@link BackendClient}.
   * 
   * @param endPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link BackendClient} is
   *                                  already connected.
   */
  protected final void setEndPoint(final EndPoint endPoint) {
    internalSetEndPoint(endPoint);
  }

  /**
   * @return the size of the {@link Session} stack of the current
   *         {@link BackendClient}.
   */
  final int internalGetSessionStackSize() {
    return sessionManager.getSessionStackSize();
  }

  /**
   * Pops the current {@link Session} of the current {@link BackendClient} from
   * the current {@link BackendClient}. Closes the current {@link BackendClient}
   * if the current {@link Session} of the current {@link BackendClient} was the
   * last {@link Session} of the current {@link BackendClient}.
   * 
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link BackendClient} is not the top
   *                           {@link Session} of the current
   *                           {@link BackendClient}.
   */
  final void internalPopCurrentSession() {
    sessionManager.popCurrentSession();
  }

  /**
   * Pops the current {@link Session} of the current {@link BackendClient} from
   * the current {@link BackendClient} Forwards the given result. Closes the
   * current {@link BackendClient} if the current {@link Session} of the current
   * {@link BackendClient} was the last {@link Session} of the current
   * {@link BackendClient}.
   * 
   * @param result
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link BackendClient} is not the top
   *                           {@link Session} of the current
   *                           {@link BackendClient}.
   */
  final void internalPopCurrentSessionAndForwardGivenResult(final Object result) {
    sessionManager.popCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * Pushes the given session to the current {@link BackendClient}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  final void internalPush(final Session<BC, AS> session) {
    sessionManager.pushSession(session);
  }

  /**
   * Pushes the given session to the current {@link BackendClient}.
   * 
   * @param session
   * @param <R>     is the type of the returned result.
   * @return the result from the given session.
   * @throws ArgumentIsNullException if the given session is null.
   */
  final <R> R internalPushAndGetResult(final Session<BC, AS> session) {
    return sessionManager.pushSessionAndGetResult(session);
  }

  /**
   * Sets the current {@link Session} of the current {@link BackendClient}. That
   * means the current {@link Session} of the current {@link BackendClient} will
   * be popped from the current {@link BackendClient} and the given session will
   * be pushed to the current {@link BackendClient}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  final void internalSetCurrentSession(final Session<BC, AS> session) {
    sessionManager.setCurrentSession(session);
  }

  /**
   * Sets the {@link Application} the current {@link BackendClient} will belong
   * to.
   * 
   * @param parentApplication
   * @throws ArgumentIsNullException  if the given parentApplication is null.
   * @throws InvalidArgumentException if the current {@link BackendClient}
   *                                  references already its parent
   *                                  {@link Application}.
   */
  final void internalSetParentApplication(final Application<BC, AS> parentApplication) {

    //Asserts that the given parent application is not null.
    GlobalValidator.assertThat(parentApplication).thatIsNamed("parent application").isNotNull();

    //Asserts that the current client does not reference its parent application.
    assertDoesNotReferenceParentApplication();

    //Sets the parent Application of the current Client.
    this.parentApplication = parentApplication;
  }

  /**
   * @throws InvalidArgumentException if the current {@link BackendClient}
   *                                  references already its parent
   *                                  {@link Application}.
   */
  private void assertDoesNotReferenceParentApplication() {
    if (referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "references already its parent application");
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link BackendClient} does
   *                                  not reference its parent
   *                                  {@link Application}.
   */
  private void assertReferencesParentApplication() {
    if (!referencesParentApplication()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not reference its parent application");
    }
  }

  /**
   * @return true if the current {@link BackendClient} references its parent
   *         {@link Application}.
   */
  private boolean referencesParentApplication() {
    return (parentApplication != null);
  }
}
