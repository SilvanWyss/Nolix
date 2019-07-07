//package declaration
package ch.nolix.core.endPoint3;

import ch.nolix.core.communicationAPI.IReplier;
import ch.nolix.core.communicationAPI.IReplyingSender;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.optionalClosableElement.OptionalClosableElement;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 100
 */
public abstract class EndPoint
extends OptionalClosableElement
implements IReplyingSender {
	
	//constant
	private static final long REPLIER_GETTING_DELAY_IN_MILLISECONDS = 5000;
	
	//optional attribute
	private IReplier replier;
	
	//abstract method
	/**
	 * @return the target of this end point.
	 */
	public abstract String getTarget();
	
	//abstract method
	/**
	 * @return true if this end point has requested the connection.
	 */
	public abstract boolean hasRequestedConnection();
	
	//abstract method
	/**
	 * @return true if this end point has a target.
	 */
	public abstract boolean hasTarget();
	
	//method
	/**
	 * Sets the replier of this end point.
	 * 
	 * @param replier
	 * @throws NullArgumentException if the given replier is null.
	 */
	public void setReplier(final IReplier replier) {
		
		//Checks if the given replier is not null.
		Validator.suppose(replier).isOfType(IReplier.class);
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	//method
	/**
	 * Lets this end point note an abort.
	 */
	@Override
	protected final void noteClose() {}
	
	//method
	/**
	 * @return the replier of this end point.
	 * @throws ArgumentMissesAttributeException if this end point does not have a replier.
	 */
	protected final IReplier getRefReplier() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!hasReplier()) {
			
			//The following statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > REPLIER_GETTING_DELAY_IN_MILLISECONDS) {
				throw new ArgumentMissesAttributeException(this, IReplier.class);
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
