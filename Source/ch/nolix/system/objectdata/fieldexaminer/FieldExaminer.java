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
  public boolean canReferenceBackBaseReference(
    final IField field,
    final IBaseReference<? extends IEntity> abstractReference) {

    if (field instanceof IBaseBackReference && abstractReference != null) {

      @SuppressWarnings("unchecked")
      final var abstractBackReference = (IBaseBackReference<IEntity>) field;

      return //
      abstractBackReferenceCanReferenceBackAbstractReferenceWhenParametersAreNotNull(
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
   * @param abstractBackReference
   * @param abstractReference
   * @return true if the given abstractBackReference can reference back the given
   *         abstractReference, false otherwise, for the case that the given
   *         abstractBackReference and abstractReference are not null.
   */
  private boolean abstractBackReferenceCanReferenceBackAbstractReferenceWhenParametersAreNotNull(
    final IBaseBackReference<IEntity> abstractBackReference,
    final IBaseReference<? extends IEntity> abstractReference) {

    if (abstractReference.belongsToEntity()) {

      final var backReferencedTableName = abstractBackReference.getBackReferencedTableName();
      final var backReferencedFieldName = abstractBackReference.getBackReferencedFieldName();

      return //
      backReferencedTableName.equals(abstractReference.getStoredParentEntity().getParentTableName())
      && backReferencedFieldName.equals(abstractReference.getName());
    }

    return false;
  }
}
