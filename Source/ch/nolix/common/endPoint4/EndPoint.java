//package declaration
package ch.nolix.common.endPoint4;

//own imports
import ch.nolix.common.closableElement.ClosableElement;
//own imports
import ch.nolix.common.communicationAPI.IReplier;
import ch.nolix.common.genericCommunicationAPI.IReplyingSender;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 100
 */
public abstract class EndPoint<M, R>
extends ClosableElement
implements IReplyingSender<M, R> {
	
	//constant
	private static final long REPLIER_GETTING_DELAY_IN_MILLISECONDS = 5000;
	
	//optional attribute
	private ch.nolix.common.genericCommunicationAPI.IReplier<M, R> replier;
	
	//method declaration
	/**
	 * @return the target of this end point.
	 */
	public abstract String getTarget();
	
	//method declaration
	/**
	 * @return true if this end point has requested the connection.
	 */
	public abstract boolean hasRequestedConnection();
	
	//method declaration
	/**
	 * @return true if this end point has a target.
	 */
	public abstract boolean hasTarget();
	
	//method
	/**
	 * Sets the replier of this end point.
	 * 
	 * @param replier
	 * @throws ArgumentIsNullException if the given replier is null.
	 */
	public void setReplier(final ch.nolix.common.genericCommunicationAPI.IReplier<M, R> replier) {
		
		//Asserts that the given replier is not null.
		Validator.assertThat(replier).isOfType(IReplier.class);
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	@Override
	protected final void noteClose() {}
	
	//method
	/**
	 * @return the replier of this end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this end point does not have a replier.
	 */
	protected final ch.nolix.common.genericCommunicationAPI.IReplier<M, R> getRefReplier() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!hasReplier()) {
			
			//The following statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > REPLIER_GETTING_DELAY_IN_MILLISECONDS) {
				throw new ArgumentDoesNotHaveAttributeException(this, IReplier.class);
			}
		}
		
		return replier;
	}
	
	//method
	/**
	 * @return true if this end point has a replier.
	 */
	private boolean hasReplier() {
		return (replier != null);
	}
}
