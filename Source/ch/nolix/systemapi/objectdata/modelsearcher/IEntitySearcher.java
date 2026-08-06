/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public interface IEntitySearcher {
  /**
   * @param entity
   * @param baseReference
   * @return a {@link Optional} with the {@link IBaseBackReference} of the given
   *         entity that can reference back the given abstractReference, an empty
   *         {@link Optional} otherwise.
   */
  Optional<IBaseBackReference> getOptionalStoredBaseBackReferenceWhoCanBackReferenceTheBaseReference(
    IEntity entity,
    BaseReference baseReference);

  /**
   * @param entity
   * @return the {@link IBaseBackReference}s that reference back the given entity.
   */
  ExtendedIterable<IBaseBackReference> getStoredBaseBackReferencesThatReferenceBackEntity(IEntity entity);

  /**
   * @param entity
   * @return the edited {@link Field}s of the given entity.
   */
  ExtendedIterable<? extends Field> getStoredEditedFields(IEntity entity);

  /**
   * @param entity
   * @param name
   * @return the {@link Field} with the given name from the given entity
   * @throws RuntimeException if the given entity does not have a field with the
   *                          given name.
   */
  Field getStoredFieldByName(IEntity entity, String name);

  /**
   * @param entity
   * @return the {@link Field}s the given entity references back.
   */
  ExtendedIterable<BaseReference> getStoredFieldsWhoAreBackReferencedFromEntity(IEntity entity);
}
