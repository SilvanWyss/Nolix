package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalCardinalityHolder} can have a cardinality.
 * 
 * @author Silvan Wyss
 * @version 2023-10-24
 */
public interface IOptionalCardinalityHolder {
  /**
   * @return the cardinality of the current {@link IOptionalCardinalityHolder}.
   * @throws RuntimeException if the current {@link IOptionalCardinalityHolder}
   *                          does not have a cardinality.
   */
  String getCardinality();

  /**
   * @return true if the current {@link IOptionalCardinalityHolder} has a
   *         cardinality.
   */
  boolean hasCardinality();
}
