//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalIdentified;

//interface
/**
 * A {@link IFluentOptionalIdHolder} is a {@link OptionalIdentified} whose id
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-02-01
 * @param <FOIH> is the type of a {@link IFluentOptionalIdHolder}.
 */
public interface IFluentOptionalIdHolder<FOIH extends IFluentOptionalIdHolder<FOIH>> extends OptionalIdentified {

  //method declaration
  /**
   * Removes the id of the current {@link IFluentOptionalIdHolder}.
   * 
   * @return the current {@link IFluentOptionalIdHolder}.
   */
  FOIH removeId();

  //method declaration
  /**
   * Sets the id of the current {@link IFluentOptionalIdHolder}.
   * 
   * @param id
   * @return the current {@link IFluentOptionalIdHolder}.
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  FOIH setId(String id);
}
