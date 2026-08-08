/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.clientserver.BackendClient;
import ch.nolix.baseapi.net.clientserver.Session;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractBackendClient} of a
 *            {@link AbstractSession}
 * @param <S> the type of the application service of the parent
 *            {@link AbstractApplication} of the parent
 *            {@link AbstractBackendClient} of a {@link AbstractSession}
 */
public abstract class AbstractSession<C extends AbstractBackendClient<C, S>, S> implements Session<C, S> {
  private C memberParentClient;

  private Object memberResult;

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean belongsToClient() {
    return (memberParentClient != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getApplicationName() {
    return getStoredParentClient().getApplicationName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredApplicationService() {
    return getStoredParentClient().getStoredApplicationService();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C getStoredParentClient() {
    assertBelongsToClient();

    return memberParentClient;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasUnderlyingSession() {
    return getStoredParentClient().internalGetSessionStackSize() > 1;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isAlive() {
    return //
    memberParentClient != null
    && memberParentClient.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void pop() {
    getStoredParentClient().internalPopCurrentSession();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void popWithResult(final Object result) {
    getStoredParentClient().internalPopCurrentSessionAndForwardGivenResult(result);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link AbstractSession}.
   * 
   * @param session
   * @throws RuntimeException if the given session is null
   */
  public final void push(final AbstractSession<C, S> session) {
    getStoredParentClient().internalPush(session);
  }

  /**
   * Pushes the given session to the parent {@link AbstractClient} of the current
   * {@link AbstractSession}.
   * 
   * @param session
   * @param <R>     the type of the returned result
   * @return the result from the given session
   * @throws RuntimeException if the given session is null
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
   * @throws RuntimeException if the given session is null
   */
  public final void setNext(final AbstractSession<C, S> session) {
    getStoredParentClient().internalSetCurrentSession(session);
  }

  /**
   * Initializes the current {@link AbstractSession} fully.
   */
  protected abstract void fullInitialize();

  /**
   * @return the {@link AbstractClient} class of the current
   *         {@link AbstractSession}.
   */
  protected abstract Class<?> getClientClass();

  final Object getStoredResult() {
    if (memberResult == null) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.RESULT);
    }

    return memberResult;
  }

  /**
   * Removes the parent client from the current {@link AbstractSession}.
   */
  final void removeParentClient() {
    memberParentClient = null;
  }

  /**
   * Sets the parent client of the current {@link AbstractSession}.
   * 
   * @param parentClient
   * @throws RuntimeException if the given parent client is null
   * @throws RuntimeException if the current {@link AbstractSession} belongs to a
   *                          client.
   */
  final void setParentClient(C parentClient) {
    Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
    assertDoesNotBelongToClient();

    memberParentClient = parentClient;
  }

  /**
   * Sets the result of the current {@link AbstractSession}.
   * 
   * @param result
   * @throws RuntimeException if the given result is null
   */
  final void setResult(final Object result) {
    Validator.assertThat(result).thatIsNamed(LowerCaseVariableNameCatalog.RESULT).isNotNull();

    memberResult = result;
  }

  /**
   * @throws RuntimeException if the current {@link AbstractSession} does not
   *                          belong to a client.
   */
  private void assertBelongsToClient() {
    if (!belongsToClient()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, BackendClient.class);
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractSession} belongs to a
   *                          client.
   */
  private void assertDoesNotBelongToClient() {
    if (belongsToClient()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentClient());
    }
  }
}
