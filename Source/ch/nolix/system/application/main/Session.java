package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;

/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of the {@link BackendClient} of a {@link Session}.
 * @param <S> is the type of the context of the parent {@link Application} of
 *            the parent {@link BackendClient} of a {@link Session}.
 */
public abstract class Session<C extends BackendClient<C, S>, S> implements IRefreshableSubscriber {

  private C parentClient;

  private Object result;

  /**
   * @return true if the current {@link Session} belongs to a {@link AbstractClient}.
   */
  public final boolean belongsToClient() {
    return (parentClient != null);
  }

  /**
   * @return the name of the parent {@link Application} of the parent
   *         {@link AbstractClient} of the current {@link Session}.
   */
  public final String getApplicationName() {
    return getStoredParentClient().getApplicationName();
  }

  /**
   * @return the context of the parent {@link Application} of the parent
   *         {@link AbstractClient} of the current {@link Session}.
   */
  public final S getStoredApplicationContext() {
    return getStoredParentApplication().getStoredApplicationService();
  }

  /**
   * @return the parent client of the current {@link Session}.
   * @throws InvalidArgumentException if the current {@link Session} does not
   *                                  belong to a client.
   */
  public final C getStoredParentClient() {

    //Asserts that the current {@link Session} belonts to a client.
    assertBelongsToClient();

    return parentClient;
  }

  public final boolean hasParentSession() {
    return (getStoredParentClient().internalGetSessionStackSize() > 1);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isAlive() {
    return parentClient != null
    && parentClient.isOpen();
  }

  /**
   * Pops the current {@link Session} from its parent {@link AbstractClient}.
   */
  public final void pop() {
    getStoredParentClient().internalPopCurrentSession();
  }

  /**
   * Pops the current {@link Session} from its parent {@link AbstractClient} with the
   * given result.
   * 
   * @param result
   * @throws ArgumentIsNullException if the given result is null.
   */
  public final void pop(final Object result) {
    getStoredParentClient().internalPopCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link Session}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void push(final Session<C, S> session) {
    getStoredParentClient().internalPush(session);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link Session}.
   * 
   * @param session
   * @param <R>     is the type of the returned result.
   * @return the result from the given session.
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final <R> R pushAndGetResult(final Session<C, S> session) {
    return getStoredParentClient().internalPushAndGetResult(session);
  }

  /**
   * Sets the next session of the parent {@link AbstractClient} of the current
   * {@link Session}. That means the current {@link Session} will be popped from
   * its parent {@link AbstractClient} and the given session is pushed to the parent
   * {@link AbstractClient} of the current {@link Session}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void setNext(final Session<C, S> session) {
    getStoredParentClient().internalSetCurrentSession(session);
  }

  /**
   * Initializes the current {@link Session}.
   */
  protected abstract void fullInitialize();

  /**
   * @return the {@link AbstractClient} class of the current {@link Session}.
   */
  protected abstract Class<?> getClientClass();

  final Object internalGetStoredResult() {

    if (result == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.RESULT);
    }

    return result;
  }

  /**
   * Removes the parent client of the current {@link Session}.
   */
  final void internalRemoveParentClient() {
    parentClient = null;
  }

  /**
   * Sets the parent client of the current {@link Session}.
   * 
   * @param parentClient
   * @throws ArgumentIsNullException  if the given parent client is null.
   * @throws InvalidArgumentException if the current {@link Session} belongs to a
   *                                  client.
   */
  final void internalSetParentClient(C parentClient) {

    //Asserts that the given client is not null.
    GlobalValidator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    //Asserts that the current session does not belong to a client.
    assertDoesNotBelongToClient();

    //Sets the parent client of the current session.
    this.parentClient = parentClient;
  }

  final void internalSetResult(final Object result) {

    GlobalValidator.assertThat(result).thatIsNamed(LowerCaseVariableCatalogue.RESULT).isNotNull();

    this.result = result;
  }

  /**
   * @throws InvalidArgumentException if the current {@link Session} does not
   *                                  belong to a client.
   */
  private void assertBelongsToClient() {

    //Asserts that the current {@link Session} belongs to a client.
    if (!belongsToClient()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not belong to a client");
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link Session} belongs to a
   *                                  client.
   */
  private void assertDoesNotBelongToClient() {

    //Asserts that the current {@link Session} does not belong to a client.
    if (belongsToClient()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "belongs to a client");
    }
  }

  /**
   * @return the parent {@link Application} of the parent {@link AbstractClient} of the
   *         current {@link Session}.
   */
  private Application<C, S> getStoredParentApplication() {
    return getStoredParentClient().getStoredParentApplication();
  }
}
