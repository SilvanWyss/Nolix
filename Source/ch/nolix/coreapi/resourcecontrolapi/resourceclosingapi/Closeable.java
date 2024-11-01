package ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

/**
 * A {@link Clearable} is a {@link AutoCloseable} whose close method does not
 * declare a {@link Exception}.
 * 
 * @author Silvan Wyss
 * @version 2023-09-10
 */
public interface Closeable extends AutoCloseable, CloseStateRequestable {

  /**
   * Closes the current {@link Closeable}.
   */
  @Override
  void close();
}
