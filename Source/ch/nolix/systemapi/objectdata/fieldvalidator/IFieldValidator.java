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
  void assertBelongsToEntity(F field);

  void assertDoesNotBelongToEntity(F field);

  void assertIsNotEmpty(F field);

  void assertIsNotMandatoryAndEmptyBoth(F field);

  void assertKnowsParentColumn(F field);
}
