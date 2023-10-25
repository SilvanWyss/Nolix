//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTitleHolder;

//interface
/**
 * A {@link IFluentOptionalTitleHolder} is a {@link IOptionalTitleHolder} whose title
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FOTH> is the type of a {@link IFluentOptionalTitleHolder}.
 */
public interface IFluentOptionalTitleHolder<FOTH extends IFluentOptionalTitleHolder<FOTH>> extends IOptionalTitleHolder {

  //method declaration
  /**
   * Removes the title of the current {@link IFluentOptionalTitleHolder}.
   * 
   * @return the current {@link IFluentOptionalTitleHolder}.
   */
  FOTH removeTitle();

  //method declaration
  /**
   * Sets the title of the current {@link IFluentOptionalTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentOptionalTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FOTH setTitle(String title);
}
