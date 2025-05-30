package ch.nolix.coreapi.containerapi.baseapi;

/**
 * @author Silvan Wyss
 * @version 2024-07-28
 * @param <E> is the type of the elements of a {@link IIntervallContainerViewProvider}.
 */
public interface IIntervallContainerViewProvider<E> {

  /**
   * @param oneBasedStartIndex
   * @return a new view of the current {@link IIntervallContainerViewProvider} from the
   *         given oneBasedStartIndex.
   * @throws RuntimeException if the given oneBasedStartIndex is bigger than
   *                          the number of elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewFromOneBasedStartIndex(int oneBasedStartIndex);

  /**
   * @param oneBasedStartIndex
   * @param oneBasedEndIndex
   * @return a new view of the current {@link IIntervallContainerViewProvider} from the
   *         given oneBasedStartIndex to the given oneBasedEndIndex.
   * @throws RuntimeException if the given oneBasedStartIndex is not positive.
   * @throws RuntimeException if the given oneBasedStartIndex is smaller than
   *                          the given oneBasedEndIndex.
   * @throws RuntimeException if the given oneBasedEndIndex is bigger than the
   *                          number of elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(int oneBasedStartIndex, int oneBasedEndIndex);

  /**
   * @param oneBasedEndIndex
   * @return a new view {@link IContainer} of the current
   *         {@link IIntervallContainerViewProvider} to the given oneBasedEndIndex.
   * @throws RuntimeException if the given oneBasedEndIndex is not positive.
   * @throws RuntimeException if the given oneBasedEndIndex is bigger than the
   *                          number of the elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> getViewToOneBasedEndIndex(int oneBasedEndIndex);

  /**
   * @return a new view {@link IContainer} view of the current
   *         {@link IIntervallContainerViewProvider} without the first element.
   * @throws RuntimeException if the current {@link IIntervallContainerViewProvider} is
   *                          empty.
   */
  IContainer<E> getViewWithoutFirst();

  /**
   * @param n
   * @return a new view {@link IContainer} view of the current
   *         {@link IIntervallContainerViewProvider} without the first n elements.
   * @throws RuntimeException if the given n is negative.
   */
  IContainer<E> getViewWithoutFirst(int n);

  /**
   * @return a new view {@link IContainer} view of the current
   *         {@link IIntervallContainerViewProvider} without the last element.
   * @throws RuntimeException if the current {@link IIntervallContainerViewProvider} is
   *                          empty.
   */
  IContainer<E> getViewWithoutLast();

  /**
   * @param n
   * @return a new view {@link IContainer} of the current
   *         {@link IIntervallContainerViewProvider} without the last n elements.
   * @throws RuntimeException if the given n is negative.
   */
  IContainer<E> getViewWithoutLast(int n);
}
