//package declaration
package ch.nolix.common.skillAPI;

//interface
/**
 * A {@link Recalculable} can be recalculated.
 * This means that the state of a {@Recalculable} can (!) be updated after several parameters have been reset.
 * 
 * There is a difference between a {@link Recalculable} and a {@link Refreshable}.
 * 
 * A {@link Recalculable} just recalculates its state
 * whereas as a {@link Refreshable} refreshes also the display when it is shown.
 * 
 * @author Silvan Wyss
 * @month 2019-03
 * @lines 20
 */
public interface Recalculable {
	
	//method declaration
	void recalculate();
}
