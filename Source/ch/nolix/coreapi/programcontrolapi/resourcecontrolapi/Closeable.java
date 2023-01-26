//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

//interface
/**
* @author Silvan Wyss
* @date 2023-01-26
*/
public interface Closeable extends AutoCloseable {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	void close();
}
