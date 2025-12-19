package ch.nolix.coreapi.datastructure.pair;

/**
 * A {@link IPair} contains 2 elements.
 * 
 * @author Silvan Wyss
 * @param <E1> is the type of the element1 of a {@link IPair}.
 * @param <T>  is the type of the element2 of a {@link IPair}.
 */
public interface IPair<E1, T> {
  /**
   * @return the element1 of the current {@link IPair}.
   */
  E1 getStoredElement1();

  /**
   * @return the element2 of the current {@link IPair}.
   */
  T getStoredElement2();

  /**
   * @param object
   * @return true if the element1 of the current {@link IPair} is the given
   *         object, false otherwise.
   */
  boolean hasElement1(Object object);

  /**
   * @param object
   * @return true if the element2 of the current {@link IPair} is the given
   *         object, false otherwise.
   */
  boolean hasElement2(Object object);
}
