package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.component.applicationcomponent.IClientComponent;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrol.trigger.IRefreshableSubscriber;

/**
 * A {@link AbstractSession} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @param <C> is the type of the {@link AbstractBackendClient} of a
 *            {@link AbstractSession}.
 * @param <S> is the type of the context of the parent {@link Application} of
 *            the parent {@link AbstractBackendClient} of a
 *            {@link AbstractSession}.
 */
public abstract class AbstractSession<C extends AbstractBackendClient<C, S>, S>
implements IClientComponent<C>, IRefreshableSubscriber {
  private C memberParentClient;

  private Object memberResult;

  /**
   * @return true if the current {@link AbstractSession} belongs to a
   *         {@link AbstractClient}, false otherwise.
   */
  @Override
  public final boolean belongsToClient() {
    return (memberParentClient != null);
  }

  /**
   * @return the name of the parent {@link Application} of the parent
   *         {@link AbstractClient} of the current {@link AbstractSession}.
   */
  public final String getApplicationName() {
    return getStoredParentClient().getApplicationName();
  }

  /**
   * @return the context of the parent {@link Application} of the parent
   *         {@link AbstractClient} of the current {@link AbstractSession}.
   */
  public final S getStoredApplicationService() {
    return getStoredParentClient().getStoredApplicationService();
  }

  /**
   * @return the parent client of the current {@link AbstractSession}.
   * @throws InvalidArgumentException if the current {@link AbstractSession} does
   *                                  not belong to a client.
   */
  @Override
  public final C getStoredParentClient() {
    //Asserts that the current {@link Session} belonts to a client.
    assertBelongsToClient();

    return memberParentClient;
  }

  public final boolean hasParentSession() {
    return (getStoredParentClient().internalGetSessionStackSize() > 1);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isAlive() {
    return memberParentClient != null
    && memberParentClient.isOpen();
  }

  /**
   * Pops the current {@link AbstractSession} from its parent
   * {@link AbstractClient}.
   */
  public final void pop() {
    getStoredParentClient().internalPopCurrentSession();
  }

  /**
   * Pops the current {@link AbstractSession} from its parent
   * {@link AbstractClient} with the given result.
   * 
   * @param result
   * @throws ArgumentIsNullException if the given result is null.
   */
  public final void pop(final Object result) {
    getStoredParentClient().internalPopCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link AbstractSession}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void push(final AbstractSession<C, S> session) {
    getStoredParentClient().internalPush(session);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link AbstractSession}.
   * 
   * @param session
   * @param <R>     is the type of the returned result.
   * @return the result from the given session.
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final <R> R pushAndGetResult(final AbstractSession<C, S> session) {
    return getStoredParentClient().internalPushAndGetResult(session);
  }

  /**
   * Sets the next session of the parent {@link AbstractClient} of the current
   * {@link AbstractSession}. That means the current {@link AbstractSession} will
   * be popped from its parent {@link AbstractClient} and the given session is
   * pushed to the parent {@link AbstractClient} of the current
   * {@link AbstractSession}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void setNext(final AbstractSession<C, S> session) {
    getStoredParentClient().internalSetCurrentSession(session);
  }

  /**
   * Initializes the current {@link AbstractSession}.
   */
  protected abstract void fullInitialize();

  /**
   * @return the {@link AbstractClient} class of the current
   *         {@link AbstractSession}.
   */
  protected abstract Class<?> getClientClass();

  final Object internalGetStoredResult() {
    if (memberResult == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.RESULT);
    }

    return memberResult;
  }

  /**
   * Removes the parent client of the current {@link AbstractSession}.
   */
  final void internalRemoveParentClient() {
    memberParentClient = null;
  }

  /**
   * Sets the parent client of the current {@link AbstractSession}.
   * 
   * @param parentClient
   * @throws ArgumentIsNullException  if the given parent client is null.
   * @throws InvalidArgumentException if the current {@link AbstractSession}
   *                                  belongs to a client.
   */
  final void internalSetParentClient(C parentClient) {
    //Asserts that the given client is not null.
    Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    //Asserts that the current session does not belong to a client.
    assertDoesNotBelongToClient();

    //Sets the parent client of the current session.
    memberParentClient = parentClient;
  }

  final void internalSetResult(final Object result) {
    Validator.assertThat(result).thatIsNamed(LowerCaseVariableCatalog.RESULT).isNotNull();

    memberResult = result;
  }

  /**
   * @throws InvalidArgumentException if the current {@link AbstractSession} does
   *                                  not belong to a client.
   */
  private void assertBelongsToClient() {
    //Asserts that the current {@link Session} belongs to a client.
    if (!belongsToClient()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not belong to a client");
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link AbstractSession}
   *                                  belongs to a client.
   */
  private void assertDoesNotBelongToClient() {
    //Asserts that the current {@link Session} does not belong to a client.
    if (belongsToClient()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "belongs to a client");
    }
  }
}
