/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public interface IEntityFieldExtractor {
  /**
   * @param entity
   * @param fieldClass
   * @param <F>        the type of the {@link Field}s that will be extract from
   *                   the given entity
   * @return the {@link Field}s from the given entity.
   * @throws RuntimeException if the given entity is null.
   * @throws RuntimeException if the given fieldClass is null.
   */
  <F extends Field> ExtendedIterable<F> extractStoredFieldsFromEntity(final Object entity, Class<F> fieldClass);
}
