//package declaration
package ch.nolix.common.skillAPI;

//own import
import ch.nolix.common.requestAPI.CloseStateRequestable;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Closable extends CloseStateRequestable {
	
	//method
	/**
	 * @return the current {@link Closable} as {@link AutoCloseable}.
	 */
	public default AutoCloseable asAutoClosable() {
		return this::close;
	}
	
	//method declaration
	/**
	 * Closes the current {@link Closable}.
	 */
	public abstract void close();
}
