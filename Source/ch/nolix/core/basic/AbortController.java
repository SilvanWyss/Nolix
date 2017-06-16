//package declaration
package ch.nolix.core.basic;

//own import
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.invalidStateException.UntimelyMethodException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 140
 */
final class AbortController {

	//attribute
	private boolean aborted = false;
	
	//optional attribute
	private String abortReason;
	
	//multiple attribute
	final List<AbortableElement> elements = new List<AbortableElement>();
	
	//constructor
	/**
	 * Creates new abort controller with the given element.
	 * 
	 * @param element
	 * @throws NullArgumentException if the given element is null.
	 */
	public AbortController(final AbortableElement element) {	
		elements.addAtEnd(element);
	}
	
	//method
	/**
	 * Aborts this abort controller.
	 * 
	 * @throws InvalidStateException if this abort controller is already aborted.
	 */
	public final void abort() {
		
		//Checks if this abort controller is already aborted.
		if (isAborted()) {
			throw new InvalidStateException(this, "is already aborted");
		}
		
		//Sets this abort controller as aborted.
		aborted = true;
	}
	
	//method
	/**
	 * Aborts this abort controller because of the given abort reason.
	 * 
	 * @param abortReason
	 * @throws NullArgumentException if the given abort reason is null.
	 * @throws EmptyArgumentException if the given abort reason is empty.
	 * @throws InvalidArgumentException if this abort controller is already aborted.
	 */
	public final void abort(String abortReason) {
		
		//Checks if the given abort reason is not null or empty.
		Validator.supposeThat(abortReason).thatIsNamed("abort reason").isNotEmpty();
		
		//Aborts this abort controller.
		abort();
		
		//Sets the abort reason of this abort controller.
		this.abortReason = abortReason;
	}
	
	//method
	/**
	 * @param element
	 * @return true if this abort controller contains the given element.
	 */
	public boolean containsElement(final AbortableElement element) {
		return elements.contains(element);
	}
	
	//method
	/**
	 * @return the abort reason of this abort controller.
	 * @throws InvalidStateException if this abort controller is not aborted.
	 * @throws UnexistingAttributeException if this abort controller has no abort reason.
	 */
	public final String getAbortReason() {
		
		//Checks if this abort controller is aborted.
		if (!isAborted()) {
			throw new UntimelyMethodException(this, "get abort reason");
		}
		
		//Checks if this abort controller has an abort reason.
		if (!hasAbortReason()) {
			throw new UnexistingAttributeException(this, "abort reason");
		}
		
		return abortReason;
	}
	
	//method
	/**
	 * @return true if this abort controller has an abort reason.
	 */
	public boolean hasAbortReason() {
		return (abortReason != null);
	}
	
	//method
	/**
	 * @return true if the elements of this abort controller are aborted.
	 */
	public boolean isAborted() {
		return aborted;
	}
	
	//method
	/**
	 * @return true if the elements of this abort controller are not aborted.
	 */
	public boolean isRunning() {
		return !isAborted();
	}
	
	//method
	/**
	 * @throws InvalidStateException if this abort controller is aborted.
	 */
	public void throwExceptionIfAborted() {
		if (isAborted()) {
			throw new InvalidStateException(this, "is aborted");
		}
	}
}
