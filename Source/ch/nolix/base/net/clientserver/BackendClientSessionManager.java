/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.clientserver.Session;

/**
 * @author Silvan Wyss
 * @param <C> the type of the parent {@link AbstractBackendClient} of a
 *            {@link BackendClientSessionManager}.
 * @param <S> the type of the application service of the parent
 *            {@link AbstractApplication} of the parent
 *            {@link AbstractBackendClient} of a
 *            {@link BackendClientSessionManager}.
 */
public final class BackendClientSessionManager<C extends AbstractBackendClient<C, S>, S> {
  private static final int MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS = 10_000;

  private final C parentClient;

  private Session<C, S> currentSession;

  private final LinkedList<Session<C, S>> sessionStack = LinkedList.createEmpty();

  private BackendClientSessionManager(final C parentClient) {
    // Asserts that the given parentClient is not null.
    Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    // Sets the parentClient of the current ClientSessionManager.
    this.parentClient = parentClient;
  }

  /**
   * @param client
   * @param <C2>   the type of the given client
   * @param <S2>   the type of the application service of the given client
   * @return a new {@link BackendClientSessionManager} for the given client
   */
  public static <C2 extends AbstractBackendClient<C2, S2>, S2> BackendClientSessionManager<C2, S2> forClient(
    final C2 client) {
    return new BackendClientSessionManager<>(client);
  }

  /**
   * @return true if the current {@link BackendClientSessionManager} contains a
   *         current session, false otherwise
   */
  public boolean containsCurrentSession() {
    return currentSession != null;
  }

  /**
   * @return true if the current {@link BackendClientSessionManager} contains a
   *         session after its current session, false otherwise
   */
  public boolean containsNextSession() {
    return containsCurrentSession() && getSessionStackSize() > getCurrentSessionIndex();
  }

  /**
   * @return true if the current {@link BackendClientSessionManager} contains a
   *         session before its current session, false otherwise
   */
  public boolean containsPreviousSession() {
    return containsCurrentSession() && getCurrentSessionIndex() > 1;
  }

  /**
   * @return true if the current session of the current
   *         {@link BackendClientSessionManager} is its top session, false
   *         otherwise
   */
  public boolean currentSessionIsTopSession() {
    return containsCurrentSession() && getStoredCurrentSession() == getStoredTopSession();
  }

  /**
   * @return the number of sessions on the session stack of the current
   *         {@link BackendClientSessionManager}
   */
  public int getSessionStackSize() {
    return sessionStack.getCount();
  }

  /**
   * Waits a limited time until the current {@link BackendClientSessionManager}
   * contains a current session.
   * 
   * @return the current session of the current
   *         {@link BackendClientSessionManager}
   * @throws RuntimeException if the current {@link BackendClientSessionManager}
   *                          does not contain a current session after the waiting
   *                          time
   */
  public Session<C, S> getStoredCurrentSession() {
    FlowController
      .forMaxMilliseconds(MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS)
      .waitUntil(this::containsCurrentSession);

    assertContainsCurrentSession();

    return currentSession;
  }

  /**
   * Pops the current session of the current {@link BackendClientSessionManager}.
   * Closes the client of the current {@link BackendClientSessionManager} or
   * reinitializes the new current session.
   */
  public void popCurrentSession() {
    popCurrentSessionFromStack();
    closeClientOrReinitializeCurrentSession();
  }

  /**
   * Pops the current session of the current {@link BackendClientSessionManager}
   * and forwards the given result to the session that has pushed it.
   * 
   * @param result
   */
  public void popCurrentSessionAndForwardGivenResult(final Object result) {
    getStoredCurrentSession().internalSetResult(result);
    popCurrentSessionFromStack();
  }

  /**
   * Pushes the given session on the current {@link BackendClientSessionManager}
   * and initializes it.
   * 
   * @param session
   */
  public void pushSession(final Session<C, S> session) {
    session.internalSetParentClient(parentClient);

    sessionStack.addAtEnd(session);
    currentSession = session;

    initializeSession(session);
  }

  /**
   * Pushes the given session on the current {@link BackendClientSessionManager}
   * and waits until the given session has been popped.
   * 
   * @param session
   * @param <R>     the type of the result of the given session
   * @return the result of the given session
   * @throws RuntimeException if the client of the current
   *                          {@link BackendClientSessionManager} is closed when
   *                          the given session has been popped
   */
  @SuppressWarnings("unchecked")
  public <R> R pushSessionAndGetResult(final Session<C, S> session) {
    pushSession(session);

    FlowController.waitUntil(() -> (parentClient.isClosed() || !session.belongsToClient()));

    ResourceValidator.assertIsOpen(parentClient);

    return (R) session.internalGetStoredResult();
  }

  /**
   * Replaces the current session of the current
   * {@link BackendClientSessionManager} by the given session.
   * 
   * @param session
   */
  public void setCurrentSession(final Session<C, S> session) {
    popCurrentSessionFromStack();
    pushSession(session);
  }

  private void assertContainsCurrentSession() {
    if (!containsCurrentSession()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "current Session");
    }
  }

  private void assertContainsCurrentSessionAsTopSession() {
    assertContainsCurrentSession();

    if (!currentSessionIsTopSession()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredCurrentSession(),
        "current Session",
        "is not the top Session");
    }
  }

  private void closeClientOrReinitializeCurrentSession() {
    if (!containsCurrentSession()) {
      parentClient.close();
    } else {
      initializeSession(getStoredCurrentSession());
    }
  }

  private int getCurrentSessionIndex() {
    return sessionStack.getOneBasedIndexOfFirstOccurrenceOf(getStoredCurrentSession());
  }

  private Session<C, S> getStoredTopSession() {
    return sessionStack.getStoredLast();
  }

  private void initializeSession(final Session<C, S> session) {
    // Check if the parentClient is open because it could be closed before.
    if (parentClient.isOpen()) {
      session.internalFullInitialize();
    }

    /*
     * Check if the parentClient is open because it could be closed by the
     * fullInitialize method. Checks if the session belongs to a Client because it
     * could be popped from the Client by the fullInitialize method.
     */
    if (parentClient.isOpen() && session.belongsToClient()) {
      session.refresh();
    }
  }

  private void popCurrentSessionFromStack() {
    assertContainsCurrentSessionAsTopSession();

    popCurrentSessionFromStackWhenContainsCurrentSessionAsTopSession();
  }

  private void popCurrentSessionFromStackWhenContainsCurrentSessionAsTopSession() {
    popTopSessionFromSessionStackWhenContainsCurrentSessionAsTopSession();
    setOrClearCurrentSessionAccordingToSessionStack();
  }

  private void popTopSessionFromSessionStackWhenContainsCurrentSessionAsTopSession() {
    final var topSession = sessionStack.removeAndGetStoredLast();

    topSession.internalRemoveParentClient();
  }

  private void setOrClearCurrentSessionAccordingToSessionStack() {
    if (sessionStack.isEmpty()) {
      currentSession = null;
    } else {
      currentSession = sessionStack.getStoredLast();
    }
  }
}
