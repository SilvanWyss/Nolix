/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public interface IFieldExaminer extends IDatabaseObjectExaminer {
  /**
   * @param field
   * @return true if the given field belongs to a {@link IEntity}, false otherwise
   */
  boolean belongsToEntity(Field field);

  /**
   * @param field
   * @return true if the given field belongs to a loaded {@link IEntity}, false
   *         otherwise
   */
  boolean belongsToLoadedEntity(Field field);

  /**
   * @param field
   * @param baseReference
   * @return true if the given field can reference back the given baseReference,
   *         false otherwise
   */
  boolean canReferenceBackBaseReference(Field field, BaseReference baseReference);

  /**
   * @param field
   * @return true if the given field is for multi content, false otherwise
   */
  boolean isForMultiContent(Field field);

  /**
   * @param field
   * @return true if the given field is for single content, false otherwise
   */
  boolean isForSingleContent(Field field);

  /**
   * @param field
   * @return true if the given field is mandatory but empty, false otherwise
   */
  boolean isMandatoryButEmpty(Field field);

  /**
   * @param field
   * @return true if the given field is new or edited but set, false otherwise
   */
  boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(Field field);
}
