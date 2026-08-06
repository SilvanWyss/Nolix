/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface BaseReference extends Field {
  /**
   * @return the names of the {@link ITable}s the current {@link BaseReference}
   *         can reference.
   */
  ExtendedIterable<String> getReferenceableTableNames();
}
