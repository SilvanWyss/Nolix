//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTitleHolder;

//interface
/**
 * A {@link IFluentMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder}
 * whose title can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FMOTH> is the type of a {@link IFluentMutableOptionalTitleHolder}.
 */
public interface IFluentMutableOptionalTitleHolder<FMOTH extends IFluentMutableOptionalTitleHolder<FMOTH>>
    extends IOptionalTitleHolder {

  //method declaration
  /**
   * Removes the title of the current {@link IFluentMutableOptionalTitleHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalTitleHolder}.
   */
  FMOTH removeTitle();

  //method declaration
  /**
   * Sets the title of the current {@link IFluentMutableOptionalTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableOptionalTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FMOTH setTitle(String title);
}
