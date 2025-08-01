package ch.nolix.systemapi.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IAbstractBackReference;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IEntitySearcher {

  /**
   * @param entity
   * @param abstractReference
   * @return a {@link Optional} with the {@link IAbstractBackReference} of the
   *         given entity that can reference back the given abstractReference, an
   *         empty {@link Optional} otherwise.
   */
  Optional<IAbstractBackReference<IEntity>> //
  getOptionalStoredAbstractBackReferenceThatCanBackReferenceAbstractReference(
    IEntity entity,
    IAbstractReference<? extends IEntity> abstractReference);

  /**
   * @param entity
   * @return the {@link IAbstractBackReference}s that reference back the given
   *         entity.
   */
  IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackEntity(IEntity entity);

  /**
   * @param entity
   * @return the edited {@link IField}s of the given entity.
   */
  IContainer<? extends IField> getStoredEditedFields(IEntity entity);

  /**
   * @param entity
   * @param name
   * @return the {@link IField} with the given name from the given entity.
   * @throws RuntimeException if the given entity does not have a field with the
   *                          given name.
   */
  IField getStoredFieldByName(IEntity entity, String name);

  /**
   * @param entity
   * @return the {@link IField}s the given entity references back.
   */
  IContainer<IAbstractReference<IEntity>> getStoredFieldsThatAreBackReferencedFrom(IEntity entity);
}
