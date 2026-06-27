/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iteratorvalidator;

import java.util.Iterator;

import ch.nolix.baseapi.commontype.iteratorvalidator.IIteratorValidator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class IteratorValidator implements IIteratorValidator {
  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public void assertHasNext(final Iterator<?> iterator) {
    //Asserts that the given iterator is not null.
    if (iterator == null) {
      throw ArgumentIsNullException.forArgumentType(Iterator.class);
    }

    //Asserts that the given iterator has a next element.
    if (!iterator.hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
        this,
        LowerCaseVariableNameCatalog.NEXT_ELEMENT);
    }
  }
}
