/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.closecontroller;

import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * A {@link Clearable} is a {@link AutoCloseable} whose close method does not
 * declare a {@link Exception}.
 * 
 * @author Silvan Wyss
 */
public interface Closeable extends AutoCloseable, OpennessRequestable {
  /**
   * Closes the current {@link Closeable}.
   */
  @Override
  void close();
}
