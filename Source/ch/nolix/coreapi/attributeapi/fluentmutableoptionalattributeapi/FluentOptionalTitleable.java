//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalTitled;

//interface
/**
 * A {@link FluentOptionalTitleable} is a {@link OptionalTitled} whose title can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FOT> is the type of a {@link FluentOptionalTitleable}.
 */
public interface FluentOptionalTitleable<FOT extends FluentOptionalTitleable<FOT>> extends OptionalTitled {

  //method declaration
  /**
   * Removes the title of the current {@link FluentOptionalTitleable}.
   * 
   * @return the current {@link FluentOptionalTitleable}.
   */
  FOT removeTitle();

  //method declaration
  /**
   * Sets the title of the current {@link FluentOptionalTitleable}.
   * 
   * @param title
   * @return the current {@link FluentOptionalTitleable}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FOT setTitle(String title);
}
