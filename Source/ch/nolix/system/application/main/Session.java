//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programcontrolapi.triggerapi.IRefreshableSubscriber;

//class
/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <BC> is the type of the {@link BackendClient} of a {@link Session}.
 * @param <AC> is the type of the context of the parent {@link Application} of
 *             the parent {@link BackendClient} of a {@link Session}.
 */
public abstract class Session<BC extends BackendClient<BC, AC>, AC>
implements IRefreshableSubscriber {

  //attribute
  private BC parentClient;

  //optional attribute
  private Object result;

  //method
  /**
   * @return true if the current {@link Session} belongs to a {@link Client}.
   */
  public final boolean belongsToClient() {
    return (parentClient != null);
  }

  //method
  /**
   * @return the name of the parent {@link Application} of the parent
   *         {@link Client} of the current {@link Session}.
   */
  public final String getApplicationName() {
    return getStoredParentClient().getApplicationName();
  }

  //method
  /**
   * @return the context of the parent {@link Application} of the parent
   *         {@link Client} of the current {@link Session}.
   */
  public final AC getStoredApplicationContext() {
    return getStoredParentApplication().getStoredApplicationContext();
  }

  //method
  /**
   * @return the parent client of the current {@link Session}.
   * @throws InvalidArgumentException if the current {@link Session} does not
   *                                  belong to a client.
   */
  public final BC getStoredParentClient() {

    //Asserts that the current {@link Session} belonts to a client.
    assertBelongsToClient();

    return parentClient;
  }

  //method
  public final boolean hasParentSession() {
    return (getStoredParentClient().internalGetSessionStackSize() > 1);
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isAlive() {
    return parentClient != null
    && parentClient.isOpen();
  }

  //method
  /**
   * Pops the current {@link Session} from its parent {@link Client}.
   */
  public final void pop() {
    getStoredParentClient().internalPopCurrentSession();
  }

  //method
  /**
   * Pops the current {@link Session} from its parent {@link Client} with the
   * given result.
   * 
   * @param result
   * @throws ArgumentIsNullException if the given result is null.
   */
  public final void pop(final Object result) {
    getStoredParentClient().internalPopCurrentSessionAndForwardGivenResult(result);
  }

  //method
  /**
   * Pushes the given session to the parent {@link Client} of the current
   * {@link Session}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void push(final Session<BC, AC> session) {
    getStoredParentClient().internalPush(session);
  }

  //method
  /**
   * Pushes the given session to the parent {@link Client} of the current
   * {@link Session}.
   * 
   * @param session
   * @param <R>     is the type of the returned result.
   * @return the result from the given session.
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final <R> R pushAndGetResult(final Session<BC, AC> session) {
    return getStoredParentClient().internalPushAndGetResult(session);
  }

  //method
  /**
   * Sets the next session of the parent {@link Client} of the current
   * {@link Session}. That means the current {@link Session} will be popped from
   * its parent {@link Client} and the given session is pushed to the parent
   * {@link Client} of the current {@link Session}.
   * 
   * @param session
   * @throws ArgumentIsNullException if the given session is null.
   */
  public final void setNext(final Session<BC, AC> session) {
    getStoredParentClient().internalSetCurrentSession(session);
  }

  //method declaration
  /**
   * Initializes the current {@link Session}.
   */
  protected abstract void fullInitialize();

  //method
  /**
   * @return the {@link Client} class of the current {@link Session}.
   */
  protected abstract Class<?> getClientClass();

  //method
  final Object internalGetStoredResult() {

    if (result == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.RESULT);
    }

    return result;
  }

  //method
  /**
   * Removes the parent client of the current {@link Session}.
   */
  final void internalRemoveParentClient() {
    parentClient = null;
  }

  //method
  /**
   * Sets the parent client of the current {@link Session}.
   * 
   * @param parentClient
   * @throws ArgumentIsNullException  if the given parent client is null.
   * @throws InvalidArgumentException if the current {@link Session} belongs to a
   *                                  client.
   */
  final void internalSetParentClient(BC parentClient) {

    //Asserts that the given client is not null.
    GlobalValidator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    //Asserts that the current session does not belong to a client.
    assertDoesNotBelongToClient();

    //Sets the parent client of the current session.
    this.parentClient = parentClient;
  }

  //method
  final void internalSetResult(final Object result) {

    GlobalValidator.assertThat(result).thatIsNamed(LowerCaseVariableCatalogue.RESULT).isNotNull();

    this.result = result;
  }

  //method
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

  //method
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

  //method
  /**
   * @return the parent {@link Application} of the parent {@link Client} of the
   *         current {@link Session}.
   */
  private Application<BC, AC> getStoredParentApplication() {
    return getStoredParentClient().getStoredParentApplication();
  }
}
