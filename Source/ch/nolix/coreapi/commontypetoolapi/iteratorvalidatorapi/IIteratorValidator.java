package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

import java.util.Iterator;

/**
 * @author Silvan Wyss
 * @version 2024-05-12
 */
public interface IIteratorValidator {

  /**
   * @param iterator
   * @throws RuntimeException if the given iterator does not have a next element.
   */
  void assertHasNext(Iterator<?> iterator);
}
