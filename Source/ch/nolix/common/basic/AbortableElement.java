//package declaration
package ch.nolix.common.basic;

//own imports
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.invalidStateException.InvalidStateException;
import ch.nolix.common.invalidStateException.UnexistingAttributeException;
import ch.nolix.common.invalidStateException.UntimelyMethodException;
import ch.nolix.common.zetaValidator.ZetaValidator;

//abstract class
/**
 * An abortable element can be aborted.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 90
 */
public abstract class AbortableElement implements Abortable {

	//attribute
	private boolean aborted = false;
	
	//optional attribute
	private String abortReason;	
	
	//method
	/**
	 * Aborts this abortable element.
	 * 
	 * @throws InvalidArgumentException if this abortable element is aborted already.
	 */
	public void abort() {
		
		throwExceptionIfAborted();
		
		aborted = true;
	}
	
	//method
	/**
	 * Stops this abortable element because of the given stop reason.
	 * 
	 * @param abortReason
	 * @throws NullArgumentException if the given abort reason is null.
	 * @throws EmptyArgumentException if the given abort reason is empty.
	 * @throws InvalidArgumentException if this abortable element is aborted already.
	 */
	public final void abort(String abortReason) {
		
		//Checks if the given abort reason is not empty.
		ZetaValidator.supposeThat(abortReason).thatIsNamed("abort reason").isNotEmpty();

		abort();
		this.abortReason = abortReason;
	}
	
	//method
	/**
	 * @return the abort reason of this abortable element.
	 * @throws UntimelyMethodException if this abortable element is not stopped.
	 * @throws UnexistingAttributeException if this abortable element has no abort reason.
	 */
	public final String getAbortReason() {
		
		//Checks if this abortable element is aborted.
		if (!isAborted()) {
			throw new UntimelyMethodException(this, "get abort reason");
		}
		
		//Checks if this abortable element has an abort reason.
		if (abortReason == null) {
			throw new UnexistingAttributeException(this, "abort reason");
		}
		
		return abortReason;
	}
	
	//method
	/**
	 * @return true if this abortable element is aborted.
	 */
	public final boolean isAborted() {
		return aborted;
	}
	
	//method
	/**
	 * @throws InvalidStateException if this abortable element is stopped.
	 */
	protected final void throwExceptionIfAborted() {
		if (isAborted()) {
			throw new InvalidStateException(this, "is aborted");
		}
	}
}
