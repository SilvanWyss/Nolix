//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//interface
/**
 * @author Silvan Wyss
 * @version 2024-07-28
 * @param <E> is the type of the elements of a {@link IViewProviderContainer}.
 */
public interface IViewProviderContainer<E> {

  //method declaration
  /**
   * @param param1BasedStartIndex
   * @return a new view of the current {@link IViewProviderContainer} from the
   *         given param1BasedStartIndex.
   * @throws RuntimeException if the given param1BasedStartIndex is not positive.
   * @throws RuntimeException if the given param1BasedStartIndex is bigger than
   *                          the number of elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewFrom1BasedStartIndex(int param1BasedStartIndex);

  //method declaration
  /**
   * @param param1BasedStartIndex
   * @param param1BasedEndIndex
   * @return a new view of the current {@link IViewProviderContainer} from the
   *         given param1BasedStartIndex to the given param1BasedEndIndex.
   * @throws RuntimeException if the given param1BasedStartIndex is not positive.
   * @throws RuntimeException if the given param1BasedStartIndex is smaller than
   *                          the given param1BasedEndIndex.
   * @throws RuntimeException if the given param1BasedEndIndex is bigger than the
   *                          number of elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewFrom1BasedStartIndexTo1BasedEndIndex(int param1BasedStartIndex, int param1BasedEndIndex);

  //method declaration
  /**
   * @param param1BasedEndIndex
   * @return a new view of the current {@link IViewProviderContainer} to the given
   *         param1BasedEndIndex.
   * @throws RuntimeException if the given param1BasedEndIndex is not positive.
   * @throws RuntimeException if the given param1BasedEndIndex is bigger than the
   *                          number of the elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewTo1BasedEndIndex(int param1BasedEndIndex);

  //method declaration
  /**
   * @return a new view of the current {@link IViewProviderContainer} without the
   *         first element.
   * @throws RuntimeException if the current {@link IViewProviderContainer} is
   *                          empty.
   */
  IContainer<E> getViewWithoutFirst();

  //method declaration
  /**
   * @param n
   * @return a new view of the current {@link IViewProviderContainer} without the
   *         first n elements.
   * @throws RuntimeException if the given n is not positive.
   * @throws RuntimeException if the given n is bigger than the number of the
   *                          elements of the current
   *                          {@link IViewProviderContainer}.
   */
  IContainer<E> getViewWithoutFirst(int n);

  //method declaration
  /**
   * @return a new view of the current {@link IViewProviderContainer} without the
   *         last element.
   * @throws RuntimeException if the current {@link IViewProviderContainer} is
   *                          empty.
   */
  IContainer<E> getViewWithoutLast();

  //method declaration
  /**
   * @param n
   * @return a new sub view of the current {@link IViewProviderContainer} without
   *         the last n elements.
   * @throws RuntimeException if the given n is not positive.
   * @throws RuntimeException if the given n is bigger than the number of the
   *                          elements of the current
   *                          {@link IViewProviderContainer}.
   */
  IContainer<E> getViewWithoutLast(int n);
}
