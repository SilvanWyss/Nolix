//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

//class
final class ClientSessionManager<C extends Client<C>> {
	
	//constant
	private static final int MAX_WAIT_TIME_FOR_SESSION_IN_MILLISECONDS = 10_000;
	
	//attribute
	private final Client<C> parentClient;
		
	//optional attribute
	private Session<C> currentSession;
	
	//multi-attribute
	private final LinkedList<Session<C>> sessionStack = new LinkedList<>();
	
	//constructor
	public ClientSessionManager(final Client<C> parentClient) {
		
		//Asserts that the given parentClient is not null.
		Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
		
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
	public Session<C> getRefCurrentSession() {
		
		Sequencer
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
	public void popCurrentSession(final Object result) {
		getRefCurrentSession().setResult(result);
		popCurrentSessionFromStack();
	}
	
	//method
	public void pushSession(final Session<C> session) {
		
		//Asserts that the given session is not null.
		Validator.assertThat(session).isOfType(Session.class);
		
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
	public <R> R pushSessionAndGetResult(final Session<C> session) {
		
		pushSession(session);
		
		Sequencer.waitUntil(() -> (parentClient.isClosed() || !session.belongsToClient()));
		
		parentClient.assertIsOpen();
		
		return (R)session.getRefResult();
	}
	
	//method
	public void setCurrentSession(final Session<C> session) {
		popCurrentSessionFromStack();
		pushSession(session);
	}
	
	//method
	private void assertContainsCurrentSession() {
		if (!containsCurrentSession()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "current Session");
		}
	}
	
	//method
	private void assertContainsCurrentSessionAsTopSession() {
		
		assertContainsCurrentSession();
		
		if (!currentSessionIsTopSession()) {
			throw new InvalidArgumentException("current Session", getRefCurrentSession(), "is not the top Session");
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
		return sessionStack.getIndexOfFirstOccurrenceOf(getRefCurrentSession());
	}
	
	//method
	private Session<C> getRefTopSession() {
		return sessionStack.getRefLast();
	}
	
	//method
	private int getSessionStackSize() {
		return sessionStack.getElementCount();
	}
	
	//method
	private void initializeSession(final Session<C> session) {
		
		//Check if the Client is open because it can be closed before.
		if (parentClient.isOpen()) {
			session.initialize();
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
