/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IEntityTypeSet {
  /**
   * @return the types of the {@link Entity}s of the current
   *         {@link IEntityTypeSet}
   */
  ExtendedIterable<Class<? extends Entity>> getEntityTypes();
}
