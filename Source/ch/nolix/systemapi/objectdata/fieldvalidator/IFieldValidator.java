/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.database.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 * @param <F> the type of the {@link Field}s a {@link IFieldValidator} validates
 */
public interface IFieldValidator<F extends Field> extends DatabaseObjectValidator<F> {
  /**
   * @param field
   * @throws RuntimeException if the given field does not belong to a Entity
   */
  void assertBelongsToEntity(F field);

  /**
   * @param field
   * @throws RuntimeException if the given field belongs to a Entity
   */
  void assertDoesNotBelongToEntity(F field);

  /**
   * @param field
   * @throws RuntimeException if the given field is empty
   */
  void assertIsNotEmpty(F field);

  /**
   * @param field
   * @throws RuntimeException if the given field is mandatory and empty
   */
  void assertIsNotMandatoryAndEmptyBoth(F field);

  /**
   * @param field
   * @throws RuntimeException if the given field does not know its parent IColumn
   */
  void assertKnowsParentColumn(F field);
}
