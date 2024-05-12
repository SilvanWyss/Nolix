//package declaration
package ch.nolix.coreapi.containerapi.iteratorvalidatorapi;

//Java imports
import java.util.Iterator;

//interface
/**
 * @author Silvan Wyss
 * @date 2024-05-12
 */
public interface IIteratorValidator {

  //method declaration
  /**
   * @param iterator
   * @throws RuntimeException if the given iterator does not have a next element.
   */
  void assertHasNext(Iterator<Object> iterator);
}
