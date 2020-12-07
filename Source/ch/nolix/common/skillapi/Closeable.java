//package declaration
package ch.nolix.common.skillapi;

//own imports
import ch.nolix.common.requestapi.CloseStateRequestable;

//interface
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public interface Closeable extends CloseStateRequestable {
	
	//method
	/**
	 * @return the current {@link Closeable} as {@link AutoCloseable}.
	 */
	default AutoCloseable asAutoClosable() {
		return this::close;
	}
	
	//method declaration
	/**
	 * Closes the current {@link Closeable}.
	 */
	void close();
}
