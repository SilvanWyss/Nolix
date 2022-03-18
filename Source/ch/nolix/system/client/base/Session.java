//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
/**
 * A {@link Session} manages user run methods and user data methods.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of the client of a {@link Session}.
 */
public abstract class Session<C extends Client<C>> {
	
	//attribute
	private C parentClient;
	
	//optional attribute
	private Object result;
	
	//method
	/**
	 * @return true if the current {@link Session} belongs to a client.
	 */
	public final boolean belongsToClient() {
		return (parentClient != null);
	}
	
	//method
	/**
	 * @return the name of the parent {@link Application} of the parent {@link Client} of the current {@link Session}.
	 */
	public final String getApplicationName() {
		return getParentClient().getApplicationName();
	}
	
	//method
	/**
	 * @return the parent {@link Application} of the parent {@link Client} of the current {@link Session}.
	 */
	public Application<C> getParentApplication() {
		return getParentClient().getParentApplication();
	}
	
	//method
	/**
	 * @return the parent client of the current {@link Session}.
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a client.
	 */
	public final C getParentClient() {
		
		//Asserts that the current {@link Session} belonts to a client.
		supposeBelongsToClient();
		
		return parentClient;
	}
	
	//method
	/**
	 * Pops the current {@link Session} from its parent {@link Client}.
	 */
	public final void pop() {
		getParentClient().internalPopCurrentSession();
	}
	
	//method
	/**
	 * Pops the current {@link Session} from its parent {@link Client} with the given result.
	 * 
	 * @param result
	 * @throws ArgumentIsNullException if the given result is null.
	 */
	public final void pop(final Object result) {
		getParentClient().internalPopCurrentSessionAndForwardGivenResult(result);
	}
	
	//method
	/**
	 * Pushes the given session to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final void push(final Session<C> session) {
		getParentClient().internalPush(session);
	}
	
	//method
	/**
	 * Pushes the given session to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @param <R> is the type of the returned result.
	 * @return the result from the given session.
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final <R> R pushAndGetResult(final Session<C> session) {
		return getParentClient().internalPushAndGetResult(session);
	}
	
	//method
	/**
	 * Sets the next session of the parent {@link Client} of the current {@link Session}.
	 * That means the current {@link Session} will be popped from its parent {@link Client}
	 * and the given session is pushed to the parent {@link Client} of the current {@link Session}.
	 * 
	 * @param session
	 * @throws ArgumentIsNullException if the given session is null.
	 */
	public final void setNext(final Session<C> session) {
		getParentClient().internalSetCurrentSession(session);
	}
	
	//method declaration
	/**
	 * Initializes the current {@link Session}.
	 */
	protected abstract void initialize();
	
	//method declaration
	/**
	 * Initializes the current {@link Session} for the first time.
	 */
	protected abstract void initializeForFirstTime();
	
	//method
	/**
	 * @return the {@link Client} class of the current {@link Session}.
	 */
	protected abstract Class<C> internalGetRefClientClass();
	
	//method declaration
	/**
	 * Updates the counterpart of the {@link Client} of the current {@link Session}.
	 */
	protected abstract void updateCounterpart();
	
	final Object getRefResult() {
		
		if (result == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.RESULT);
		}
		
		return result;
	}
			
	//method
	/**
	 * Removes the parent client of the current {@link Session}.
	 */
	final void removeParentClient() {
		parentClient = null;
	}
	
	//method
	/**
	 * Sets the parent client of the current {@link Session}.
	 * 
	 * @param parentClient
	 * @throws ArgumentIsNullException if the given parent client is null.
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	final void setParentClient(C parentClient) {
		
		//Asserts that the given client is not null.
		Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
		
		//Asserts that the current session does not belong to a client.
		suppoeDoesNotBelongToClient();
						
		//Sets the parent client of the current session.
		this.parentClient = parentClient;
		
		//Initializes the current Session the first time.
		initializeForFirstTime();
	}
	
	//method
	final void setResult(final Object result) {
		
		Validator.assertThat(result).thatIsNamed(LowerCaseCatalogue.RESULT).isNotNull();
		
		this.result = result;
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Session} does not belong to a client.
	 */
	private void supposeBelongsToClient() {
		
		//Asserts that the current {@link Session} belongs to a client.
		if (!belongsToClient()) {
			throw new InvalidArgumentException(this, "does not belong to a client");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Session} belongs to a client.
	 */
	private void suppoeDoesNotBelongToClient() {
		
		//Asserts that the current {@link Session} does not belong to a client.
		if (belongsToClient()) {
			throw new InvalidArgumentException(this, "belongs to a client");
		}
	}
}
