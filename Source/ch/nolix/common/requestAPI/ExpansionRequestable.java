//package declaration
package ch.nolix.common.requestAPI;

//interface
/**
 * A {@link ExpansionRequestable} can be asked if it is expanded or collapsed.
 * 
 * @author Silvan Wyss
 * @month 2020-10
 * @lines 20
 */
public interface ExpansionRequestable {
	
	//method
	/**
	 * @return true if the current {@link ExpansionRequestable} is collapsed.
	 */
	public default boolean isCollapsed() {
		return !isExpanded();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link ExpansionRequestable} is expanded.
	 */
	public abstract boolean isExpanded();
}
