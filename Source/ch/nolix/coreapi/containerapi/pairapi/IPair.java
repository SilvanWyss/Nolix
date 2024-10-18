package ch.nolix.coreapi.containerapi.pairapi;

/**
 * A {@link IPair} contains 2 elements.
 * 
 * @author Silvan Wyss
 * @version 2022-07-02
 * @param <E1> is the type of the element1 of a {@link IPair}.
 * @param <E2> is the type of the element2 of a {@link IPair}.
 */
public interface IPair<E1, E2> {

  /**
   * @return the element1 of the current {@link IPair}.
   */
  E1 getStoredElement1();

  /**
   * @return the element2 of the current {@link IPair}.
   */
  E2 getStoredElement2();

  /**
   * @param object
   * @return true if the element1 of the current {@link IPair} is the given
   *         object.
   */
  boolean hasElement1(Object object);

  /**
   * @param object
   * @return true if the element2 of the current {@link IPair} is the given
   *         object.
   */
  boolean hasElement2(Object object);
}
