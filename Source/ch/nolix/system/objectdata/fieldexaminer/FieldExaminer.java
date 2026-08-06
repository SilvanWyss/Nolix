/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.baseapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public class FieldExaminer extends DatabaseObjectExaminer implements IFieldExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToEntity(final Field field) {
    return //
    field != null
    && field.belongsToEntity();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToLoadedEntity(final Field field) {
    return //
    belongsToEntity(field)
    && field.getStoredParentEntity().isLoaded();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canReferenceBackBaseReference(final Field field, final BaseReference baseReference) {
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
  public boolean isForMultiContent(final Field field) {
    return //
    field != null &&
    field.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isForSingleContent(final Field field) {
    return //
    field != null
    && field.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMandatoryButEmpty(final Field field) {
    return //
    field != null
    && field.isMandatory()
    && field.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(final Field field) {
    return //
    field != null
    && (!field.isMandatory()
    || !isNewOrEdited(field)
    || field.containsAny());
  }
}
