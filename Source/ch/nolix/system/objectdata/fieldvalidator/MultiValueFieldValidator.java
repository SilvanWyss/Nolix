/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.MultiValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IMultiValueFieldValidator;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public final class MultiValueFieldValidator extends FieldValidator implements IMultiValueFieldValidator {
  private static final MultiValueFieldExaminer MULTI_VALUE_EXAMINER = new MultiValueFieldExaminer();

  @Override
  public <V> void assertCanAddValue(final IMultiValueField<V> multiValueField, final V value) {
    if (!MULTI_VALUE_EXAMINER.canAddValue(multiValueField, value)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValueField,
        "cannot add the given value '" + value + "'");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanBeCleared(final IMultiValueField<?> multiValueField) {
    if (!MULTI_VALUE_EXAMINER.canBeCleared(multiValueField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValueField, "cannot be cleared");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <V> void assertCanRemoveValue(final IMultiValueField<V> multiValueField, final V value) {
    if (!MULTI_VALUE_EXAMINER.canRemoveValue(multiValueField, value)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValueField,
        "cannot remove the given value '" + value + "'");
    }
  }
}
