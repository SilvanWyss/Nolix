//package declaration
package ch.nolix.common.net.endpoint2;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.net.communicationapi.IReplier;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;

//class
/**
 * An end point can send messages to an other end point of the same type.
 * An end point is abortable.
 * 
 * @author Silvan Wyss
 * @date 2017-05-21
 * @lines 110
 */
public abstract class EndPoint implements ICloseableElement, IReplier {
	
	//constant
	private static final long REPLIER_GETTING_DELAY_IN_MILLISECONDS = 5000;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private IReplier replier;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
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
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a web {@link EndPoint}.
	 */
	public abstract boolean isWebEndPoint();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method
	/**
	 * Sets the replier of this end point.
	 * 
	 * @param replier
	 * @throws ArgumentIsNullException if the given replier is null.
	 */
	public void setReplier(final IReplier replier) {
		
		//Asserts that the given replier is not null.
		Validator.assertThat(replier).isOfType(IReplier.class);
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	//method
	/**
	 * @return the replier of this end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this end point does not have a replier.
	 */
	protected final IReplier getRefReplier() {
		
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
