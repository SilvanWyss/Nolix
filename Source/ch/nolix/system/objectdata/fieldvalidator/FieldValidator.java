/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IFieldValidator;
import ch.nolix.systemapi.objectdata.model.Field;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public class FieldValidator implements IFieldValidator {
  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  @Override
  public final void assertBelongsToEntity(final Field field) {
    if (!field.belongsToEntity()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(field, IEntity.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertDoesNotBelongToEntity(final Field field) {
    if (field.belongsToEntity()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(field, field.getStoredParentEntity());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotEmpty(final Field field) {
    if (field.isEmpty()) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotMandatoryAndEmptyBoth(final Field field) {
    if (FIELD_EXAMINER.isMandatoryButEmpty(field)) {
      throw EmptyArgumentException.forArgument(field);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertKnowsParentColumn(final Field field) {
    if (!field.knowsParentColumn()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(field, "does not know its parent column");
    }
  }
}
