//package declaration
package ch.nolix.common.multiAttributeRequestAPI;

//own import
import ch.nolix.common.container.IContainer;

//interface
/**
 * A {@link MultiTokened} can be asked which tokens it has.
 * 
 * @author Silvan Wyss
 * @month 2020-07
 * @lines 30
 */
public interface MultiTokened {
	
	//method declaration
	/**
	 * @return the tokens of the current {@link MultiTokened}.
	 */
	public abstract IContainer<String> getTokens();
	
	//method
	/**
	 * @return true if the current {@link MultiTokened} has the given token.
	 */
	public default boolean hasToken(final String token) {
		return getTokens().containsEqualing(token);
	}
}
