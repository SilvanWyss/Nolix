//package declaration
package ch.nolix.systemapi.timeapi.momentapi;

//own imports
import ch.nolix.systemapi.elementapi.IElement;

//interface
public interface ITime extends IElement {
	
	//method declaration
	/**
	 * @return the milliseconds of the current {@link ITime}.
	 */
	long getMilliseconds();
	
	//method declaration
	/**
	 * @param time
	 * @return true if the current {@link ITime} is after the given time.
	 */
	boolean isAfter(ITime time);
	
	//method
	/**
	 * @param time
	 * @return true if the current {@link ITime} is before the given time.
	 */
	boolean isBefore(ITime time);
}
