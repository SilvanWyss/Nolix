//package declaration
package ch.nolix.core.errorcontrol.validator;

//class
/**
 * The {@link GlobalIterableHelper} provides methods to analyse {@link Iterable}s. The
 * {@link GlobalIterableHelper} is not useful for public because it does not use
 * dependencies. Of the {@link GlobalIterableHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-09-30
 */
final class GlobalIterableHelper {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalIterableHelper} can be created.
   */
  private GlobalIterableHelper() {
  }

  //static method
  /**
   * @param iterableObject
   * @param <E>            is the type of the elements of the given
   *                       iterableObject.
   * @return true if the given iterableObject contains at least 1 element.
   */
  public static <E> boolean containsAny(final Iterable<E> iterableObject) {
    return !isEmpty(iterableObject);
  }

  //static method
  /**
   * @param iterableObject
   * @param <E>            is the type of the elements of the given
   *                       iterableObject.
   * @return true if the given iterableObject does not contain an element.
   */
  public static <E> boolean isEmpty(final Iterable<E> iterableObject) {
    return !iterableObject.iterator().hasNext();
  }
}
