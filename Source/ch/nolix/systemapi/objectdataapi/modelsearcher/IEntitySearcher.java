package ch.nolix.systemapi.objectdataapi.modelsearcher;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IEntitySearcher {

  @Deprecated
  Optional<? extends IAbstractBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    IEntity entity,
    IAbstractReference<? extends IEntity> baseReference);

  @Deprecated
  IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences(IEntity entity);

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
