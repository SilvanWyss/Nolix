//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2020-01-05
 */
public interface IOptionalIdHolder {

  //method declaration
  /**
   * @return the id of the current {@link IOptionalIdHolder}.
   */
  String getId();

  //method declaration
  /**
   * @return the id of the current {@link IOptionalIdHolder} in quotes.
   */
  String getIdInQuotes();

  //method declaration
  /**
   * @return true if the current {@link IOptionalIdHolder} has an id.
   */
  boolean hasId();

  //method declaration
  /**
   * @param id
   * @return true if the current {@link IOptionalIdHolder} has the given id.
   */
  boolean hasId(String id);
}
