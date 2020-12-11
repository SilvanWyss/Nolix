//package declaration
package ch.nolix.common.skillapi;

//own import
import ch.nolix.common.requestapi.CloseStateRequestable;

//interface
/**
 * @author Silvan Wyss
 * @date 2016-01-01
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
