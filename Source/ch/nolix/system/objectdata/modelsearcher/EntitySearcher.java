/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelsearcher;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

/**
 * @author Silvan Wyss
 */
public final class EntitySearcher implements IEntitySearcher {
  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<IBaseBackReference> //
  getOptionalStoredBaseBackReferenceWhoCanBackReferenceTheBaseReference(
    final IEntity entity,
    final BaseReference baseReference) {
    if (entity != null && baseReference != null) {
      for (final var f : entity.internalGetStoredFields()) {
        if (FIELD_EXAMINER.canReferenceBackBaseReference(f, baseReference)) {
          return Optional.of((IBaseBackReference) f);
        }
      }
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<IBaseBackReference> getStoredBaseBackReferencesThatReferenceBackEntity(
    final IEntity entity) {
    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(Field::getStoredBaseBackReferencesWhoReferencesBackThis);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Field> getStoredEditedFields(final IEntity entity) {
    return entity.internalGetStoredFields().getStoredSelected(Field::isEdited);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Field getStoredFieldByName(final IEntity entity, final String name) {
    final var fields = entity.internalGetStoredFields();

    return fields.getStoredFirst(f -> f.hasName(name));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<BaseReference> getStoredFieldsWhoAreBackReferencedFromEntity(final IEntity entity) {
    final var fields = entity.internalGetStoredFields();

    return fields.toMultiples(Field::getStoredBackReferencedBaseReferences);
  }
}
