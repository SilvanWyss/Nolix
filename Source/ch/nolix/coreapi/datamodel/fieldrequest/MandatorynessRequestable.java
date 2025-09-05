package ch.nolix.coreapi.datamodel.fieldrequest;

/**
 * A {@link MandatorynessRequestable} can be asked if it is mandatory.
 * 
 * @author Silvan Wyss
 * @version 2021-12-26
 */
public interface MandatorynessRequestable {
  /**
   * @return true if the current {@link MandatorynessRequestable} is mandatory,
   *         false otherweise.
   */
  boolean isMandatory();

  /**
   * @return true if the current {@link MandatorynessRequestable} is optional,
   *         false otherweise.
   */
  default boolean isOptional() {
    return !isMandatory();
  }
}
