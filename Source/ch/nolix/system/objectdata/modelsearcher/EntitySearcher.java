package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
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

    for (final var b : getStoredAbstractBackReferences(entity)) {
      if (baseBackReferenceWouldReferenceBackBaseReference(b, baseReference)) {
        return Optional.of(b);
      }
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferences(final IEntity entity) {

    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    final ILinkedList<IAbstractBackReference<IEntity>> abstractBackReferences = LinkedList.createEmpty();
    final var fields = entity.internalGetStoredFields();

    for (final var f : fields) {
      if (f instanceof IAbstractBackReference<?>) {

        final var abstractBackReference = (IAbstractBackReference<IEntity>) f;

        abstractBackReferences.addAtEnd(abstractBackReference);
      }
    }

    return abstractBackReferences;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackEntity(
    final IEntity entity) {

    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    final var fields = entity.internalGetStoredFields();

    return fields.toMultiple(IField::getStoredAbstractBackReferencesThatReferencesBackThis);
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

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IAbstractReference<IEntity>> getStoredFieldsThatAreBackReferencedFrom(final IEntity entity) {

    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    final var fields = entity.internalGetStoredFields();

    return fields.toMultiple(IField::getStoredAbstractReferencesThatAreBackReferencedFromThis);
  }

  private boolean baseBackReferenceWouldReferenceBackBaseReference(
    final IAbstractBackReference<?> baseBackReference,
    final IAbstractReference<? extends IEntity> baseReference) {
    return //
    baseBackReference.getBackReferencedTableName().equals(baseReference.getStoredParentEntity().getParentTableName())
    && baseBackReference.getBackReferencedFieldName().equals(baseReference.getName());
  }
}
