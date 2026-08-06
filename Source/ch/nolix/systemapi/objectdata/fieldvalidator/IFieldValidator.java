/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public interface IFieldValidator {
  void assertBelongsToEntity(Field field);

  void assertDoesNotBelongToEntity(Field field);

  void assertIsNotEmpty(Field field);

  void assertIsNotMandatoryAndEmptyBoth(Field field);

  void assertKnowsParentColumn(Field field);
}
