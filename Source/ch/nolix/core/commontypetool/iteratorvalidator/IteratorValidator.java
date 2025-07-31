package ch.nolix.core.commontypetool.iteratorvalidator;

import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.commontypetool.iteratorvalidator.IIteratorValidator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-05-12
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT);
    }
  }
}
