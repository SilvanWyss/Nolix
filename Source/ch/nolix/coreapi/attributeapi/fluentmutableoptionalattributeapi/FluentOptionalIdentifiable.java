//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalIdentified;

//interface
/**
 * A {@link FluentOptionalIdentifiable} is a {@link OptionalIdentified} whose id
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-02-01
 * @param <FOI> is the type of a {@link FluentOptionalIdentifiable}.
 */
public interface FluentOptionalIdentifiable<FOI extends FluentOptionalIdentifiable<FOI>> extends OptionalIdentified {

  //method declaration
  /**
   * Removes the id of the current {@link FluentOptionalIdentifiable}.
   * 
   * @return the current {@link FluentOptionalIdentifiable}.
   */
  FOI removeId();

  //method declaration
  /**
   * Sets the id of the current {@link FluentOptionalIdentifiable}.
   * 
   * @param id
   * @return the current {@link FluentOptionalIdentifiable}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  FOI setId(String id);
}
