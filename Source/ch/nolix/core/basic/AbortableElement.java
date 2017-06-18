//package declaration
package ch.nolix.core.basic;

//own imports
import ch.nolix.core.interfaces.Abortable;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//abstract class
/**
 * An abortable element can be aborted.
 * An abortable element can be aborted only for 1 time.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 120
 */
public abstract class AbortableElement implements Abortable {

	//attribute
	private AbortController abortController = new AbortController(this);
	
	//method
	/**
	 * Aborts this abortable element.
	 * 
	 * @throws InvalidArgumentException if this abortable element
	 * or an abort depency of this abortable elements is already aborted.
	 */
	public final void abort() {
		abortController.abort();
	}
	
	//method
	/**
	 * Aborts this abortable element because of the given abort reason.
	 * 
	 * @param abortReason
	 * @throws NullArgumentException if the given abort reason is null.
	 * @throws EmptyArgumentException if the given abort reason is empty.
	 * @throws InvalidArgumentException if this abortable element is already aborted.
	 */
	public final void abort(String abortReason) {
		abortController.abort(abortReason);
	}
	
	//method
	/**
	 * @return the abort reason of this abortable element.
	 * @throws InvalidStateException if this abortable element not aborted.
	 * @throws UnexistingAttributeException if this abortable element has no abort reason.
	 */
	public final String getAbortReason() {
		return abortController.getAbortReason();
	}
	
	//method
	/**
	 * @return true if this abortable element has an abort reason.
	 */
	public final boolean hasAbortReason() {
		return abortController.hasAbortReason();
	}
	
	//method
	/**
	 * @param element
	 * @return true if this abortable element has the given element as abort dependency.
	 */
	public final boolean hasAbortDependencyTo(final AbortableElement element) {
		return abortController.containsElement(element);
	}
	
	//method
	/**
	 * @return true if this abortable element is aborted.
	 */
	public final boolean isAborted() {
		return abortController.isAborted();
	}
	
	//method
	/**
	 * Creates an abort dependency between this aborteble element and the given element.
	 * When an abortable element is aborted all of its abort dependencies are also aborted
	 * and vice versa.
	 * 
	 * @param element
	 * @throws InvalidStateException if this abortable element is aborted.
	 * @throws InvalidStateException
	 * if this abortable element has already an abort dependeny to the given element.
	 */
	protected final synchronized void createAbortDependency(final AbortableElement element) {
		
		//Checks if this abortable element is aborted.
		throwExceptionIfAborted();
		
		//Checks if this abortable element has already an abort dependency to the given element.
		if (hasAbortDependencyTo(element)) {
			throw new InvalidStateException(
				this,
				"has already an abort dependency to the given element"
			);
		}
		
		abortController.elements.forEach(e -> e.abortController = abortController);
		abortController.elements.addAtEnd(element.abortController.elements);
	}
	
	//abstract method
	/**
	 * Lets this abortable element note an abort.
	 */
	protected abstract void noteAbort();
	
	//method
	/**
	 * @throws InvalidStateException if this abortable element is aborted.
	 */
	protected final void throwExceptionIfAborted() {
		abortController.isAborted();
	}
}
