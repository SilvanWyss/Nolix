//package declaration
package ch.nolix.coreapi.methodapi.mutationapi;

import ch.nolix.coreapi.methodapi.requestapi.CloseStateRequestable;

//interface
/**
 * A {@link Clearable} is a {@link AutoCloseable} whose close method does not
 * declare a {@link Exception}.
 * 
 * @author Silvan Wyss
 * @date 2023-09-10
 */
public interface Closeable extends AutoCloseable, CloseStateRequestable {

  //method declaration
  /**
   * Closes the current {@link Closeable}.
   */
  @Override
  void close();
}
