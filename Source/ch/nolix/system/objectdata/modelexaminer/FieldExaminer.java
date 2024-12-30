package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.IFieldExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class FieldExaminer implements IFieldExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canReferenceBackAbstractReference(
    final IField field,
    final IAbstractReference<? extends IEntity> abstractReference) {

    if (field instanceof IAbstractBackReference && abstractReference != null) {

      @SuppressWarnings("unchecked")
      final var abstractBackReference = (IAbstractBackReference<IEntity>) field;

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
   *         abstractBackReference and abstractReference is not null.
   */
  private boolean abstractBackReferenceCanReferenceBackAbstractReferenceWhenParametersAreNotNull( //NOSONAR: This method is an instance method.
    final IAbstractBackReference<IEntity> abstractBackReference,
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
}
