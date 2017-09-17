//package declaration
package ch.nolix.core.endPoint3;

import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.communicationInterfaces.IReplier;
import ch.nolix.core.communicationInterfaces.ISender2;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

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
extends ClosableElement
implements ISender2 {
	
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
		Validator.supposeThat(replier).thatIsInstanceOf(IReplier.class).isNotNull();
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	//method
	/**
	 * Lets this end point note an abort.
	 */
	protected final void noteClosing() {}
	
	//method
	/**
	 * @return the replier of this end point.
	 * @throws UnexistingAttributeException if this end point has no replier.
	 */
	protected final IReplier getRefReplier() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler of the JVM.
		while (!hasReplier()) {
			
			//The following statement that is actually unnecessary makes that the loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > REPLIER_GETTING_DELAY_IN_MILLISECONDS) {
				throw new UnexistingAttributeException(this, IReplier.class);
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
