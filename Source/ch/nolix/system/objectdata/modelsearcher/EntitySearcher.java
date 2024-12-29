package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelsearcher.IEntitySearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class EntitySearcher implements IEntitySearcher {

  @Override
  public Optional<? extends IAbstractBackReference<?>> //
  getOptionalStoredBaseBackReferenceOfEntityThatWouldBackReferenceBaseReference(
    final IEntity entity,
    final IAbstractReference<? extends IEntity> baseReference) {

    for (final var p : entity.internalGetStoredFields()) {
      if (p instanceof final IAbstractBackReference<?> baseBackReference
      && baseBackReferenceWouldReferenceBackBaseReference(baseBackReference, baseReference)) {
        return Optional.of(baseBackReference);
      }
    }

    return Optional.empty();
  }

  @Override
  public IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences(final IEntity entity) {
    return entity.internalGetStoredFields().toMultiple(IField::getStoredBaseBackReferences);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IField> getStoredEditedFields(final IEntity entity) {
    return entity.internalGetStoredFields().getStoredSelected(IField::isEdited);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IField getStoredFieldByName(final IEntity entity, final String name) {

    final var fields = entity.internalGetStoredFields();

    return fields.getStoredFirst(f -> f.hasName(name));
  }

  @Override
  public IContainer<? extends IField> getStoredReferencingFields(final IEntity entity) {
    return entity.internalGetStoredFields().toMultiple(IField::getStoredReferencingFields);
  }

  private boolean baseBackReferenceWouldReferenceBackBaseReference(
    final IAbstractBackReference<?> baseBackReference,
    final IAbstractReference<? extends IEntity> baseReference) {
    return //
    baseBackReference.getBackReferencedTableName().equals(baseReference.getStoredParentEntity().getParentTableName())
    && baseBackReference.getBackReferencedFieldName().equals(baseReference.getName());
  }
}
