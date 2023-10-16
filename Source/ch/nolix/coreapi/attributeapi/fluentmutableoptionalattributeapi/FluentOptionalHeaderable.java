//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalHeadered;

//interface
/**
 * A {@link FluentOptionalHeaderable} is a {@link OptionalHeadered} whose header
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-02-24
 * @param <FOH> is the type of a {@link FluentOptionalHeaderable}.
 */
public interface FluentOptionalHeaderable<FOH extends FluentOptionalHeaderable<FOH>> extends OptionalHeadered {

  //method declaration
  /**
   * Removes the header of current {@link FluentOptionalHeaderable}.
   * 
   * @return the current {@link FluentOptionalHeaderable}.
   */
  FOH removeHeader();

  //method declaration
  /**
   * Sets the header of the current {@link FluentOptionalHeaderable}.
   * 
   * @param header
   * @return the current {@link FluentOptionalHeaderable}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  FOH setHeader(String header);
}
