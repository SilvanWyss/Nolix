package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.coreapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.modelexaminer.IFieldExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class FieldExaminer extends DatabaseObjectExaminer implements IFieldExaminer {

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
  public boolean canReferenceBackAbstractReference(
    final IField field,
    final IAbstractReference<? extends IEntity> abstractReference) {

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
   * @param abstractBackReference
   * @param abstractReference
   * @return true if the given abstractBackReference can reference back the given
   *         abstractReference, false otherwise, for the case that the given
   *         abstractBackReference and abstractReference are not null.
   */
  private boolean abstractBackReferenceCanReferenceBackAbstractReferenceWhenParametersAreNotNull( //NOSONAR: This method is an instance method.
    final IBaseBackReference<IEntity> abstractBackReference,
    final IAbstractReference<? extends IEntity> abstractReference) {

    if (abstractReference.belongsToEntity()) {

      final var backReferencedTableName = abstractBackReference.getBackReferencedTableName();
      final var backReferencedFieldName = abstractBackReference.getBackReferencedFieldName();

      return //
      backReferencedTableName.equals(abstractReference.getStoredParentEntity().getParentTableName())
      && backReferencedFieldName.equals(abstractReference.getName());
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
}
