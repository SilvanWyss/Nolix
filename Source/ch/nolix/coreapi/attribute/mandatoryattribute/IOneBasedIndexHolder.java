package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IOneBasedIndexHolder} has a one-based index.
 * 
 * @author Silvan Wyss
 */
public interface IOneBasedIndexHolder {
  /**
   * @return the one-based index of the current {@link IOneBasedIndexHolder}.
   */
  int getOneBasedIndex();
}
