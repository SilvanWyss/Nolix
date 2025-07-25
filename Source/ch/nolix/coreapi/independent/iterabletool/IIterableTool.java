package ch.nolix.coreapi.independent.iterabletool;

/**
 * A {@link IIterableTool} provides methods to handle {@link Iterable}s.
 * 
 * @author Silvan Wyss
 * @version 2024-12-12
 */
public interface IIterableTool {

  /**
   * @param iterable
   * @return the number of elements of the given iterable.
   */
  int getElementCount(Iterable<?> iterable);
}
