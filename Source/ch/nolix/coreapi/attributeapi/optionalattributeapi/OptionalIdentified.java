//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalIdentified} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2020-01-05
 */
public interface OptionalIdentified {

  // method declaration
  /**
   * @return the id of the current {@link OptionalIdentified}.
   */
  String getId();

  // method declaration
  /**
   * @return the id of the current {@link OptionalIdentified} in quotes.
   */
  String getIdInQuotes();

  // method declaration
  /**
   * @return true if the current {@link OptionalIdentified} has an id.
   */
  boolean hasId();

  // method declaration
  /**
   * @param id
   * @return true if the current {@link OptionalIdentified} has the given id.
   */
  boolean hasId(String id);
}
