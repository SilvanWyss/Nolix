package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

/**
 * A {@link IOneBaseIndexed} has a one-based index.
 * 
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IOneBaseIndexed {

  /**
   * @return the one-based index of the current {@link IOneBaseIndexed}.
   */
  int getOneBasedIndex();
}
