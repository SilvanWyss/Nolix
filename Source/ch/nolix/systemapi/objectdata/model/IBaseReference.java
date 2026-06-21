/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IBaseReference extends IField {
  /**
   * @return the names of the {@link ITable}s the current {@link IBaseReference}
   *         can reference.
   */
  ExtendedIterable<String> getReferenceableTableNames();
}
