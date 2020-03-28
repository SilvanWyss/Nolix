//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link OptionalHeaderable} can have a header that can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 30
 * @param <OH> The type of a {@link OptionalHeaderable}.
 */
public interface OptionalHeaderable<OH extends OptionalHeaderable<OH>> extends OptionalHeadered {
	
	//method declaration
	/**
	 * Removes the header of current {@link OptionalHeaderable}.
	 * 
	 * @return the current {@link OptionalHeaderable}.
	 */
	public abstract OH removeHeader();
	
	//method declaration
	/**
	 * Sets the header of the current {@link OptionalHeaderable}.
	 * 
	 * @param header
	 * @return the current {@link OptionalHeaderable}.
	 */
	public abstract OH setHeader(String header);
}
