//package declaration
package ch.nolix.core.container.iteratorvalidator;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.iteratorvalidatorapi.IIteratorValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @version 2024-05-12
 */
public final class IteratorValidator implements IIteratorValidator {

  //method
  //For a better performance, this implementation does not use all comfortable methods.
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT);
    }
  }
}