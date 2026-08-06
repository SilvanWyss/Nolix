/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s a {@link IEntityFieldExtractor}
 *            can extract {@link Field}s from.
 * @param <F> the type of the {@link Field}s of the {@link IEntity}s a
 *            {@link IEntityFieldExtractor} can extract.
 */
public interface IEntityFieldExtractor<E extends IEntity, F extends Field> {
  /**
   * @param entity
   * @return the {@link Field}s from the given entity.
   */
  ExtendedIterable<F> extractStoredFieldsFromEntity(final E entity);
}
