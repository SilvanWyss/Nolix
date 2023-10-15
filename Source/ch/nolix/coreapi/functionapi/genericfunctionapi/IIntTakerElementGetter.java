//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IIntTakerElementGetter} has a method that takes an int and returns
 * an element.
 * 
 * @author Silvan Wyss
 * @date 2019-02-16
 * @param <E> is the type of the elements a {@link IIntTakerElementGetter}
 *            returns.
 */
@FunctionalInterface
public interface IIntTakerElementGetter<E> {

  // method declaration
  /**
   * @param value
   * @return an element for the given value from the current
   *         {@link IIntTakerElementGetter}.
   */
  E getOutput(int value);
}
