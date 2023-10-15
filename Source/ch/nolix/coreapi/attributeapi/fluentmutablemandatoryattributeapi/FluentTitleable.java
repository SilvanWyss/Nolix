//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Titled;

//interface
/**
 * A {@link FluentTitleable} is a {@link Titled} whose title can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 * @param <FT> is the type of a {@link FluentTitleable}.
 */
public interface FluentTitleable<FT extends FluentTitleable<FT>> extends Titled {

  // method declaration
  /**
   * Sets the title of the current {@link FluentTitleable}.
   * 
   * @param title
   * @return the current {@link FluentTitleable}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FT setTitle(String title);
}
