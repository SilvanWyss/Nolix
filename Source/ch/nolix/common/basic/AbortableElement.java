/*
 * file:	AbortableElement.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	80
 */

//package declaration
package ch.nolix.common.basic;

//own imports
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.util.Validator;

//abstract class
/**
 * A abortable element can be stopped.
 */
public abstract class AbortableElement implements Abortable {

	//attribute
	private boolean stopped = false;
	
	//optional attribute
	private String stopReason;
	
	//method
	/**
	 * @return the stop reason of this abortable element
	 * @throws Exception if:
	 *  -this abortable element is not stopped
	 *  -this abortable element has no stop reason
	 */
	public final String getAbortReason() {
		
		if (!isAborted()) {
			throw new RuntimeException("Stoppable element is not stopped.");
		}
		
		if (stopReason == null) {
			throw new UnexistingAttributeException(this, "stop reason");
		}
		
		return stopReason;
	}
	
	//method
	/**
	 * @return true if this abortable element is stopped
	 */
	public final boolean isAborted() {
		return stopped;
	}	
	
	//method
	/**
	 * Stops this abortable element.
	 * 
	 * @throws Exception if this abortable element is stopped already
	 */
	public void abort() {
		
		throwExceptionIfStopped();
		
		stopped = true;
	}
	
	//method
	/**
	 * Stops this abortable element because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if:
	 *  -the given stop reason is null or an empty string
	 *  -this abortable element is stopped already
	 */
	public final void abort(String stopReason) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty(stopReason, "stop reason");
		
		abort();
		this.stopReason = stopReason;
	}
	
	//method
	/**
	 * @throws Exception if this abortable element is stopped
	 */
	protected final void throwExceptionIfStopped() {
		if (isAborted()) {
			throw new RuntimeException("Stoppable element is stopped.");
		}
	}
}
