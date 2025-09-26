package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.coreapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IBaseReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public class FieldExaminer extends DatabaseObjectExaminer implements IFieldExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToEntity(final IField field) {
    return //
    field != null
    && field.belongsToEntity();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToLoadedEntity(final IField field) {
    return //
    belongsToEntity(field)
    && field.getStoredParentEntity().isLoaded();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canReferenceBackBaseReference(final IField field, final IBaseReference abstractReference) {
    if (field instanceof IBaseBackReference && abstractReference != null) {
      @SuppressWarnings("unchecked")
      final var abstractBackReference = (IBaseBackReference<IEntity>) field;

      return //
      baseBackReferenceCanReferenceBackBaseReferenceWhenParametersAreNotNull(
        abstractBackReference,
        abstractReference);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isForMultiContent(final IField field) {
    return //
    field != null &&
    field.getType().getCardinality().getBaseCardinality() == BaseCardinality.MULTI;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isForSingleContent(final IField field) {
    return //
    field != null
    && field.getType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMandatoryButEmpty(final IField field) {
    return //
    field != null
    && field.isMandatory()
    && field.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(final IField field) {
    return //
    field != null
    && (!field.isMandatory()
    || !isNewOrEdited(field)
    || field.containsAny());
  }

  /**
   * @param baseBackReference
   * @param baseReference
   * @return true if the given baseBackReference can reference back the given
   *         baseReference false otherwise. For the case that the given
   *         baseBackReference and baseReference are not null.
   */
  private boolean baseBackReferenceCanReferenceBackBaseReferenceWhenParametersAreNotNull(
    final IBaseBackReference<IEntity> baseBackReference,
    final IBaseReference baseReference) {
    if (baseReference.belongsToEntity()) {
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
}
