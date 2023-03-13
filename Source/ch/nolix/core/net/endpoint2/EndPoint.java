//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.baseendpoint.BaseEndPoint;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-21
 */
public abstract class EndPoint extends BaseEndPoint {
	
	//constant
	private static final long REPLIER_GETTING_DELAY_IN_MILLISECONDS = 5000;
	
	//optional attribute
	private IElementTakerElementGetter<String, String> replier;
	
	//method declaration
	public abstract String getReplyForRequest(final String request);
	
	//method
	/**
	 * @return true if this end point has a replier.
	 */
	public boolean hasReplier() {
		return (replier != null);
	}
	
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
	public void setReplier(final IElementTakerElementGetter<String, String> replier) {
		
		//Asserts that the given replier is not null.
		GlobalValidator.assertThat(replier).thatIsNamed("replier").isNotNull();
		
		//Sets the replier of this end point.
		this.replier = replier;
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	protected final void assertIsOpen() {
		if (isClosed()) {
			throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * @return the replier of this end point.
	 * @throws ArgumentDoesNotHaveAttributeException if this end point does not have a replier.
	 */
	protected final IElementTakerElementGetter<String, String> getRefReplier() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away by the compiler or the JVM.
		while (!hasReplier()) {
			
			//The following statement, that is actually unnecessary,
			//makes that the current loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > REPLIER_GETTING_DELAY_IN_MILLISECONDS) {
				throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "replier");
			}
		}
		
		return replier;
	}
}
