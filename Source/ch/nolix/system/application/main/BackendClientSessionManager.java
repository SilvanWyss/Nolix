package ch.nolix.system.application.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

public final class BackendClientSessionManager<C extends BackendClient<C, AC>, AC> {

  private static final int MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS = 10_000;

  private final C parentClient;

  private Session<C, AC> currentSession;

  private final LinkedList<Session<C, AC>> sessionStack = LinkedList.createEmpty();

  private BackendClientSessionManager(final C parentClient) {

    //Asserts that the given parentClient is not null.
    GlobalValidator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    //Sets the parentClient of the current ClientSessionManager.
    this.parentClient = parentClient;
  }

  public static <C2 extends BackendClient<C2, AC2>, AC2> BackendClientSessionManager<C2, AC2> forClient(
    final C2 client) {
    return new BackendClientSessionManager<>(client);
  }

  public boolean containsCurrentSession() {
    return (currentSession != null);
  }

  public boolean containsNextSession() {
    return (containsCurrentSession() && getSessionStackSize() > getCurrentSessionIndex());
  }

  public boolean containsPreviousSession() {
    return (containsCurrentSession() && getCurrentSessionIndex() > 1);
  }

  public boolean currentSessionIsTopSession() {
    return (containsCurrentSession() && getStoredCurrentSession() == getStoredTopSession());
  }

  public Session<C, AC> getStoredCurrentSession() {

    GlobalSequencer
      .forMaxMilliseconds(MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS)
      .waitUntil(this::containsCurrentSession);

    assertContainsCurrentSession();

    return currentSession;
  }

  public int getSessionStackSize() {
    return sessionStack.getCount();
  }

  public void popCurrentSession() {
    popCurrentSessionFromStack();
    closeClientOrReinitializeCurrentSession();
  }

  public void popCurrentSessionAndForwardGivenResult(final Object result) {
    getStoredCurrentSession().internalSetResult(result);
    popCurrentSessionFromStack();
  }

  public void pushSession(final Session<C, AC> session) {

    //Asserts that the given session is not null.
    GlobalValidator.assertThat(session).isOfType(Session.class);

    //Sets the given session to the Client of the current ClientSessionManager.
    session.internalSetParentClient(parentClient.asConcrete());

    //Pushes the given session to the current ClientSessionManager.
    sessionStack.addAtEnd(session);
    currentSession = session;

    //Initializes the given session.
    initializeSession(session);
  }

  @SuppressWarnings("unchecked")
  public <R> R pushSessionAndGetResult(final Session<C, AC> session) {

    pushSession(session);

    GlobalSequencer.waitUntil(() -> (parentClient.isClosed() || !session.belongsToClient()));

    parentClient.internalAssertIsOpen();

    return (R) session.internalGetStoredResult();
  }

  public void setCurrentSession(final Session<C, AC> session) {
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
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "current Session",
        getStoredCurrentSession(),
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
    return sessionStack.get1BasedIndexOfFirstOccurrenceOf(getStoredCurrentSession());
  }

  private Session<C, AC> getStoredTopSession() {
    return sessionStack.getStoredLast();
  }

  private void initializeSession(final Session<C, AC> session) {

    //Check if the parentClient is open because it could be closed before.
    if (parentClient.isOpen()) {
      session.fullInitialize();
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
