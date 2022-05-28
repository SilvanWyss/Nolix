//package declaration
package ch.nolix.systemapi.timeapi.momentapi;

import ch.nolix.systemapi.elementuniversalapi.Specified;

//interface
public interface ITime extends Specified {
	
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
