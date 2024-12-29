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

  @Deprecated
  IContainer<? extends IField> getStoredReferencingFields(IEntity entity);
}
