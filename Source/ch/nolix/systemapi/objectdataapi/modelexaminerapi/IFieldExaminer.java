package ch.nolix.systemapi.objectdataapi.modelexaminerapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IFieldExaminer {

  /**
   * @param field
   * @param abstractReference
   * @return true if the given field can reference back the given
   *         abstractReference, false otherwise.
   */
  boolean canReferenceBackAbstractReference(IField field, IAbstractReference<? extends IEntity> abstractReference);
}
