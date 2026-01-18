/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.resourcecontrol.closecontroller;

import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * A {@link Clearable} is a {@link AutoCloseable} whose close method does not
 * declare a {@link Exception}.
 * 
 * @author Silvan Wyss
 */
public interface Closeable extends AutoCloseable, CloseStateRequestable {
  /**
   * Closes the current {@link Closeable}.
   */
  @Override
  void close();
}
