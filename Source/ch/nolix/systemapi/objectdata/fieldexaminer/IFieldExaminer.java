/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.database.databaseobjectexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.BaseReference;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @param <F> the type of the {@link Field}s a {@link IFieldExaminer} is for
 */
public interface IFieldExaminer<F extends Field> extends IDatabaseObjectExaminer<F> {
  /**
   * @param field
   * @return true if the given field belongs to a {@link IEntity}, false otherwise
   */
  boolean belongsToEntity(F field);

  /**
   * @param field
   * @return true if the given field belongs to a loaded {@link IEntity}, false
   *         otherwise
   */
  boolean belongsToLoadedEntity(F field);

  /**
   * @param field
   * @param baseReference
   * @return true if the given field can reference back the given baseReference,
   *         false otherwise
   */
  boolean canReferenceBackBaseReference(F field, BaseReference baseReference);

  /**
   * @param field
   * @return true if the given field is for multi content, false otherwise
   */
  boolean isForMultiContent(F field);

  /**
   * @param field
   * @return true if the given field is for single content, false otherwise
   */
  boolean isForSingleContent(F field);

  /**
   * @param field
   * @return true if the given field is mandatory but empty, false otherwise
   */
  boolean isMandatoryButEmpty(F field);

  /**
   * @param field
   * @return true if the given field is new or edited but set, false otherwise
   */
  boolean isSetForCaseWhenIsMandatoryAndNewOrEdited(F field);
}
