/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.system.databaseobject.modelexaminer.AbstractDatabaseObjectExaminer;
import ch.nolix.systemapi.database.databaseproperty.BaseCardinality;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 * @param <F> the type of the {@link Field}s a {@link AbstractFieldExaminer} is
 *            for
 */
public abstract class AbstractFieldExaminer<F extends Field>
extends AbstractDatabaseObjectExaminer<F>
implements IFieldExaminer<F> {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToEntity(final F field) {
    return //
    field != null
    && field.belongsToEntity();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToLoadedEntity(final F field) {
    return //
    belongsToEntity(field)
    && field.getStoredParentEntity().isLoaded();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canReferenceBackBaseReference(final F field, final BaseReference baseReference) {
    if (field instanceof final BaseBackReference baseBackReference
    && baseReference != null
    && baseReference.belongsToEntity()) {
      final var baseReferenceParentTableName = baseReference.getStoredParentEntity().getParentTableName();
      final var baseReferenceName = baseReference.getName();
      final var backReferenceableTableNames = baseBackReference.getBackReferenceableTableNames();
      final var backReferencedFieldName = baseBackReference.getBackReferencedFieldName();

      return //
      backReferenceableTableNames.contains(baseReferenceParentTableName)
      && backReferencedFieldName.equals(baseReferenceName);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isForMultiContent(final F field) {
    return //
    field != null &&
    field.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isForSingleContent(final F field) {
    return //
    field != null
    && field.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMandatoryButEmpty(final F field) {
    return //
    field != null
    && field.isMandatory()
    && field.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(final F field) {
    return //
    field != null
    && (!field.isMandatory()
    || !isNewOrEdited(field)
    || field.containsAny());
  }
}
