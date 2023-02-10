//package declaration
package ch.nolix.system.application.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

//class
final class ClientSessionManager<
	C extends BackendClient<C, AC>,
	AC
> {
	
	//constant
	private static final int MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS = 10_000;
	
	//attribute
	private final Client<C> parentClient;
		
	//optional attribute
	private Session<C, AC> currentSession;
	
	//multi-attribute
	private final LinkedList<Session<C, AC>> sessionStack = new LinkedList<>();
	
	//constructor
	public ClientSessionManager(final Client<C> parentClient) {
		
		//Asserts that the given parentClient is not null.
		GlobalValidator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
		
		//Sets the parentClient of the current ClientSessionManager.
		this.parentClient = parentClient;
	}
	
	//method
	public boolean containsCurrentSession() {
		return (currentSession != null);
	}
	
	//method
	public boolean containsNextSession() {
		return (containsCurrentSession() && getSessionStackSize() > getCurrentSessionIndex());
	}
	
	//method
	public boolean containsPreviousSession() {
		return (containsCurrentSession() && getCurrentSessionIndex() > 1);
	}
	
	//method
	public boolean currentSessionIsTopSession() {
		return (containsCurrentSession() && getRefCurrentSession() == getRefTopSession());
	}
	
	//method
	public Session<C, AC> getRefCurrentSession() {
		
		GlobalSequencer
		.forMaxMilliseconds(MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS)
		.waitUntil(this::containsCurrentSession);
		
		assertContainsCurrentSession();
		
		return currentSession;
	}
	
	//method
	public void popCurrentSession() {
		popCurrentSessionFromStack();
		closeClientOrReinitializeCurrentSession();
	}
	
	//method
	public void popCurrentSessionAndForwardGivenResult(final Object result) {
		getRefCurrentSession().setResult(result);
		popCurrentSessionFromStack();
	}
	
	//method
	public void pushSession(final Session<C, AC> session) {
		
		//Asserts that the given session is not null.
		GlobalValidator.assertThat(session).isOfType(Session.class);
		
		//Sets the given session to the Client of the current ClientSessionManager.
		session.setParentClient(parentClient.asConcrete());
		
		//Pushes the given session to the current ClientSessionManager.
		sessionStack.addAtEnd(session);
		currentSession = session;
		
		//Initializes the given session.
		initializeSession(session);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public <R> R pushSessionAndGetResult(final Session<C, AC> session) {
		
		pushSession(session);
		
		GlobalSequencer.waitUntil(() -> (parentClient.isClosed() || !session.belongsToClient()));
		
		parentClient.internalAssertIsOpen();
		
		return (R)session.getRefResult();
	}
	
	//method
	public void setCurrentSession(final Session<C, AC> session) {
		popCurrentSessionFromStack();
		pushSession(session);
	}
	
	//method
	private void assertContainsCurrentSession() {
		if (!containsCurrentSession()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "current Session");
		}
	}
	
	//method
	private void assertContainsCurrentSessionAsTopSession() {
		
		assertContainsCurrentSession();
		
		if (!currentSessionIsTopSession()) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				"current Session",
				getRefCurrentSession(),
				"is not the top Session"
			);
		}
	}
	
	//method
	private void closeClientOrReinitializeCurrentSession() {
		if (!containsCurrentSession()) {
			parentClient.close();
		} else {
			initializeSession(getRefCurrentSession());
		}
	}
	
	//method
	private int getCurrentSessionIndex() {
		return sessionStack.get1BasedIndexOfFirstOccuranceOf(getRefCurrentSession());
	}
	
	//method
	private Session<C, AC> getRefTopSession() {
		return sessionStack.getRefLast();
	}
	
	//method
	private int getSessionStackSize() {
		return sessionStack.getElementCount();
	}
	
	//method
	private void initializeSession(final Session<C, AC> session) {
		
		//Check if the Client is open because it can be closed before.
		if (parentClient.isOpen()) {
			session.fullInitialize();
		}
		
		//Check if the Client is open because it can be closed before.
		if (parentClient.isOpen()) {
			session.updateCounterpart();
		}
	}
	
	//method
	private void popCurrentSessionFromStack() {
		
		assertContainsCurrentSessionAsTopSession();
		
		popCurrentSessionFromStackWhenContainsCurrentSessionAsTopSession();
	}
	
	//method
	private void popCurrentSessionFromStackWhenContainsCurrentSessionAsTopSession() {
		popTopSessionFromSessionStackWhenContainsCurrentSessionAsTopSession();
		setOrClearCurrentSessionAccordingToSessionStack();
	}
	
	//method
	private void popTopSessionFromSessionStackWhenContainsCurrentSessionAsTopSession() {
		final var topSession = sessionStack.removeAndGetRefLast();
		topSession.removeParentClient();
	}
	
	//method
	private void setOrClearCurrentSessionAccordingToSessionStack() {
		if (sessionStack.isEmpty()) {
			currentSession = null;
		} else {
			currentSession = sessionStack.getRefLast();
		}
	}
}
