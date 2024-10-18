package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTitleHolder;

/**
 * A {@link IFluentMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder}
 * whose title can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-02-06
 * @param <FMOTH> is the type of a {@link IFluentMutableOptionalTitleHolder}.
 */
public interface IFluentMutableOptionalTitleHolder<FMOTH extends IFluentMutableOptionalTitleHolder<FMOTH>>
extends IOptionalTitleHolder {

  /**
   * Removes the title of the current {@link IFluentMutableOptionalTitleHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalTitleHolder}.
   */
  FMOTH removeTitle();

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
