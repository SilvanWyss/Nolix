//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalLabeled} can have a label.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalLabeled {

  //method declaration
  /**
   * @return the label of the current {@link OptionalLabeled}.
   * @throws RuntimeException if the current {@link OptionalLabeled} does not have
   *                          a label.
   */
  String getLabel();

  //method declaration
  /**
   * @return true if the current {@link OptionalLabeled} has a label.
   */
  boolean hasLabel();
}
